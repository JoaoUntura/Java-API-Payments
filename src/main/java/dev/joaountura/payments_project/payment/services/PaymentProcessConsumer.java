package dev.joaountura.payments_project.payment.services;

import dev.joaountura.payments_project.config.RabbitConfig;
import dev.joaountura.payments_project.payment.models.Payment;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcessConsumer {

    @RabbitListener(queues = RabbitConfig.PAYMENT_QUEUE)
    public void processPayment(Payment Payment){

            System.out.println(Payment.getCustumer());
    }

}
