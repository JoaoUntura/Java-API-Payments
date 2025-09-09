package dev.joaountura.payments_project;

import dev.joaountura.payments_project.payment.models.Payment;
import dev.joaountura.payments_project.payment.models.PaymentCreateDTO;
import dev.joaountura.payments_project.product.models.Product;
import dev.joaountura.payments_project.receiver.models.Receiver;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Mocks {



    public static PaymentCreateDTO paymentCreateDTOMock(){
        return new PaymentCreateDTO("Teste Customer", "email@gmail.com", List.of(1L, 2L), UUID.randomUUID());
    }

    public static List<Product> productsMock(){
        return List.of(Product.builder().id(1L).name("TestProduct").value(BigDecimal.valueOf(2.99)).build(),
                Product.builder().id(2L).name("TestProduct2").value(BigDecimal.valueOf(3.99)).build());
    }

    public static Receiver receiverMock(){
        PaymentCreateDTO paymentCreateDTOMock = paymentCreateDTOMock();
        return Receiver.builder().name("Receiver Test").pixKey("12345678910").city("Test City").externalID(paymentCreateDTOMock.receiverID()).build();
    }

    public static Payment paymentMock(){
        PaymentCreateDTO paymentCreateDTOMock = paymentCreateDTOMock();
        List<Product> productsMock = productsMock();
        Receiver receiverMock = receiverMock();
        return Payment.builder().email(paymentCreateDTOMock.email()).date(LocalDateTime.now()).receiver(receiverMock).productList(productsMock).customer(paymentCreateDTOMock.customer()).value(BigDecimal.valueOf(6.98)).externalID(UUID.randomUUID()).build();
    }

}
