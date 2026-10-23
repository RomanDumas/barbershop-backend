package com.roman.barber_shop.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ClientRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
