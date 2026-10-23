package com.roman.barber_shop.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hairdresser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name",
            nullable = false)
    private String firstName;

    @Column(name = "last_name",
            nullable = false)
    private String lastName;

    @Column(name = "start_of_working_date",
            nullable = false)
    private LocalDate startOfWorking;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_hairdresser_qualification"))
    private Qualification qualification;

    @OneToMany(mappedBy = "hairdresser")
    private List<Visit> visits;

    @Override
    public String toString() {
        return "Hairdresser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", startOfWorking=" + startOfWorking +
                ", qualification=" + qualification +
                '}';
    }

}
