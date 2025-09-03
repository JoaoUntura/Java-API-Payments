package dev.joaountura.payments_project.receiver.services;


import dev.joaountura.payments_project.receiver.mapper.ReceiverMapper;
import dev.joaountura.payments_project.receiver.models.Receiver;
import dev.joaountura.payments_project.receiver.models.ReceiverCreateDTO;

import dev.joaountura.payments_project.receiver.models.ReceiverGetDTO;
import dev.joaountura.payments_project.receiver.repository.ReceiverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReceiverServices {

    private final ReceiverMapper receiverMapper;
    private final ReceiverRepository receiverRepository;

    public ReceiverServices(ReceiverMapper receiverMapper, ReceiverRepository receiverRepository) {
        this.receiverMapper = receiverMapper;
        this.receiverRepository = receiverRepository;
    }

    public Receiver createReceiver(ReceiverCreateDTO receiverCreateDTO){

        Receiver receiver =  receiverMapper.toReceiverCreate(receiverCreateDTO);

        return receiverRepository.save(receiver);

    }

    public List<ReceiverGetDTO> getAll(){
        List<Receiver> receivers = receiverRepository.findAll();
        return receiverMapper.toReceiverGet(receivers);
    }

    public Receiver getByExternalID(UUID externalId){

        return receiverRepository.findByExternalID(externalId);

    }


}
