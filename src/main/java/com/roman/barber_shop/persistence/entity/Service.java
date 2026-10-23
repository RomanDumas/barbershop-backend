package com.roman.barber_shop.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,
            unique = true)
    private String name;

    @Column(name = "duration_min",
            nullable = false)
    private Integer durationMin;

    @Column(nullable = false)
    private Double price;

    @OneToMany(mappedBy = "service")
    private List<Visit> visits;

    @Override
    public String toString() {
        return "Service{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
