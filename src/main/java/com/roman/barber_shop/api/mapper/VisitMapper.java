package com.roman.barber_shop.api.mapper;

import com.roman.barber_shop.api.dto.request.VisitRequestDto;
import com.roman.barber_shop.api.dto.response.ClientResponseDto;
import com.roman.barber_shop.api.dto.response.HairdresserResponseDto;
import com.roman.barber_shop.api.dto.response.ServiceResponseDto;
import com.roman.barber_shop.api.dto.response.VisitResponseDto;
import com.roman.barber_shop.persistence.entity.Client;
import com.roman.barber_shop.persistence.entity.Hairdresser;
import com.roman.barber_shop.persistence.entity.Service;
import com.roman.barber_shop.persistence.entity.Visit;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class VisitMapper {
    public VisitResponseDto toDto(Visit visit,
                                  ClientResponseDto clientResponseDto,
                                  HairdresserResponseDto hairdresserResponseDto,
                                  ServiceResponseDto serviceResponseDto) {
        LocalTime startTime = visit.getStartTime();

        return VisitResponseDto.builder()
                .id(visit.getId())
                .client(clientResponseDto)
                .hairdresser(hairdresserResponseDto)
                .service(serviceResponseDto)
                .startDate(visit.getStartDate())
                .startTime(startTime)
                .endTime(startTime.plusMinutes(serviceResponseDto.getDurationMin()))
                .build();
    }

    public Visit toEntity(VisitRequestDto visitRequestDto,
                          Client client,
                          Hairdresser hairdresser,
                          Service service) {
        LocalTime startTime = visitRequestDto.getStartTime();
        return Visit.builder()
                .client(client)
                .hairdresser(hairdresser)
                .service(service)
                .startDate(visitRequestDto.getStartDate())
                .startTime(startTime)
                .endTime(startTime.plusMinutes(service.getDurationMin()))
                .build();
    }

    public void updateEntityFromDto(Visit existingVisit,
                                    VisitRequestDto visitRequestDto,
                                    Client client,
                                    Hairdresser hairdresser,
                                    Service service) {
        LocalTime startTime = visitRequestDto.getStartTime();
        existingVisit.setClient(client);
        existingVisit.setHairdresser(hairdresser);
        existingVisit.setService(service);
        existingVisit.setStartDate(visitRequestDto.getStartDate());
        existingVisit.setStartTime(startTime);
        existingVisit.setEndTime(startTime.plusMinutes(service.getDurationMin()));
    }

    public void partialUpdateEntityFromDto(Visit existingVisit,
                                    VisitRequestDto visitRequestDto,
                                    Client client,
                                    Hairdresser hairdresser,
                                    Service service) {
        if(visitRequestDto.getClientId() != null)
            existingVisit.setClient(client);

        if(visitRequestDto.getHairdresserId() != null)
            existingVisit.setHairdresser(hairdresser);

        if(visitRequestDto.getServiceId() != null)
            existingVisit.setService(service);

        if(visitRequestDto.getStartDate() != null)
            existingVisit.setStartDate(visitRequestDto.getStartDate());

        if(visitRequestDto.getStartTime() != null) {
            LocalTime startTime = visitRequestDto.getStartTime();
            existingVisit.setStartTime(startTime);

            if(visitRequestDto.getServiceId() == null)
                existingVisit.setEndTime(startTime.plusMinutes(existingVisit.getService().getDurationMin()));
            else
                existingVisit.setEndTime(startTime.plusMinutes(service.getDurationMin()));
        }
    }
}
