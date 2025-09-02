package dev.joaountura.payments_project.payment.services;

import dev.joaountura.payments_project.payment.PaymentRepository;
import dev.joaountura.payments_project.payment.mappers.PaymentMapper;
import dev.joaountura.payments_project.payment.models.Payment;
import dev.joaountura.payments_project.payment.models.PaymentCreateDTO;
import dev.joaountura.payments_project.payment.models.PaymentGetDTO;
import dev.joaountura.payments_project.product.models.Product;
import dev.joaountura.payments_project.product.services.ProductServices;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentServices {

    private final ProductServices productServices;
    private final PaymentMapper paymentMapper;
    private final PaymentRepository paymentRepository ;
    private final PaymentProducer paymentProducer;

    public PaymentServices(ProductServices productServices, PaymentMapper paymentMapper, PaymentRepository paymentRepository, PaymentProducer paymentProducer) {
        this.productServices = productServices;
        this.paymentMapper = paymentMapper;
        this.paymentRepository = paymentRepository;
        this.paymentProducer = paymentProducer;
    }


    public Payment createPayment(PaymentCreateDTO paymentCreateDTO){

        List<Product> products = productServices.getByIdList(paymentCreateDTO.productIdList());

        BigDecimal totalPayment = products.stream().map(Product::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);

        Payment newPayment = paymentRepository.save(paymentMapper.paymentCreateMapper(paymentCreateDTO, totalPayment, products));

        paymentProducer.sendPayment(newPayment);

        return newPayment;

    }

    public List<PaymentGetDTO> getAllPayments(){

        List<Payment> payments = paymentRepository.findAll();

        return paymentMapper.paymentGetMapper(payments);

    }

    public void deletePayment(Long id){
        paymentRepository.deleteById(id);
    }

}
