package com.roman.barber_shop.api.dto.request;

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
public class VisitRequestDto {
    private Long clientId;
    private Long hairdresserId;
    private Long serviceId;
    private LocalDate startDate;
    private LocalTime startTime;
}
