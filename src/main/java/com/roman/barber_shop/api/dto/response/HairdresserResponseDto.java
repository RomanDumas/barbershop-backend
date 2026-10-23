package com.roman.barber_shop.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HairdresserResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate startOfWorking;
    private QualificationResponseDto qualification;
}