package com.elektra.entrevista.deivi.validation.Impl;

import com.elektra.entrevista.deivi.common.exceptions.ClienteTechnicalException;
import com.elektra.entrevista.deivi.dto.ClienteRequestDto;
import com.elektra.entrevista.deivi.repository.ClienteRepository;
import com.elektra.entrevista.deivi.validation.ClienteValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NombreUnicoValidator implements ClienteValidator {
    private static final Logger logger = LoggerFactory.getLogger(NombreUnicoValidator.class);
    private ClienteRepository clienteRepository;
    public NombreUnicoValidator(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @Override
    public void validate(ClienteRequestDto clienteRequest) throws ClienteTechnicalException {
        if(clienteRequest.getId() !=null && this.clienteRepository.existsById(clienteRequest.getId())){
            logger.error("Cliente id {} ingresado incorrecto", clienteRequest.getId());
            throw new ClienteTechnicalException("Verifique el identificador " + clienteRequest.getId());
        }
        if (clienteRepository.existsByNombre(clienteRequest.getNombre())) {
            logger.error("Cliente con el nombre {} ya existe", clienteRequest.getNombre());
            throw new ClienteTechnicalException("Cliente " + clienteRequest.getNombre() + " ya existe!");
        }
    }
}
