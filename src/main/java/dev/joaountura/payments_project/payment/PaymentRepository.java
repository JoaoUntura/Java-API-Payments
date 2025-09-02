package dev.joaountura.payments_project.payment;

import dev.joaountura.payments_project.payment.models.Payment;

import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface PaymentRepository extends JpaRepository<Payment, Long > {



}
