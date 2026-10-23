package com.roman.barber_shop.api.mapper;

import com.roman.barber_shop.api.dto.request.HairdresserRequestDto;
import com.roman.barber_shop.api.dto.response.HairdresserResponseDto;
import com.roman.barber_shop.api.dto.response.QualificationResponseDto;
import com.roman.barber_shop.persistence.entity.Hairdresser;
import com.roman.barber_shop.persistence.entity.Qualification;
import org.springframework.stereotype.Component;

@Component
public class HairdresserMapper {
    public HairdresserResponseDto toDto(Hairdresser hairdresser, QualificationResponseDto qualificationResponseDto) {
        return HairdresserResponseDto.builder()
                .id(hairdresser.getId())
                .firstName(hairdresser.getFirstName())
                .lastName(hairdresser.getLastName())
                .startOfWorking(hairdresser.getStartOfWorking())
                .qualification(qualificationResponseDto)
                .build();
    }

    public Hairdresser toEntity(HairdresserRequestDto hairdresserRequestDto, Qualification qualification) {
        return Hairdresser.builder()
                .firstName(hairdresserRequestDto.getFirstName())
                .lastName(hairdresserRequestDto.getLastName())
                .startOfWorking(hairdresserRequestDto.getStartOfWorking())
                .qualification(qualification)
                .build();
    }

    public void updateEntityFromDto(Hairdresser existingHairdresser, Qualification qualification, HairdresserRequestDto hairdresserRequestDto) {
        existingHairdresser.setFirstName(hairdresserRequestDto.getFirstName());
        existingHairdresser.setLastName(hairdresserRequestDto.getLastName());
        existingHairdresser.setStartOfWorking(hairdresserRequestDto.getStartOfWorking());
        existingHairdresser.setQualification(qualification);
    }

    public void partialUpdateEntityFromDto(Hairdresser existingHairdresser, Qualification qualification, HairdresserRequestDto hairdresserRequestDto) {
        if(hairdresserRequestDto.getFirstName() != null)
            existingHairdresser.setFirstName(hairdresserRequestDto.getFirstName());

        if(hairdresserRequestDto.getLastName() != null)
            existingHairdresser.setLastName(hairdresserRequestDto.getLastName());

        if(hairdresserRequestDto.getStartOfWorking() != null)
            existingHairdresser.setStartOfWorking(hairdresserRequestDto.getStartOfWorking());

        if(qualification != null)
            existingHairdresser.setQualification(qualification);
    }
}
