package com.roman.barber_shop.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ServiceResponseDto {
    private long id;
    private String name;
    private Integer durationMin;
    private Double price;
}
