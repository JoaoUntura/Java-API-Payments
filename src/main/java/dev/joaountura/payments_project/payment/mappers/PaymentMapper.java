package dev.joaountura.payments_project.payment.mappers;

import dev.joaountura.payments_project.payment.models.Payment;
import dev.joaountura.payments_project.payment.models.PaymentCreateDTO;
import dev.joaountura.payments_project.payment.models.PaymentGetDTO;
import dev.joaountura.payments_project.payment.models.PaymentRabbitDTO;
import dev.joaountura.payments_project.product.models.Product;
import dev.joaountura.payments_project.receiver.models.Receiver;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.List;

@Component
public class PaymentMapper {

    public Payment paymentCreateMapper(PaymentCreateDTO paymentCreateDTO, BigDecimal totalPayment, List<Product> products, Receiver receiver){

        return Payment.builder()
                .customer(paymentCreateDTO.customer())
                .email(paymentCreateDTO.email())
                .value(totalPayment)
                .productList(products)
                .receiver(receiver)
                .build();

    }

    public List<PaymentGetDTO> paymentGetMapper(List<Payment> payments){
        return payments.stream().map((Payment payment) -> PaymentGetDTO
                .builder()
                .id(payment.getExternalID())
                .date(payment.getDate())
                .email(payment.getEmail())
                .customer(payment.getCustomer())
                .status(payment.getStatus())
                .value(payment.getValue())
                .build()
        ).toList();

    }

    public PaymentRabbitDTO paymentRabbitMapper(Payment payment){
        return PaymentRabbitDTO.builder()
                .receiver(payment.getReceiver().getExternalID())
                .id(payment.getExternalID())
                .value(payment.getValue())
                .status(payment.getStatus())
                .date(payment.getDate())
                .email(payment.getEmail())
                .productNames(payment.getProductList().stream().map(Product::getName).toList())
                .build();
    }

}