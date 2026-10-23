package com.roman.barber_shop.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_method_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_payment_payment_method"))
    private PaymentMethod paymentMethod;

    @Column(name = "is_paid",
            nullable = false)
    private Boolean isPaid;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_payment_visit"))
    private Visit visit;
}
