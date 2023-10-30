package com.elektra.entrevista.deivi.validation.Impl;

import com.elektra.entrevista.deivi.common.exceptions.ClienteTechnicalException;
import com.elektra.entrevista.deivi.dto.ClienteRequestDto;
import com.elektra.entrevista.deivi.validation.ClienteValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class FechaNacimientoValidator implements ClienteValidator {
    private static final Logger logger = LoggerFactory.getLogger(FechaNacimientoValidator.class);

    @Override
    public void validate(ClienteRequestDto clienteRequest) throws ClienteTechnicalException {
        if (!StringUtils.hasText(clienteRequest.getFechaNacimiento())) {
            logger.error("Ingrese una fecha de nacimiento a registrar");
            throw new ClienteTechnicalException("Ingrese una fecha de nacimiento a registrar");
        }
    }
}
