package com.roman.barber_shop.service;

import com.roman.barber_shop.api.dto.request.ServiceRequestDto;
import com.roman.barber_shop.api.dto.response.ServiceResponseDto;
import com.roman.barber_shop.api.mapper.ServiceMapper;
import com.roman.barber_shop.persistence.entity.Service;
import com.roman.barber_shop.persistence.repository.ServiceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceManager {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    public List<ServiceResponseDto> getAllServiceDtos() {
        List<Service> services = serviceRepository.findAll();
        return services.stream()
                .map(serviceMapper::toDto).toList();
    }

    public ServiceResponseDto getServiceDtoById(long id) {
        Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no service with id " + id));
        return serviceMapper.toDto(existingService);
    }

    public ServiceResponseDto createService(ServiceRequestDto request) {
        Service newService = serviceMapper.toEntity(request);
        Service savedService = serviceRepository.save(newService);
        return serviceMapper.toDto(savedService);
    }

    public ServiceResponseDto updateService(ServiceRequestDto request, long id) {
        Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no service with id " + id));

        serviceMapper.updateEntityFromDto(request, existingService);

        Service updatedService = serviceRepository.save(existingService);

        return serviceMapper.toDto(updatedService);
    }

    public ServiceResponseDto partialUpdateService(ServiceRequestDto request, long id) {
        Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no service with id " + id));

        serviceMapper.partialUpdateEntityFromDto(request, existingService);

        Service partialUpdatedService = serviceRepository.save(existingService);

        return serviceMapper.toDto(partialUpdatedService);
    }

    public void deleteService(long id) {
        Service foundService = serviceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no service with id " + id));

        serviceRepository.delete(foundService);
    }
}

