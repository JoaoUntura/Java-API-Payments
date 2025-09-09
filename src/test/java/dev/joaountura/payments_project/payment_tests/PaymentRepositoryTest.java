package dev.joaountura.payments_project.payment_tests;

import dev.joaountura.payments_project.Mocks;
import dev.joaountura.payments_project.payment.PaymentRepository;
import dev.joaountura.payments_project.payment.models.Payment;
import dev.joaountura.payments_project.receiver.models.Receiver;
import dev.joaountura.payments_project.receiver.repository.ReceiverRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class PaymentRepositoryTest {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ReceiverRepository receiverRepository;

    @Test
    @Transactional
    void shouldSaveAndReturnPayment(){

        Payment paymentMock = Mocks.paymentMock();
        Receiver receiverMock = Mocks.receiverMock();
        Receiver receiverDataTest = receiverRepository.save(receiverMock);
        paymentMock.setReceiver(receiverDataTest);
        paymentRepository.save(paymentMock);
        Optional<Payment> found = paymentRepository.findById(paymentMock.getId());

        Assertions.assertTrue(found.isPresent());

        Payment finalPayment = found.get();

        Assertions.assertEquals(finalPayment.getId(), paymentMock.getId(), "Payment Found");
        Assertions.assertEquals(finalPayment.getExternalID(), paymentMock.getExternalID());
        Assertions.assertEquals(finalPayment.getValue(), paymentMock.getValue());


    }

}
