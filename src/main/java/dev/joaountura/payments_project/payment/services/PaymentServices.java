package dev.joaountura.payments_project.payment.services;

import com.google.zxing.WriterException;
import dev.joaountura.payments_project.payment.PaymentRepository;
import dev.joaountura.payments_project.payment.mappers.PaymentMapper;
import dev.joaountura.payments_project.payment.models.Payment;
import dev.joaountura.payments_project.payment.models.PaymentCreateDTO;
import dev.joaountura.payments_project.payment.models.PaymentGetDTO;
import dev.joaountura.payments_project.product.models.Product;
import dev.joaountura.payments_project.product.services.ProductServices;
import dev.joaountura.payments_project.receiver.models.Receiver;
import dev.joaountura.payments_project.receiver.services.ReceiverServices;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentServices {

    private final ProductServices productServices;
    private final ReceiverServices receiverServices;
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository ;
    private final PaymentProducer paymentProducer;
    private final PixService pixService;

    public PaymentServices(ProductServices productServices, ReceiverServices receiverServices, PaymentMapper paymentMapper, PaymentRepository paymentRepository, PaymentProducer paymentProducer, PixService pixService) {
        this.productServices = productServices;
        this.receiverServices = receiverServices;
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
        this.paymentProducer = paymentProducer;
        this.pixService = pixService;
    }

    @Transactional
    public byte[] createPayment(PaymentCreateDTO paymentCreateDTO) throws IOException, WriterException {

        List<Product> products = productServices.getByIdList(paymentCreateDTO.productIdList());

        Receiver receiver = receiverServices.getByExternalID(paymentCreateDTO.receiverID());

        BigDecimal totalPayment = products.stream().map(Product::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);

        Payment newPayment = paymentRepository.save(paymentMapper.paymentCreateMapper(paymentCreateDTO, totalPayment, products, receiver));


        byte[] qrCodePix = pixService.gerarQRCode(totalPayment, receiver.getKey(), receiver.getName(), receiver.getCity());


        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                paymentProducer.sendPayment(paymentMapper.paymentRabbitDTO(newPayment));
            }
        });


        return qrCodePix;

    }

    public List<PaymentGetDTO> getAllPayments(){

        List<Payment> payments = paymentRepository.findAll();

        return paymentMapper.paymentGetMapper(payments);

    }

    public void deletePayment(Long id) throws EntityNotFoundException{
        paymentRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Payment not found"));
        paymentRepository.deleteById(id);
    }

}
