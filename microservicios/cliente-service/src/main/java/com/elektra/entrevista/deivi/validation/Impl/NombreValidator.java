package com.elektra.entrevista.deivi.validation.Impl;

import com.elektra.entrevista.deivi.common.exceptions.ClienteTechnicalException;
import com.elektra.entrevista.deivi.dto.ClienteRequestDto;
import com.elektra.entrevista.deivi.validation.ClienteValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class NombreValidator implements ClienteValidator {
    private static final Logger logger = LoggerFactory.getLogger(NombreValidator.class);
    @Override
    public void validate(ClienteRequestDto clienteRequest) throws ClienteTechnicalException {
        if (!StringUtils.hasText(clienteRequest.getNombre())) {
            logger.error("Cliente necesita un nombre a regitrar");
            throw new ClienteTechnicalException("Ingrese un nombre a registrar");
        }
    }
}
