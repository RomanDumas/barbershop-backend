package com.roman.barber_shop.api.controller;

import com.roman.barber_shop.api.dto.request.ClientRequestDto;
import com.roman.barber_shop.api.dto.response.ClientResponseDto;
import com.roman.barber_shop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> getAllClients() {
        List<ClientResponseDto> clientResponseDtos = clientService.getAllClientDtos();
        return ResponseEntity.status(HttpStatus.OK).body(clientResponseDtos);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> getClientDtoById(@PathVariable("clientId") long id) {
        ClientResponseDto foundClient = clientService.getClientDtoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundClient);
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> createClient(@RequestBody ClientRequestDto request) {
        ClientResponseDto savedClient = clientService.createClient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> updateClient(@RequestBody ClientRequestDto request,
                                                          @PathVariable("clientId") long id) {
        ClientResponseDto updatedClient = clientService.updateClient(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedClient);
    }

    @PatchMapping("/{clientId}")
    public ResponseEntity<ClientResponseDto> partialUpdateClient(@RequestBody ClientRequestDto request,
                                                                 @PathVariable("clientId") long id){
        ClientResponseDto partialUpdatedClient = clientService.partialUpdateClient(request, id);
        return ResponseEntity.status(HttpStatus.OK).body(partialUpdatedClient);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable("clientId") long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

}
