package com.elektra.entrevista.deivi.validation.Impl;

import com.elektra.entrevista.deivi.common.exceptions.ContactoTechnicalException;
import com.elektra.entrevista.deivi.dto.ContactoRequestDto;
import com.elektra.entrevista.deivi.validation.ContactoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class DatosContactoValidator implements ContactoValidator {
    private static final Logger logger = LoggerFactory.getLogger(DatosContactoValidator.class);

    @Override
    public void validate(ContactoRequestDto contactoRequest) throws ContactoTechnicalException {
        if(!StringUtils.hasText(contactoRequest.getCorreo()) || !StringUtils.hasText(contactoRequest.getTelefono())){
            logger.error("Ingrese Correo o télefono para cliente {}", contactoRequest.getClienteId());
            throw new ContactoTechnicalException("Ingrese algún dato de contacto");
        }
    }
}
