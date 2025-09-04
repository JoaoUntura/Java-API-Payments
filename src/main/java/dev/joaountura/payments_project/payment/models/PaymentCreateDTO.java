package dev.joaountura.payments_project.payment.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


import java.util.List;
import java.util.UUID;

public record PaymentCreateDTO (
        @NotBlank String customer,
        @NotBlank @Email String email,
        @NotEmpty List<Long> productIdList,
        @NotEmpty UUID receiverID
)
{
}
