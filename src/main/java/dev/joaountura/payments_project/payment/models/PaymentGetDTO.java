package dev.joaountura.payments_project.payment.models;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PaymentGetDTO(
  UUID id,
  String custumer,
  String email,
  BigDecimal value,
  LocalDateTime date,
  UUID receiver,
  String status
){
}
