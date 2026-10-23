package com.roman.barber_shop.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PaymentResponseDto {
    private long id;
    private PaymentMethodResponseDto paymentMethod;
    private Boolean isPaid;
    private VisitResponseDto visit;
}