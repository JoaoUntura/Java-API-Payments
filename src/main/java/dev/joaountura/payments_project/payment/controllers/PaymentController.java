package dev.joaountura.payments_project.payment.controllers;

import com.google.zxing.WriterException;
import dev.joaountura.payments_project.payment.models.Payment;
import dev.joaountura.payments_project.payment.models.PaymentCreateDTO;
import dev.joaountura.payments_project.payment.models.PaymentGetDTO;
import dev.joaountura.payments_project.payment.services.PaymentServices;
import dev.joaountura.payments_project.payment.services.PixService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentServices paymentServices;
    private final PixService pixService;

    public PaymentController(PaymentServices paymentServices, PixService pixService) {
        this.paymentServices = paymentServices;
        this.pixService = pixService;
    }

    @PostMapping
    private ResponseEntity<byte[]> createPayment(@RequestBody PaymentCreateDTO paymentCreateDTO) throws IOException, WriterException {
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.IMAGE_PNG).body(paymentServices.createPayment(paymentCreateDTO));
    }

    @GetMapping
    private ResponseEntity<List<PaymentGetDTO>> getAllPayments(){
        return ResponseEntity.status(HttpStatus.OK).body(paymentServices.getAllPayments());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<String> deletePayment(@PathVariable Long id) throws EntityNotFoundException {

        paymentServices.deletePayment(id);

        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }



}
