package dev.joaountura.payments_project.receiver.mapper;



import dev.joaountura.payments_project.receiver.models.Receiver;
import dev.joaountura.payments_project.receiver.models.ReceiverCreateDTO;
import dev.joaountura.payments_project.receiver.models.ReceiverGetDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReceiverMapper {

    public  Receiver toReceiverCreate(ReceiverCreateDTO receiverCreateDTO){

        return Receiver.builder().name(receiverCreateDTO.name()).city(receiverCreateDTO.city()).pixKey(receiverCreateDTO.pixKey()).build();

    }


    public  List<ReceiverGetDTO> toReceiverGet(List<Receiver> receivers){

       return receivers.stream().map((Receiver receiver) ->
               ReceiverGetDTO
                       .builder()
                       .id(receiver.getExternalID())
                       .city(receiver.getCity())
                       .name(receiver.getName())
                       .pixKey(receiver.getPixKey())
                       .build()).toList();


    }



}
