package dev.joaountura.payments_project.payment.mappers;

import dev.joaountura.payments_project.payment.models.Payment;
import dev.joaountura.payments_project.payment.models.PaymentCreateDTO;
import dev.joaountura.payments_project.payment.models.PaymentGetDTO;
import dev.joaountura.payments_project.product.models.Product;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;
import java.util.List;

@Component
public class PaymentMapper {

    public Payment paymentCreateMapper(PaymentCreateDTO paymentCreateDTO, BigDecimal totalPayment, List<Product> products){

        return Payment.builder()
                .custumer(paymentCreateDTO.custumer())
                .email(paymentCreateDTO.email())
                .value(totalPayment)
                .productList(products)
                .build();

    }

    public List<PaymentGetDTO> paymentGetMapper(List<Payment> payments){
        return payments.stream().map((Payment payment) -> PaymentGetDTO
                .builder()
                .id(payment.getId())
                .date(payment.getDate())
                .email(payment.getEmail())
                .custumer(payment.getCustumer())
                .status(payment.getStatus())
                .value(payment.getValue())
                .build()
        ).toList();

    }

}
