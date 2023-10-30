package com.elektra.entrevista.deivi.service;

import com.elektra.entrevista.deivi.common.exceptions.ContactoTechnicalException;
import com.elektra.entrevista.deivi.dto.ContactoRequestDto;
import com.elektra.entrevista.deivi.dto.ContactoResponseDto;

import java.util.List;

public interface ContactoService {
    ContactoResponseDto createContacto(ContactoRequestDto contactoResponseDto) throws ContactoTechnicalException;
    ContactoResponseDto updateContacto(ContactoRequestDto contactoResponseDto) throws ContactoTechnicalException;
    List<ContactoResponseDto> getAllContactos() throws ContactoTechnicalException;
    ContactoResponseDto getContacto(Long id) throws ContactoTechnicalException;
    void deleteContacto(Long id) throws ContactoTechnicalException;
    List<ContactoResponseDto> getAllContactosPorCliente(Long clienteId) throws ContactoTechnicalException;
}
