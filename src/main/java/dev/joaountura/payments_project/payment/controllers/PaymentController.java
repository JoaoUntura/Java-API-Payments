package dev.joaountura.payments_project.payment.controllers;

import com.google.zxing.WriterException;
import dev.joaountura.payments_project.payment.models.Payment;
import dev.joaountura.payments_project.payment.models.PaymentCreateDTO;
import dev.joaountura.payments_project.payment.models.PaymentGetDTO;
import dev.joaountura.payments_project.payment.services.PaymentServices;
import dev.joaountura.payments_project.payment.services.PixService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Payments", description = "Payments related operations")
public class PaymentController {

    private final PaymentServices paymentServices;


    public PaymentController(PaymentServices paymentServices, PixService pixService) {
        this.paymentServices = paymentServices;

    }

    @PostMapping
    @Operation(summary = "Create new payment ")
    private ResponseEntity<byte[]> createPayment(@RequestBody PaymentCreateDTO paymentCreateDTO) throws IOException, WriterException {
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.IMAGE_PNG).body(paymentServices.createPayment(paymentCreateDTO));
    }

    @GetMapping
    @Operation(summary = "Get all payments")
    private ResponseEntity<List<PaymentGetDTO>> getAllPayments(){
        return ResponseEntity.status(HttpStatus.OK).body(paymentServices.getAllPayments());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete payment by id")
    private ResponseEntity<String> deletePayment(@PathVariable Long id) throws EntityNotFoundException {

        paymentServices.deletePayment(id);

        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }



}
