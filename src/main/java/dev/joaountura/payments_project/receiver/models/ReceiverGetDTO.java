package dev.joaountura.payments_project.receiver.models;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ReceiverGetDTO(
        UUID id,
        String name,
        String city,
        String pixKey

) {
}
