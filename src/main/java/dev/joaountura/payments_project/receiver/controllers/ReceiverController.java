package dev.joaountura.payments_project.receiver.controllers;

import dev.joaountura.payments_project.receiver.models.Receiver;
import dev.joaountura.payments_project.receiver.models.ReceiverCreateDTO;

import dev.joaountura.payments_project.receiver.models.ReceiverGetDTO;
import dev.joaountura.payments_project.receiver.services.ReceiverServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receiver")
public class ReceiverController {

    private final ReceiverServices receiverServices;

    public ReceiverController(ReceiverServices receiverServices) {
        this.receiverServices = receiverServices;
    }


    @PostMapping
    private ResponseEntity<Receiver> receiverController(@RequestBody ReceiverCreateDTO receiverCreateDTO){

        Receiver receiver = receiverServices.createReceiver(receiverCreateDTO);

       return ResponseEntity.status(HttpStatus.CREATED).body(receiver);


    }

    @GetMapping
    private ResponseEntity<List<ReceiverGetDTO>> receiverControllerGet(){

        List<ReceiverGetDTO> receiverGetDTOS = receiverServices.getAll();

        return ResponseEntity.status(HttpStatus.OK).body(receiverGetDTOS);


    }



}
