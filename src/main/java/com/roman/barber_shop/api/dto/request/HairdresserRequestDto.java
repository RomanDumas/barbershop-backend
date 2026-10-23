package com.roman.barber_shop.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HairdresserRequestDto {
    private String firstName;
    private String lastName;
    private LocalDate startOfWorking;
    private Long qualificationId;
}
