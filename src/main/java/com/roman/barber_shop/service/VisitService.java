package com.roman.barber_shop.service;

import com.roman.barber_shop.api.dto.request.VisitRequestDto;
import com.roman.barber_shop.api.dto.response.*;
import com.roman.barber_shop.api.mapper.*;
import com.roman.barber_shop.persistence.entity.*;
import com.roman.barber_shop.persistence.repository.ClientRepository;
import com.roman.barber_shop.persistence.repository.HairdresserRepository;
import com.roman.barber_shop.persistence.repository.ServiceRepository;
import com.roman.barber_shop.persistence.repository.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class VisitService {
    private final VisitRepository visitRepository;
    private final HairdresserRepository hairdresserRepository;
    private final ClientRepository clientRepository;
    private final ServiceRepository serviceRepository;
    private final VisitMapper visitMapper;
    private final ClientMapper clientMapper;
    private final HairdresserMapper hairdresserMapper;
    private final QualificationMapper qualificationMapper;
    private final ServiceMapper serviceMapper;


    public List<VisitResponseDto> getAllVisitDtos() {
        List<Visit> visits = visitRepository.findAll();

        return visits.stream()
                .map(visit -> {
                    Client client = visit.getClient();
                    ClientResponseDto clientResponseDto = clientMapper.toDto(client);

                    Hairdresser hairdresser = visit.getHairdresser();
                    Qualification qualification = hairdresser.getQualification();

                    QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);
                    HairdresserResponseDto hairdresserResponseDto = hairdresserMapper.toDto(hairdresser, qualificationResponseDto);

                    Service service = visit.getService();
                    ServiceResponseDto serviceResponseDto = serviceMapper.toDto(service);

                    return visitMapper.toDto(visit,
                            clientResponseDto,
                            hairdresserResponseDto,
                            serviceResponseDto);
                }).toList();
    }

    public VisitResponseDto getVisitDtoById(long id) {
        Visit existingVisit = visitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no visit with id " + id));

        Client client = existingVisit.getClient();
        ClientResponseDto clientResponseDto = clientMapper.toDto(client);

        Hairdresser hairdresser = existingVisit.getHairdresser();
        Qualification qualification = hairdresser.getQualification();

        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);
        HairdresserResponseDto hairdresserResponseDto = hairdresserMapper.toDto(hairdresser, qualificationResponseDto);

        Service service = existingVisit.getService();
        ServiceResponseDto serviceResponseDto = serviceMapper.toDto(service);

        return visitMapper.toDto(existingVisit,
                clientResponseDto,
                hairdresserResponseDto,
                serviceResponseDto);
    }

    public VisitResponseDto createVisit(VisitRequestDto request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("There is no client with id " + request.getClientId()));
        Hairdresser hairdresser = hairdresserRepository.findById(request.getHairdresserId())
                .orElseThrow(() -> new EntityNotFoundException("There is no hairdresser with id " + request.getHairdresserId()));
        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("There is no service with id " + request.getServiceId()));

        Visit newVisit = visitMapper.toEntity(request, client, hairdresser, service);

        Visit savedVisit = visitRepository.save(newVisit);

        ClientResponseDto clientResponseDto = clientMapper.toDto(client);

        Qualification qualification = hairdresser.getQualification();

        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);
        HairdresserResponseDto hairdresserResponseDto = hairdresserMapper.toDto(hairdresser, qualificationResponseDto);

        ServiceResponseDto serviceResponseDto = serviceMapper.toDto(service);

        return visitMapper.toDto(savedVisit,
                clientResponseDto,
                hairdresserResponseDto,
                serviceResponseDto);
    }

    public VisitResponseDto updateVisitDto(VisitRequestDto request, long id) {
        Visit existingVisit = visitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no visit with id " + id));

        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new EntityNotFoundException("There is no client with id " + request.getClientId()));
        Hairdresser hairdresser = hairdresserRepository.findById(request.getHairdresserId())
                .orElseThrow(() -> new EntityNotFoundException("There is no hairdresser with id " + request.getHairdresserId()));
        Service service = serviceRepository.findById(request.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("There is no service with id " + request.getServiceId()));

        visitMapper.updateEntityFromDto(existingVisit,
                request,
                client,
                hairdresser,
                service);

        Visit updatedVisit = visitRepository.save(existingVisit);

        ClientResponseDto clientResponseDto = clientMapper.toDto(client);

        Qualification qualification = hairdresser.getQualification();

        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);
        HairdresserResponseDto hairdresserResponseDto = hairdresserMapper.toDto(hairdresser, qualificationResponseDto);

        ServiceResponseDto serviceResponseDto = serviceMapper.toDto(service);

        return visitMapper.toDto(updatedVisit,
                clientResponseDto,
                hairdresserResponseDto,
                serviceResponseDto);
    }

    public VisitResponseDto partialUpdateVisitDto(VisitRequestDto request, long id) {
        Visit existingVisit = visitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no visit with id " + id));

        Client client = null;
        if(request.getClientId() != null)
            client = clientRepository.findById(request.getClientId())
                    .orElseThrow(() -> new EntityNotFoundException("There is no client with id " + request.getClientId()));

        Hairdresser hairdresser = null;
        Qualification qualification = null;
        if(request.getHairdresserId() != null) {
            hairdresser = hairdresserRepository.findById(request.getHairdresserId())
                    .orElseThrow(() -> new EntityNotFoundException("There is no hairdresser with id " + request.getHairdresserId()));
            qualification = hairdresser.getQualification();
        }
        Service service = null;
        if(request.getServiceId() != null)
            service = serviceRepository.findById(request.getServiceId())
                    .orElseThrow(() -> new EntityNotFoundException("There is no service with id " + request.getServiceId()));

        visitMapper.partialUpdateEntityFromDto(existingVisit,
                request,
                client,
                hairdresser,
                service);

        Visit partialUpdatedVisit = visitRepository.save(existingVisit);

        ClientResponseDto clientResponseDto = clientMapper.toDto(partialUpdatedVisit.getClient());


        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(partialUpdatedVisit.getHairdresser().getQualification());
        HairdresserResponseDto hairdresserResponseDto = hairdresserMapper.toDto(partialUpdatedVisit.getHairdresser(), qualificationResponseDto);

        ServiceResponseDto serviceResponseDto = serviceMapper.toDto(partialUpdatedVisit.getService());

        return visitMapper.toDto(partialUpdatedVisit,
                clientResponseDto,
                hairdresserResponseDto,
                serviceResponseDto);
    }

    public void deleteVisit(long id) {
        Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no visit with id " + id));
        visitRepository.delete(visit);
    }
}
