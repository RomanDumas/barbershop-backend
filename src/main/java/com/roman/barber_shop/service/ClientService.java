package com.roman.barber_shop.service;


import com.roman.barber_shop.api.dto.request.ClientRequestDto;
import com.roman.barber_shop.api.dto.response.ClientResponseDto;
import com.roman.barber_shop.api.mapper.ClientMapper;
import com.roman.barber_shop.persistence.entity.Client;
import com.roman.barber_shop.persistence.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public List<ClientResponseDto> getAllClientDtos() {
        List<Client> clients = clientRepository.findAll();

        return clients.stream()
                .map(clientMapper::toDto).toList();
    }

    public ClientResponseDto getClientDtoById(long id) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no client with id " + id));

        return clientMapper.toDto(existingClient);
    }

    public ClientResponseDto createClient(ClientRequestDto request) {
        Client newClient = clientMapper.toEntity(request);
        Client savedClient = clientRepository.save(newClient);
        return clientMapper.toDto(savedClient);
    }

    public ClientResponseDto updateClient(ClientRequestDto request, long id) {
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no client with id " + id));

        clientMapper.updateEntityFromDto(existingClient, request);

        Client updatedClient = clientRepository.save(existingClient);

        return clientMapper.toDto(updatedClient);
    }

    public ClientResponseDto partialUpdateClient(ClientRequestDto request, long id){
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no client with id " + id));

        clientMapper.partialUpdateEntityFromDto(existingClient, request);

        Client partialUpdatedClient = clientRepository.save(existingClient);

        return clientMapper.toDto(partialUpdatedClient);
    }

    public void deleteClient(long clientId) {
        Client foundClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException("There is no client with id " + clientId));

        clientRepository.delete(foundClient);
    }


}
