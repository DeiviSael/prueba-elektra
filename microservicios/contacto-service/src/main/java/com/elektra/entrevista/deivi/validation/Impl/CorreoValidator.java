package com.elektra.entrevista.deivi.validation.Impl;

import com.elektra.entrevista.deivi.common.exceptions.ContactoTechnicalException;
import com.elektra.entrevista.deivi.dto.ContactoRequestDto;
import com.elektra.entrevista.deivi.repository.ContactoRepository;
import com.elektra.entrevista.deivi.validation.ContactoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorreoValidator implements ContactoValidator {
    private static final Logger logger = LoggerFactory.getLogger(CorreoValidator.class);
    private ContactoRepository contactoRepository;
    public CorreoValidator(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }
    @Override
    public void validate(ContactoRequestDto contactoRequest) throws ContactoTechnicalException {
        if(contactoRepository.existsByClienteIdAndCorreo(contactoRequest.getClienteId(), contactoRequest.getCorreo())){
            logger.error("Correo {} ya existe para el cliente {}", contactoRequest.getCorreo(), contactoRequest.getClienteId());
            throw new ContactoTechnicalException("Ya existe el correo "+ contactoRequest.getCorreo() +" para el cliente");
        }
    }
}
