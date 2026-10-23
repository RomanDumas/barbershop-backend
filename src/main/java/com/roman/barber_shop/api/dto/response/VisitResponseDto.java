package com.roman.barber_shop.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VisitResponseDto {
    private long id;
    private ClientResponseDto client;
    private HairdresserResponseDto hairdresser;
    private ServiceResponseDto service;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;
}