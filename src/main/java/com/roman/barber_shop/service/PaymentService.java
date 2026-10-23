package com.roman.barber_shop.service;

import com.roman.barber_shop.api.dto.request.PaymentRequestDto;
import com.roman.barber_shop.api.dto.response.*;
import com.roman.barber_shop.api.mapper.*;
import com.roman.barber_shop.persistence.entity.*;
import com.roman.barber_shop.persistence.repository.PaymentMethodRepository;
import com.roman.barber_shop.persistence.repository.PaymentRepository;
import com.roman.barber_shop.persistence.repository.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final VisitRepository visitRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    private final PaymentMapper paymentMapper;
    private final ClientMapper clientMapper;
    private final HairdresserMapper hairdresserMapper;
    private final QualificationMapper qualificationMapper;
    private final ServiceMapper serviceMapper;
    private final VisitMapper visitMapper;
    private final PaymentMethodMapper paymentMethodMapper;

    public List<PaymentResponseDto> getAllPaymentDtos() {
        List<Payment> payments = paymentRepository.findAll();

        return payments.stream().
                map(payment -> {
                    Visit visit = payment.getVisit();

                    Client client = visit.getClient();
                    ClientResponseDto clientResponseDto = clientMapper.toDto(client);

                    Hairdresser hairdresser = visit.getHairdresser();
                    Qualification qualification = hairdresser.getQualification();

                    QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);
                    HairdresserResponseDto hairdresserResponseDto = hairdresserMapper.toDto(hairdresser,
                            qualificationResponseDto);

                    Service service = visit.getService();
                    ServiceResponseDto serviceResponseDto = serviceMapper.toDto(service);

                    PaymentMethod paymentMethod = payment.getPaymentMethod();

                    PaymentMethodResponseDto paymentMethodResponseDto = paymentMethodMapper.toDto(paymentMethod);

                    VisitResponseDto visitResponseDto = visitMapper.toDto(visit,
                            clientResponseDto,
                            hairdresserResponseDto,
                            serviceResponseDto);

                    return paymentMapper.toDto(payment, visitResponseDto, paymentMethodResponseDto);
                }).toList();
    }

    public PaymentResponseDto getPaymentDtoById(long id) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no payment with id " + id));

        Visit visit = existingPayment.getVisit();

        Client client = visit.getClient();
        ClientResponseDto clientResponseDto = clientMapper.toDto(client);


        Hairdresser hairdresser = visit.getHairdresser();
        Qualification qualification = hairdresser.getQualification();

        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);
        HairdresserResponseDto hairdresserResponseDto = hairdresserMapper.toDto(hairdresser, qualificationResponseDto);

        Service service = visit.getService();
        ServiceResponseDto serviceResponseDto = serviceMapper.toDto(service);

        PaymentMethod paymentMethod = existingPayment.getPaymentMethod();

        PaymentMethodResponseDto paymentMethodResponseDto = paymentMethodMapper.toDto(paymentMethod);

        VisitResponseDto visitResponseDto = visitMapper.toDto(visit,
                clientResponseDto,
                hairdresserResponseDto,
                serviceResponseDto);

        return paymentMapper.toDto(existingPayment, visitResponseDto, paymentMethodResponseDto);
    }

    public PaymentResponseDto createPayment(PaymentRequestDto request) {
        Visit visit = visitRepository.findById(request.getVisitId())
                .orElseThrow(() -> new EntityNotFoundException("There is no visit with id " + request));
        PaymentMethod paymentMethod = paymentMethodRepository.findById(request.getPaymentMethodId())
                .orElseThrow(() -> new EntityNotFoundException("There is no payment method with id " + paymentMethodRepository));

        Payment payment = paymentMapper.toEntity(request, visit, paymentMethod);

        Payment createdPayment = paymentRepository.save(payment);

        PaymentMethodResponseDto paymentMethodResponseDto = paymentMethodMapper.toDto(paymentMethod);

        Client client = visit.getClient();
        ClientResponseDto clientResponseDto = clientMapper.toDto(client);

        Hairdresser hairdresser = visit.getHairdresser();
        Qualification qualification = hairdresser.getQualification();

        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);
        HairdresserResponseDto hairdresserResponseDto = hairdresserMapper.toDto(hairdresser, qualificationResponseDto);

        Service service = visit.getService();
        ServiceResponseDto serviceResponseDto = serviceMapper.toDto(service);

        VisitResponseDto visitResponseDto = visitMapper.toDto(visit,
                clientResponseDto,
                hairdresserResponseDto,
                serviceResponseDto);

        return paymentMapper.toDto(createdPayment, visitResponseDto, paymentMethodResponseDto);
    }

    public PaymentResponseDto updatePayment(PaymentRequestDto request, long id) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no payment with id " + id));
        PaymentMethod paymentMethod = paymentMethodRepository.findById(request.getPaymentMethodId())
                .orElseThrow(() -> new EntityNotFoundException("There is no payment method with id " + request.getPaymentMethodId()));
        Visit visit = visitRepository.findById(request.getVisitId())
                .orElseThrow(() -> new EntityNotFoundException("There is no visit with id " + request.getVisitId()));


        paymentMapper.updateEntityFromDto(existingPayment, request, visit, paymentMethod);

        Payment updatedPayment = paymentRepository.save(existingPayment);
        PaymentMethodResponseDto paymentMethodResponseDto = paymentMethodMapper.toDto(paymentMethod);

        Client client = visit.getClient();
        ClientResponseDto clientResponseDto = clientMapper.toDto(client);

        Hairdresser hairdresser = visit.getHairdresser();

        Qualification qualification = hairdresser.getQualification();
        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);

        HairdresserResponseDto hairdresserResponseDto = hairdresserMapper.toDto(hairdresser, qualificationResponseDto);

        Service service = visit.getService();
        ServiceResponseDto serviceResponseDto = serviceMapper.toDto(service);

        VisitResponseDto visitResponseDto = visitMapper.toDto(visit,
                clientResponseDto,
                hairdresserResponseDto,
                serviceResponseDto);

        return paymentMapper.toDto(updatedPayment, visitResponseDto, paymentMethodResponseDto);
    }

    public PaymentResponseDto partialUpdatePayment(PaymentRequestDto request, long id) {
        Payment existingPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no payment with id " + id));

        PaymentMethod paymentMethod = null;
        if(request.getPaymentMethodId() != null)
            paymentMethod = paymentMethodRepository.findById(request.getPaymentMethodId())
                    .orElseThrow(() -> new EntityNotFoundException("There is no payment method with id " + request.getPaymentMethodId()));

        Visit visit = null;
        if(request.getVisitId() != null)
            visit = visitRepository.findById(request.getVisitId())
                   .orElseThrow(() -> new EntityNotFoundException("There is no visit with id " + request.getVisitId()));
        paymentMapper.partialUpdateEntityFromDto(existingPayment, request, visit, paymentMethod);

        Payment partialUpdatedPayment = paymentRepository.save(existingPayment);

        PaymentMethodResponseDto paymentMethodResponseDto = paymentMethodMapper.toDto(partialUpdatedPayment.getPaymentMethod());

        visit = partialUpdatedPayment.getVisit();

        Client client = visit.getClient();
        ClientResponseDto clientResponseDto = clientMapper.toDto(client);

        Hairdresser hairdresser = visit.getHairdresser();

        Qualification qualification = hairdresser.getQualification();
        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);

        HairdresserResponseDto hairdresserResponseDto = hairdresserMapper.toDto(hairdresser, qualificationResponseDto);

        Service service = visit.getService();
        ServiceResponseDto serviceResponseDto = serviceMapper.toDto(service);

        VisitResponseDto visitResponseDto = visitMapper.toDto(visit,
                clientResponseDto,
                hairdresserResponseDto,
                serviceResponseDto);
        return paymentMapper.toDto(partialUpdatedPayment, visitResponseDto, paymentMethodResponseDto);
    }

    public void deletePayment(long id) {
        Payment foundPayment = paymentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no payment with id" + id));
        paymentRepository.delete(foundPayment);
    }
}