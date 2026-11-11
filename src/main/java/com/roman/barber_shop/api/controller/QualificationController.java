package com.roman.barber_shop.api.controller;

import com.roman.barber_shop.api.dto.request.QualificationRequestDto;
import com.roman.barber_shop.api.dto.response.QualificationResponseDto;
import com.roman.barber_shop.service.QualificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/qualifications")
public class QualificationController {
    private final QualificationService qualificationService;

    @GetMapping
    public ResponseEntity<List<QualificationResponseDto>> getAllQualificationDtos() {
        List<QualificationResponseDto> qualificationResponseDtos = qualificationService.getAllQualificationDtos();
        return ResponseEntity.status(HttpStatus.OK).body(qualificationResponseDtos);
    }

    @GetMapping("/{qualificationId}")
    public ResponseEntity<QualificationResponseDto> getQualificationDto(@PathVariable long id) {
        QualificationResponseDto foundQualification = qualificationService.getQualificationDtoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundQualification);
    }

    @PostMapping
    public ResponseEntity<QualificationResponseDto> createQualification(@RequestBody QualificationRequestDto request) {
        QualificationResponseDto createdQualification = qualificationService.creteQualification(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQualification);
    }

    @PutMapping("/{qualificationId}")
    public ResponseEntity<QualificationResponseDto> updateQualification(@RequestBody QualificationRequestDto request,
                                                                        @PathVariable long id) {
        QualificationResponseDto updatedQualification = qualificationService.updateQualification(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedQualification);
    }

    @PatchMapping("/{qualificationId}")
    public ResponseEntity<QualificationResponseDto> partialUpdateQualification(@RequestBody QualificationRequestDto request,
                                                                               @PathVariable long id){
        QualificationResponseDto partialUpdatedQualification = qualificationService.partialUpdateQualification(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(partialUpdatedQualification);
    }

    @DeleteMapping("/{qualificationId}")
    public ResponseEntity<Void> deleteQualification(@PathVariable long id) {
        qualificationService.deleteQualification(id);
        return ResponseEntity.ok().build();
    }
}
