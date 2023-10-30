package com.elektra.entrevista.deivi.validation.Impl;

import com.elektra.entrevista.deivi.common.exceptions.ContactoTechnicalException;
import com.elektra.entrevista.deivi.dto.ContactoRequestDto;
import com.elektra.entrevista.deivi.repository.ContactoRepository;
import com.elektra.entrevista.deivi.validation.ContactoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TelefonoValidator implements ContactoValidator {
    private static final Logger logger = LoggerFactory.getLogger(TelefonoValidator.class);
    private ContactoRepository contactoRepository;
    public TelefonoValidator(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }
    @Override
    public void validate(ContactoRequestDto contactoRequest) throws ContactoTechnicalException {
        if(contactoRepository.existsByClienteIdAndTelefono(contactoRequest.getClienteId(), contactoRequest.getTelefono())){
            logger.error("Teléfono {} ya existe para el cliente {}", contactoRequest.getTelefono(), contactoRequest.getClienteId());
            throw new ContactoTechnicalException("Ya existe el teléfono "+ contactoRequest.getTelefono() +" para el cliente");
        }
    }
}