package com.roman.barber_shop.api.mapper;

import com.roman.barber_shop.api.dto.request.PaymentMethodRequestDto;
import com.roman.barber_shop.api.dto.response.PaymentMethodResponseDto;
import com.roman.barber_shop.persistence.entity.PaymentMethod;
import org.springframework.stereotype.Component;

@Component
public class PaymentMethodMapper {
    public PaymentMethodResponseDto toDto(PaymentMethod paymentMethod) {
        return PaymentMethodResponseDto.builder()
                .id(paymentMethod.getId())
                .name(paymentMethod.getName())
                .build();
    }

    public PaymentMethod toEntity(PaymentMethodRequestDto paymentMethodRequestDto) {
        return PaymentMethod.builder()
                .name(paymentMethodRequestDto.getName())
                .build();
    }

    public void updateEntityFromDto(PaymentMethodRequestDto paymentMethodRequestDto, PaymentMethod existingPaymentMethod) {
        existingPaymentMethod.setName(paymentMethodRequestDto.getName());
    }
    public void partialUpdateEntityFromDto(PaymentMethodRequestDto paymentMethodRequestDto, PaymentMethod existingPaymentMethod) {
        if(paymentMethodRequestDto.getName() != null)
            existingPaymentMethod.setName(paymentMethodRequestDto.getName());
    }
}
