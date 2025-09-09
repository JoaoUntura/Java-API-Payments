package dev.joaountura.payments_project.receiver.models;

import dev.joaountura.payments_project.payment.models.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor // construtor sem argumentos para o JPA
@AllArgsConstructor // construtor completo para o Builder
public class Receiver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Builder.Default
    private UUID externalID = UUID.randomUUID();

    private String name;

    private String city;

    private String pixKey;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> paymentList;

}
