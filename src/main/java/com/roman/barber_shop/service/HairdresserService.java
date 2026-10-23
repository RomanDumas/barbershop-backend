package com.roman.barber_shop.service;

import com.roman.barber_shop.api.dto.request.HairdresserRequestDto;
import com.roman.barber_shop.api.dto.response.HairdresserResponseDto;
import com.roman.barber_shop.api.dto.response.QualificationResponseDto;
import com.roman.barber_shop.api.mapper.HairdresserMapper;
import com.roman.barber_shop.api.mapper.QualificationMapper;
import com.roman.barber_shop.persistence.entity.Hairdresser;
import com.roman.barber_shop.persistence.entity.Qualification;
import com.roman.barber_shop.persistence.repository.HairdresserRepository;
import com.roman.barber_shop.persistence.repository.QualificationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HairdresserService {
    private final HairdresserRepository hairdresserRepository;
    private final QualificationRepository qualificationRepository;
    private final HairdresserMapper hairdresserMapper;
    private final QualificationMapper qualificationMapper;

    public List<HairdresserResponseDto> getAllHairdresserDto() {
        List<Hairdresser> hairdressers = hairdresserRepository.findAll();

        return hairdressers.stream()
                .map(hairdresser -> {
                    Qualification qualification = hairdresser.getQualification();
                    QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);

                    return hairdresserMapper.toDto(hairdresser, qualificationResponseDto);
                })
                .toList();
    }

    public HairdresserResponseDto getHairdresserResponseDtoById(long id) {
        Hairdresser existingHairdresser = hairdresserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no hairdresser with id " + id));

        Qualification qualification = existingHairdresser.getQualification();

        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);

        return hairdresserMapper.toDto(existingHairdresser, qualificationResponseDto);
    }

    public HairdresserResponseDto createHairdresser(HairdresserRequestDto request) {
        Qualification qualification = qualificationRepository.findById(request.getQualificationId())
                .orElseThrow(() -> new EntityNotFoundException("There is no qualification with id " + request.getQualificationId()));
        Hairdresser hairdresser = hairdresserMapper.toEntity(request, qualification);

        Hairdresser createdHairdresser = hairdresserRepository.save(hairdresser);

        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);

        return hairdresserMapper.toDto(createdHairdresser, qualificationResponseDto);
    }

    public HairdresserResponseDto updateHairdresser(HairdresserRequestDto request, long id) {
        Hairdresser existingHairdresser = hairdresserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no hairdresser with id " + id));
        Qualification qualification = qualificationRepository.findById(request.getQualificationId())
                .orElseThrow(() -> new EntityNotFoundException("There is no qualification with id " + request.getQualificationId()));

        hairdresserMapper.updateEntityFromDto(existingHairdresser, qualification, request);
        Hairdresser updatedHairdresser = hairdresserRepository.save(existingHairdresser);

        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(qualification);

        return hairdresserMapper.toDto(updatedHairdresser, qualificationResponseDto);
    }
    public HairdresserResponseDto partialUpdateHairdresser(HairdresserRequestDto request, long id) {
        Hairdresser existingHairdresser = hairdresserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no hairdresser with id " + id));

        Qualification qualification = null;
        if(request.getQualificationId() != 0)
            qualification = qualificationRepository.findById(request.getQualificationId())
                    .orElseThrow(() -> new EntityNotFoundException("There is no hairdresser with id " + id));

        hairdresserMapper.partialUpdateEntityFromDto(existingHairdresser, qualification, request);

        Hairdresser partialUpdatedHairdresser = hairdresserRepository.save(existingHairdresser);

        QualificationResponseDto qualificationResponseDto = qualificationMapper.toDto(partialUpdatedHairdresser.getQualification());

        return hairdresserMapper.toDto(partialUpdatedHairdresser, qualificationResponseDto);
    }

    public void deleteHairdresser(long id) {
        Hairdresser foundHairdresser = hairdresserRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no hairdresser with id " + id));

        hairdresserRepository.delete(foundHairdresser);
    }
}
