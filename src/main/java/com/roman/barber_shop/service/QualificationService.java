package com.roman.barber_shop.service;

import com.roman.barber_shop.api.dto.request.QualificationRequestDto;
import com.roman.barber_shop.api.dto.response.QualificationResponseDto;
import com.roman.barber_shop.api.mapper.QualificationMapper;
import com.roman.barber_shop.persistence.entity.Qualification;
import com.roman.barber_shop.persistence.repository.QualificationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QualificationService {
    private final QualificationRepository qualificationRepository;
    private final QualificationMapper qualificationMapper;

    public List<QualificationResponseDto> getAllQualificationDtos() {
        List<Qualification> qualifications = qualificationRepository.findAll();

        return qualifications.stream()
                .map(qualificationMapper::toDto).toList();
    }

    public QualificationResponseDto getQualificationDtoById(long id) {
        Qualification existingQualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no qualification with id " + id));

        return qualificationMapper.toDto(existingQualification);
    }

    public QualificationResponseDto creteQualification(QualificationRequestDto request) {
        Qualification newQualification = qualificationMapper.toEntity(request);

        Qualification createdQualification = qualificationRepository.save(newQualification);

        return qualificationMapper.toDto(createdQualification);
    }

    public QualificationResponseDto updateQualification(QualificationRequestDto request, long id) {
        Qualification existingQualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no qualification with id " + id));

        qualificationMapper.updateEntityFromDto(request, existingQualification);

        Qualification updatedQualification = qualificationRepository.save(existingQualification);

        return qualificationMapper.toDto(updatedQualification);
    }

    public QualificationResponseDto partialUpdateQualification(QualificationRequestDto request, long id) {
        Qualification existingQualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no qualification with id " + id));

        qualificationMapper.partialUpdateEntityFromDto(request, existingQualification);

        Qualification partialUpdatedQualification = qualificationRepository.save(existingQualification);

        return qualificationMapper.toDto(partialUpdatedQualification);
    }

    public void deleteQualification(long id) {
        Qualification foundQualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no qualification with id " + id));

        qualificationRepository.delete(foundQualification);
    }
}
