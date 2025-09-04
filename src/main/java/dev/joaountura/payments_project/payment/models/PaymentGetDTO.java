package dev.joaountura.payments_project.payment.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PaymentGetDTO implements Serializable {
    private UUID id;
    private String customer;
    private String email;
    private BigDecimal value;
    private LocalDateTime date;
    private UUID receiver;
    private String status;
}
