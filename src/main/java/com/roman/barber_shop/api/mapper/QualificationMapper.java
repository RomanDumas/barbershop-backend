package com.roman.barber_shop.api.mapper;

import com.roman.barber_shop.api.dto.request.QualificationRequestDto;
import com.roman.barber_shop.api.dto.response.QualificationResponseDto;
import com.roman.barber_shop.persistence.entity.Qualification;
import org.springframework.stereotype.Component;

@Component
public class QualificationMapper {
    public QualificationResponseDto toDto(Qualification qualification) {
        return QualificationResponseDto.builder()
                .id(qualification.getId())
                .name(qualification.getName())
                .build();
    }

    public Qualification toEntity(QualificationRequestDto qualificationRequestDto) {
        return Qualification.builder()
                .name(qualificationRequestDto.getName())
                .build();
    }

    public void updateEntityFromDto(QualificationRequestDto qualificationRequestDto, Qualification existingQualification) {
        existingQualification.setName(qualificationRequestDto.getName());
    }
    public void partialUpdateEntityFromDto(QualificationRequestDto qualificationRequestDto, Qualification existingQualification) {
        if(qualificationRequestDto.getName() != null)
            existingQualification.setName(qualificationRequestDto.getName());
    }
}
