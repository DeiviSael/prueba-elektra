package com.elektra.entrevista.deivi.service;

import com.elektra.entrevista.deivi.common.exceptions.ClienteTechnicalException;
import com.elektra.entrevista.deivi.dto.ClienteRequestDto;
import com.elektra.entrevista.deivi.dto.ClienteResponseDto;

import java.util.List;

public interface ClienteService {
    ClienteResponseDto createCliente(ClienteRequestDto clienteResponseDto) throws ClienteTechnicalException;
    ClienteResponseDto updateCliente(ClienteRequestDto clienteResponseDto) throws ClienteTechnicalException;
    List<ClienteResponseDto> getAllClientes() throws ClienteTechnicalException;
    ClienteResponseDto getCliente(Long id) throws ClienteTechnicalException;
    void deleteCliente(Long id) throws ClienteTechnicalException;
}
