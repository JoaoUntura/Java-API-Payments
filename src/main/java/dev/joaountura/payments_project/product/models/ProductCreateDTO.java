package dev.joaountura.payments_project.product.models;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductCreateDTO(
        @Positive @NotBlank BigDecimal value,
        @NotBlank String name
) {
}
