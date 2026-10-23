package com.roman.barber_shop.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false,
            unique = true)
    private String name;

    @OneToMany(mappedBy = "paymentMethod")
    private List<Payment> payments;

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "id=" + id +
                ", methodName='" + name + '\'' +
                '}';
    }
}
