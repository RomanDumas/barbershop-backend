package com.roman.barber_shop.api.controller;

import com.roman.barber_shop.api.dto.request.VisitRequestDto;
import com.roman.barber_shop.api.dto.response.VisitResponseDto;
import com.roman.barber_shop.service.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/visits")
public class VisitController {
    private final VisitService visitService;

    @GetMapping
    public ResponseEntity<List<VisitResponseDto>> getAllVisitDtos() {
        List<VisitResponseDto> visitDtos = visitService.getAllVisitDtos();
        return ResponseEntity.status(HttpStatus.OK).body(visitDtos);
    }

    @GetMapping("/{visitId}")
    public ResponseEntity<VisitResponseDto> getVisitDtoById(@PathVariable long id) {
        VisitResponseDto foundVisit = visitService.getVisitDtoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundVisit);
    }

    @PostMapping
    public ResponseEntity<VisitResponseDto> createVisit(@RequestBody VisitRequestDto request) {
        VisitResponseDto createdVisit = visitService.createVisit(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVisit);
    }

    @PutMapping("/{visitId}")
    public ResponseEntity<VisitResponseDto> updateVisit(@RequestBody VisitRequestDto request,
                                                        @PathVariable long id) {
        VisitResponseDto updatedVisit = visitService.updateVisitDto(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedVisit);
    }

    @PatchMapping("/{visitId}")
    public ResponseEntity<VisitResponseDto> partialUpdateVisit(@RequestBody VisitRequestDto request,
                                                        @PathVariable long id) {
        VisitResponseDto partialUpdatedVisit = visitService.partialUpdateVisitDto(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(partialUpdatedVisit);
    }

    @DeleteMapping("/{visitId}")
    public ResponseEntity<Void> deleteVisit(@PathVariable long id) {
        visitService.deleteVisit(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
