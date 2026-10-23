package com.roman.barber_shop.api.controller;


import com.roman.barber_shop.api.dto.request.HairdresserRequestDto;
import com.roman.barber_shop.api.dto.response.HairdresserResponseDto;
import com.roman.barber_shop.service.HairdresserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/hairdressers")
public class HairdresserController {
    private final HairdresserService hairdresserService;

    @GetMapping
    public ResponseEntity<List<HairdresserResponseDto>> getAllHairdresserDtos() {
        List<HairdresserResponseDto> hairdresserResponseDtos = hairdresserService.getAllHairdresserDto();
        return ResponseEntity.status(HttpStatus.OK).body(hairdresserResponseDtos);
    }

    @GetMapping("/{hairdresserId}")
    public ResponseEntity<HairdresserResponseDto> getHairdresserDtoById(@PathVariable long id) {
        HairdresserResponseDto foundHairdresser = hairdresserService.getHairdresserResponseDtoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundHairdresser);
    }

    @PostMapping
    public ResponseEntity<HairdresserResponseDto> createHairdresser(@RequestBody HairdresserRequestDto request) {
        HairdresserResponseDto createdHairdresser = hairdresserService.createHairdresser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHairdresser);
    }

    @PutMapping("/{hairdresserId}")
    public ResponseEntity<HairdresserResponseDto> updateHairdresser(@RequestBody HairdresserRequestDto request,
                                                                    @PathVariable long id) {
        HairdresserResponseDto updatedHairdresser = hairdresserService.updateHairdresser(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedHairdresser);
    }

    @PatchMapping("/{hairdresserId}")
    public ResponseEntity<HairdresserResponseDto> partialUpdateHairdresser(@RequestBody HairdresserRequestDto request,
                                                                    @PathVariable long id) {
        HairdresserResponseDto partialUpdatedHairdresser = hairdresserService.partialUpdateHairdresser(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(partialUpdatedHairdresser);
    }

    @DeleteMapping("/{hairdresserId}")
    public ResponseEntity<Void> deleteHairdresser(@PathVariable long id) {
        hairdresserService.deleteHairdresser(id);
        return ResponseEntity.ok().build();
    }
}
