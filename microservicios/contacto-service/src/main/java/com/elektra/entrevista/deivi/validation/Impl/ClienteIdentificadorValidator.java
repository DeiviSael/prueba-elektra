package com.elektra.entrevista.deivi.validation.Impl;

import com.elektra.entrevista.deivi.common.exceptions.ContactoTechnicalException;
import com.elektra.entrevista.deivi.common.response.ClienteResponse;
import com.elektra.entrevista.deivi.dto.ClienteResponseDto;
import com.elektra.entrevista.deivi.dto.ContactoRequestDto;
import com.elektra.entrevista.deivi.service.clienteservice.ClienteServiceApi;
import com.elektra.entrevista.deivi.validation.ContactoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClienteIdentificadorValidator implements ContactoValidator {
    private static final Logger logger = LoggerFactory.getLogger(ClienteIdentificadorValidator.class);
    private ClienteServiceApi clienteServiceApi;
    public ClienteIdentificadorValidator(ClienteServiceApi clienteServiceApi) {
        this.clienteServiceApi = clienteServiceApi;
    }
    @Override
    public void validate(ContactoRequestDto contactoRequest) throws ContactoTechnicalException {
        if(contactoRequest.getClienteId() == null){
            logger.error("Identificador del cliente invalido");
            throw new ContactoTechnicalException("Verifique el identificador del cliente");
        }
        ClienteResponse<ClienteResponseDto> clienteResponse = this.clienteServiceApi.obetenerCliente(contactoRequest.getClienteId());
        if(clienteResponse.getParameters()==null){
            logger.error("Cliente no encontrado id {}", contactoRequest.getClienteId());
            throw new ContactoTechnicalException("No se econtró cliente, verifique el identificador");
        }
    }
}
