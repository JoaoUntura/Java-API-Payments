package dev.joaountura.payments_project.payment.services;

import dev.joaountura.payments_project.config.RabbitConfig;
import dev.joaountura.payments_project.payment.models.Payment;
import dev.joaountura.payments_project.payment.models.PaymentRabbitDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentProcessConsumer {

    @RabbitListener(queues = RabbitConfig.PAYMENT_QUEUE)
    public void processPayment(PaymentRabbitDTO paymentRabbitDTO){

            System.out.println(paymentRabbitDTO);
    }

}
