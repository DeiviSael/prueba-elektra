package com.elektra.entrevista.deivi.validation.Impl;

import com.elektra.entrevista.deivi.common.exceptions.ClienteTechnicalException;
import com.elektra.entrevista.deivi.dto.ClienteRequestDto;
import com.elektra.entrevista.deivi.model.Cliente;
import com.elektra.entrevista.deivi.repository.ClienteRepository;
import com.elektra.entrevista.deivi.validation.ClienteValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Optional;

public class NombreUnicoToUpdateValidator implements ClienteValidator {
    private static final Logger logger = LoggerFactory.getLogger(NombreUnicoToUpdateValidator.class);
    private ClienteRepository clienteRepository;
    public NombreUnicoToUpdateValidator(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @Override
    public void validate(ClienteRequestDto clienteRequest) throws ClienteTechnicalException {
        if(!this.clienteRepository.existsById(clienteRequest.getId())){
            logger.error("Cliente no econtrado id {}", clienteRequest.getId());
            throw new ClienteTechnicalException("Cliente " + clienteRequest.getNombre() + " no encontrado");
        }
        Optional<Cliente> cliente = this.clienteRepository.findByNombre(clienteRequest.getNombre());
        if (cliente.isPresent() && !Objects.equals(cliente.get().getId(), clienteRequest.getId())) {
            logger.error("Cliente con mismo nombre ya existe - {}", clienteRequest.getNombre());
            throw new ClienteTechnicalException("Cliente con el nombre " + clienteRequest.getNombre() + " ya existe!");
        }
    }
}
