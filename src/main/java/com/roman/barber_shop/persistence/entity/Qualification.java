package com.roman.barber_shop.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Qualification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,
            unique = true)
    private String name;

    @OneToMany(mappedBy = "qualification")
    private List<Hairdresser> hairdressers;

    @Override
    public String toString() {
        return "Qualification{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
