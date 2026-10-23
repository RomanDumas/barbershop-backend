package com.roman.barber_shop.api.mapper;

import com.roman.barber_shop.api.dto.request.ClientRequestDto;
import com.roman.barber_shop.api.dto.response.ClientResponseDto;
import com.roman.barber_shop.persistence.entity.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public ClientResponseDto toDto(Client client) {
        return ClientResponseDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .email(client.getEmail())
                .phone(client.getPhone())
                .build();
    }

    public Client toEntity(ClientRequestDto clientRequestDto) {
        return Client.builder()
                .firstName(clientRequestDto.getFirstName())
                .lastName(clientRequestDto.getLastName())
                .email(clientRequestDto.getEmail())
                .phone(clientRequestDto.getPhone())
                .build();
    }

    public void updateEntityFromDto(Client existingClient, ClientRequestDto clientRequestDto) {
        existingClient.setFirstName(clientRequestDto.getFirstName());
        existingClient.setLastName(clientRequestDto.getLastName());
        existingClient.setEmail(clientRequestDto.getEmail());
        existingClient.setPhone(clientRequestDto.getPhone());
    }

    public void partialUpdateEntityFromDto(Client existingClient, ClientRequestDto clientRequestDto){
        if(clientRequestDto.getFirstName() != null)
            existingClient.setFirstName(clientRequestDto.getFirstName());

        if(clientRequestDto.getLastName() != null)
            existingClient.setLastName(clientRequestDto.getLastName());

        if(clientRequestDto.getEmail() != null)
            existingClient.setEmail(clientRequestDto.getEmail());

        if(clientRequestDto.getPhone() != null)
            existingClient.setPhone(clientRequestDto.getPhone());
    }
}
