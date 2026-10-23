package com.roman.barber_shop.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false,
            foreignKey = @ForeignKey(name = "fk_visit_client"))
    private Client client;

    @ManyToOne
    @JoinColumn(nullable = false,
            foreignKey = @ForeignKey(name = "fk_visit_hairdresser"))
    private Hairdresser hairdresser;

    @ManyToOne
    @JoinColumn(nullable = false,
            foreignKey = @ForeignKey(name = "fk_visit_service"))
    private Service service;

    @Column(name = "start_date",
            nullable = false)
    private LocalDate startDate;

    @Column(name = "start_time",
            nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @OneToMany(mappedBy = "visit")
    private List<Payment> payments;


}
