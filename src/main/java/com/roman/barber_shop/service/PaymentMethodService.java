package com.roman.barber_shop.service;

import com.roman.barber_shop.api.dto.request.PaymentMethodRequestDto;
import com.roman.barber_shop.api.dto.response.PaymentMethodResponseDto;
import com.roman.barber_shop.api.mapper.PaymentMethodMapper;
import com.roman.barber_shop.persistence.entity.PaymentMethod;
import com.roman.barber_shop.persistence.repository.PaymentMethodRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;
    private final PaymentMethodMapper paymentMethodMapper;

    public List<PaymentMethodResponseDto> getAllPaymentMethodDtos() {
        List<PaymentMethod> paymentMethods = paymentMethodRepository.findAll();

        return paymentMethods.stream()
                .map(paymentMethodMapper::toDto).toList();
    }

    public PaymentMethodResponseDto getPaymentMethodDtoById(long id) {
        PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no payment method with id " + id));

        return paymentMethodMapper.toDto(existingPaymentMethod);
    }

    public PaymentMethodResponseDto createPaymentMethod(PaymentMethodRequestDto request) {
        PaymentMethod paymentMethod = paymentMethodMapper.toEntity(request);
        PaymentMethod savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        return paymentMethodMapper.toDto(savedPaymentMethod);
    }

    public PaymentMethodResponseDto updatePaymentMethod(PaymentMethodRequestDto request, long id) {
        PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no payment method with id " + id));

        paymentMethodMapper.updateEntityFromDto(request, existingPaymentMethod);

        PaymentMethod updatedPaymentMethod = paymentMethodRepository.save(existingPaymentMethod);

        return paymentMethodMapper.toDto(updatedPaymentMethod);
    }

    public PaymentMethodResponseDto partialUpdatePaymentMethod(PaymentMethodRequestDto request, long id) {
        PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no payment method with id " + id));

        paymentMethodMapper.partialUpdateEntityFromDto(request, existingPaymentMethod);

        PaymentMethod partialUpdatedPaymentMethod = paymentMethodRepository.save(existingPaymentMethod);

        return paymentMethodMapper.toDto(partialUpdatedPaymentMethod);
    }


    public void deletePaymentMethod(long id) {
        PaymentMethod foundPaymentMethod = paymentMethodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no payment method with id " + id));

        paymentMethodRepository.delete(foundPaymentMethod);
    }

}
