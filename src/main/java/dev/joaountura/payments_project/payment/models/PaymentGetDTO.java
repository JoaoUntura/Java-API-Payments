package dev.joaountura.payments_project.payment.models;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PaymentGetDTO(
  Long id,
  String custumer,
  String email,
  BigDecimal value,
  LocalDateTime date,
  String status
){
}
