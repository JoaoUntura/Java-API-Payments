package dev.joaountura.payments_project.receiver.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ReceiverCreateDTO(

        @NotBlank String name,

        @NotBlank  String city,

        @NotBlank String pixKey
) {
}
