package com.roman.barber_shop.api.controller;

import com.roman.barber_shop.api.dto.request.ServiceRequestDto;
import com.roman.barber_shop.api.dto.response.ServiceResponseDto;
import com.roman.barber_shop.service.ServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/services")
public class ServiceController {
    private final ServiceManager serviceManager;

    @GetMapping
    public ResponseEntity<List<ServiceResponseDto>> getAllServiceDtos() {
        List<ServiceResponseDto> serviceResponseDtos = serviceManager.getAllServiceDtos();
        return ResponseEntity.status(HttpStatus.OK).body(serviceResponseDtos);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<ServiceResponseDto> getServiceDtoById(@PathVariable long id) {
        ServiceResponseDto foundService = serviceManager.getServiceDtoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundService);
    }

    @PostMapping
    public ResponseEntity<ServiceResponseDto> createService(@RequestBody ServiceRequestDto request) {
        ServiceResponseDto createdService = serviceManager.createService(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdService);
    }

    @PutMapping("/{serviceId}")
    public ResponseEntity<ServiceResponseDto> updateService(@RequestBody ServiceRequestDto request,
                                                            @PathVariable long id) {
        ServiceResponseDto updatedService = serviceManager.updateService(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedService);
    }

    @PatchMapping("/{serviceId}")
    public ResponseEntity<ServiceResponseDto> partialUpdateService(@RequestBody ServiceRequestDto request,
                                                            @PathVariable long id) {
        ServiceResponseDto partialUpdatedService = serviceManager.partialUpdateService(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(partialUpdatedService);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable long id) {
        serviceManager.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
