package com.roman.barber_shop.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ServiceRequestDto {
    private String name;
    private Integer durationMin;
    private Double price;
}
