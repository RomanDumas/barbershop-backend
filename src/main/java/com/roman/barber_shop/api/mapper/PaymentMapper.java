package com.roman.barber_shop.api.mapper;

import com.roman.barber_shop.api.dto.request.PaymentRequestDto;
import com.roman.barber_shop.api.dto.response.PaymentMethodResponseDto;
import com.roman.barber_shop.api.dto.response.PaymentResponseDto;
import com.roman.barber_shop.api.dto.response.VisitResponseDto;
import com.roman.barber_shop.persistence.entity.Payment;
import com.roman.barber_shop.persistence.entity.PaymentMethod;
import com.roman.barber_shop.persistence.entity.Visit;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public PaymentResponseDto toDto(Payment payment,
                                    VisitResponseDto visitResponseDto,
                                    PaymentMethodResponseDto paymentMethodResponseDto) {
        return PaymentResponseDto.builder()
                .id(payment.getId())
                .visit(visitResponseDto)
                .paymentMethod(paymentMethodResponseDto)
                .isPaid(payment.getIsPaid())
                .build();
    }

    public Payment toEntity(PaymentRequestDto paymentRequestDto, Visit visit, PaymentMethod paymentMethod) {
        return Payment.builder()
                .paymentMethod(paymentMethod)
                .isPaid(paymentRequestDto.getIsPaid())
                .visit(visit)
                .build();
    }

    public void updateEntityFromDto(Payment existingPayment, PaymentRequestDto paymentRequestDto, Visit visit, PaymentMethod paymentMethod) {
        existingPayment.setPaymentMethod(paymentMethod);
        existingPayment.setVisit(visit);
        existingPayment.setIsPaid(paymentRequestDto.getIsPaid());
    }

    public void partialUpdateEntityFromDto(Payment existingPayment, PaymentRequestDto paymentRequestDto, Visit visit, PaymentMethod paymentMethod) {
        if(paymentMethod != null)
            existingPayment.setPaymentMethod(paymentMethod);
        if(visit != null)
            existingPayment.setVisit(visit);
        if(paymentRequestDto.getIsPaid() != null)
            existingPayment.setIsPaid(paymentRequestDto.getIsPaid());
    }
}
