package dev.joaountura.payments_project.payment.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


import java.util.List;

public record PaymentCreateDTO (
        @NotBlank String custumer,
        @NotBlank String email,
        @NotEmpty List<Long> productIdList
)
{
}
