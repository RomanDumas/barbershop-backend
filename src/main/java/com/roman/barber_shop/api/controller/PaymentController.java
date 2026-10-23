package com.roman.barber_shop.api.controller;

import com.roman.barber_shop.api.dto.request.PaymentRequestDto;
import com.roman.barber_shop.api.dto.response.PaymentResponseDto;
import com.roman.barber_shop.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping
    public ResponseEntity<List<PaymentResponseDto>> getAllPaymentDtos() {
        List<PaymentResponseDto> paymentResponseDtos = paymentService.getAllPaymentDtos();
        return ResponseEntity.status(HttpStatus.OK).body(paymentResponseDtos);
    }

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDto> getPaymentDtoById(@PathVariable long id) {
        PaymentResponseDto foundPayment = paymentService.getPaymentDtoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundPayment);
    }

    @PostMapping
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto request) {
        PaymentResponseDto createdPayment = paymentService.createPayment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPayment);
    }

    @PutMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDto> updatePayment(@RequestBody PaymentRequestDto request,
                                                            @PathVariable long id) {
        PaymentResponseDto updatedPayment = paymentService.updatePayment(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedPayment);
    }

    @PatchMapping("/{paymentId}")
    public ResponseEntity<PaymentResponseDto> partialUpdatePayment(@RequestBody PaymentRequestDto request,
                                                            @PathVariable long id) {
        PaymentResponseDto partialUpdatedPayment = paymentService.partialUpdatePayment(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(partialUpdatedPayment);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok().build();
    }
}
