package com.roman.barber_shop.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClientResponseDto {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
