package dev.joaountura.payments_project.payment.services;

import dev.joaountura.payments_project.config.RabbitConfig;
import dev.joaountura.payments_project.payment.models.Payment;


import dev.joaountura.payments_project.payment.models.PaymentRabbitDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {


    private final RabbitTemplate rabbitTemplate;


    public PaymentProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendPayment(PaymentRabbitDTO payment){
        rabbitTemplate.convertAndSend(RabbitConfig.PAYMENT_EXCHANGE, RabbitConfig.PAYMENT_ROUTINGKEY, payment);
    }


}
