package dev.joaountura.payments_project.payment.controllers;

import dev.joaountura.payments_project.payment.models.Payment;
import dev.joaountura.payments_project.payment.models.PaymentCreateDTO;
import dev.joaountura.payments_project.payment.models.PaymentGetDTO;
import dev.joaountura.payments_project.payment.services.PaymentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentServices paymentServices;

    public PaymentController(PaymentServices paymentServices) {
        this.paymentServices = paymentServices;
    }

    @PostMapping
    private ResponseEntity<Payment> createPayment(@RequestBody PaymentCreateDTO paymentCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentServices.createPayment(paymentCreateDTO));
    }

    @GetMapping
    private ResponseEntity<List<PaymentGetDTO>> getAllPayments(){
        return ResponseEntity.status(HttpStatus.OK).body(paymentServices.getAllPayments());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deletePayment(@PathVariable Long id){

        paymentServices.deletePayment(id);

        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }


}
