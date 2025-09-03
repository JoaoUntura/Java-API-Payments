package dev.joaountura.payments_project.payment.models;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record PaymentRabbitDTO(
        UUID id,
        String custumer,
        String email,
        BigDecimal value,
        LocalDateTime date,
        UUID receiver,
        String status,
        List<String> productNames
){
}
