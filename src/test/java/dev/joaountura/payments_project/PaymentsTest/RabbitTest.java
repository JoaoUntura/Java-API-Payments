package dev.joaountura.payments_project.PaymentsTest;

import dev.joaountura.payments_project.payment.models.PaymentRabbitDTO;
import dev.joaountura.payments_project.payment.services.PaymentProducer;

import dev.joaountura.payments_project.payment.models.Payment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RabbitTest {

    @Autowired
    private PaymentProducer paymentProducer;


    @Test
    void testSendPayment(PaymentRabbitDTO payment) {
        paymentProducer.sendPayment(payment);
        System.out.println("Mensagem enviada para a fila!");
    }


}
