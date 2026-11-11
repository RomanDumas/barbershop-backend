package com.roman.barber_shop.api.controller;

import com.roman.barber_shop.api.dto.request.PaymentMethodRequestDto;
import com.roman.barber_shop.api.dto.response.PaymentMethodResponseDto;
import com.roman.barber_shop.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/paymentmethods")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<List<PaymentMethodResponseDto>> getAllPaymentMethodDtos() {
        List<PaymentMethodResponseDto> paymentMethodResponseDtos = paymentMethodService.getAllPaymentMethodDtos();
        return ResponseEntity.status(HttpStatus.OK).body(paymentMethodResponseDtos);
    }

    @GetMapping("/{paymentMethodId}")
    public ResponseEntity<PaymentMethodResponseDto> getPaymentMethodDtoById(@PathVariable long id) {
        PaymentMethodResponseDto foundPaymentMethod = paymentMethodService.getPaymentMethodDtoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundPaymentMethod);
    }

    @PostMapping
    public ResponseEntity<PaymentMethodResponseDto> createPaymentMethod(@RequestBody PaymentMethodRequestDto request) {
        PaymentMethodResponseDto createdPaymentMethod = paymentMethodService.createPaymentMethod(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPaymentMethod);
    }

    @PutMapping("/{paymentMethodId}")
    public ResponseEntity<PaymentMethodResponseDto> updatePaymentMethod(@RequestBody PaymentMethodRequestDto request,
                                                                        @PathVariable long id) {
        PaymentMethodResponseDto updatedPaymentMethod = paymentMethodService.updatePaymentMethod(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPaymentMethod);
    }

    @PatchMapping("/{paymentMethodId}")
    public ResponseEntity<PaymentMethodResponseDto> partialUpdatePaymentMethod(@RequestBody PaymentMethodRequestDto request,
                                                                        @PathVariable long id) {
        PaymentMethodResponseDto partialUpdatedPaymentMethod = paymentMethodService.partialUpdatePaymentMethod(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(partialUpdatedPaymentMethod);
    }

    @DeleteMapping("/{paymentMethodId}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable long id) {
        paymentMethodService.deletePaymentMethod(id);
        return ResponseEntity.ok().build();
    }
}
