package dev.joaountura.payments_project.payment.models;

import dev.joaountura.payments_project.product.models.Product;
import dev.joaountura.payments_project.receiver.models.Receiver;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@Builder
@NoArgsConstructor // construtor sem argumentos para o JPA
@AllArgsConstructor // construtor completo para o Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Builder.Default
    private UUID externalID = UUID.randomUUID();

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Receiver receiver;

    private String customer;

    private String email;

    private BigDecimal value;

    @Builder.Default
    private LocalDateTime date = LocalDateTime.now();

    @Builder.Default
    private String status = "pending";

    @ManyToMany
    @JoinTable(
            name = "payment_product",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> productList;

}
