package com.roman.barber_shop.api.mapper;

import com.roman.barber_shop.api.dto.request.ServiceRequestDto;
import com.roman.barber_shop.api.dto.response.ServiceResponseDto;
import com.roman.barber_shop.persistence.entity.Service;
import org.springframework.stereotype.Component;

@Component
public class ServiceMapper {
    public ServiceResponseDto toDto(Service service) {
        return ServiceResponseDto.builder()
                .id(service.getId())
                .name(service.getName())
                .durationMin(service.getDurationMin())
                .price(service.getPrice())
                .build();
    }

    public Service toEntity(ServiceRequestDto serviceRequestDto) {
        return Service.builder()
                .name(serviceRequestDto.getName())
                .durationMin(serviceRequestDto.getDurationMin())
                .price(serviceRequestDto.getPrice())
                .build();
    }

    public void updateEntityFromDto(ServiceRequestDto serviceRequestDto, Service existingService) {
        existingService.setName(serviceRequestDto.getName());
        existingService.setDurationMin(serviceRequestDto.getDurationMin());
        existingService.setPrice(serviceRequestDto.getPrice());
    }

    public void partialUpdateEntityFromDto(ServiceRequestDto serviceRequestDto, Service existingService) {
        if(serviceRequestDto.getName() != null)
            existingService.setName(serviceRequestDto.getName());

        if(serviceRequestDto.getDurationMin() != null)
            existingService.setDurationMin(serviceRequestDto.getDurationMin());

        if(serviceRequestDto.getPrice() != null)
            existingService.setPrice(serviceRequestDto.getPrice());
    }
}
