package dev.joaountura.payments_project.payment_tests;

import com.google.zxing.WriterException;
import dev.joaountura.payments_project.payment.PaymentRepository;
import dev.joaountura.payments_project.payment.mappers.PaymentMapper;
import dev.joaountura.payments_project.payment.models.Payment;
import dev.joaountura.payments_project.payment.models.PaymentCreateDTO;
import dev.joaountura.payments_project.payment.models.PaymentRabbitDTO;
import dev.joaountura.payments_project.payment.services.PaymentProducer;
import dev.joaountura.payments_project.payment.services.PaymentServices;
import dev.joaountura.payments_project.payment.services.PixService;
import dev.joaountura.payments_project.product.models.Product;
import dev.joaountura.payments_project.product.services.ProductServices;
import dev.joaountura.payments_project.receiver.models.Receiver;
import dev.joaountura.payments_project.receiver.services.ReceiverServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionSynchronizationManager;


import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)


public class PaymentServicesTests {


    @Mock
    private ProductServices productServices;
    @Mock private ReceiverServices receiverServices;
    @Mock private PaymentRepository paymentRepository;


    @Mock private PaymentProducer paymentProducer;

    @InjectMocks
    private PaymentServices paymentService;


    private PaymentMapper paymentMapper;
    private PixService pixService;

    @BeforeEach
    public void setup() {
        paymentMapper =  Mockito.spy(new PaymentMapper());

        pixService = Mockito.spy(new PixService());

        paymentService = new PaymentServices(
                productServices,
                receiverServices,
                paymentMapper,
                paymentRepository,
                paymentProducer,
                pixService
        );
    }
    @Test

    public void shouldReturnQRCodeAndCreatePayment() throws Exception  {
        try (MockedStatic<TransactionSynchronizationManager> mocked = Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            mocked.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);
            mocked.when(() -> TransactionSynchronizationManager.registerSynchronization(Mockito.any())).then(invocation -> null);

            PaymentCreateDTO paymentCreateMock = new PaymentCreateDTO("Teste Customer", "email@gmail.com", List.of(1L, 2L), UUID.randomUUID());
            List<Product> productsMock = List.of(Product.builder().id(1L).name("TestProduct").value(BigDecimal.valueOf(2.99)).build()
                    , Product.builder().id(2L).name("TestProduct2").value(BigDecimal.valueOf(3.99)).build());
            Receiver receiverMock = Receiver.builder().id(1L).name("Receiver Test").key("12345678910").city("Test City").externalID(paymentCreateMock.receiverID()).build();

            Payment paymentMock = Payment.builder().id(1L).email(paymentCreateMock.email()).date(LocalDateTime.now()).receiver(receiverMock).productList(productsMock).customer(paymentCreateMock.customer()).value(BigDecimal.valueOf(6.98)).externalID(UUID.randomUUID()).build();

            Mockito.when(productServices.getByIdList(paymentCreateMock.productIdList())).thenReturn(productsMock);
            Mockito.when(receiverServices.getByExternalID(paymentCreateMock.receiverID())).thenReturn(receiverMock);
            Mockito.when(paymentRepository.save(Mockito.any(Payment.class))).thenReturn(paymentMock);

            byte[] qrCode = paymentService.createPayment(paymentCreateMock);

            Assertions.assertNotNull(qrCode);

            Assertions.assertTrue(qrCode.length > 0);

            Mockito.verify(productServices).getByIdList(paymentCreateMock.productIdList());
            Mockito.verify(pixService).gerarQRCode(Mockito.any(BigDecimal.class), Mockito.any(String.class),Mockito.any(String.class),Mockito.any(String.class));
            Mockito.verify(receiverServices).getByExternalID(paymentCreateMock.receiverID());
            Mockito.verify(paymentRepository).save(Mockito.any(Payment.class));
            Mockito.verify(paymentMapper).paymentCreateMapper(
                    Mockito.any(PaymentCreateDTO.class),
                    Mockito.any(BigDecimal.class),
                    Mockito.anyList(),
                    Mockito.any(Receiver.class));
        }


    }


}
