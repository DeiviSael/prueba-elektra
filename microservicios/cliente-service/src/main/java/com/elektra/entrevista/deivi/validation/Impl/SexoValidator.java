package com.elektra.entrevista.deivi.validation.Impl;

import com.elektra.entrevista.deivi.common.exceptions.ClienteTechnicalException;
import com.elektra.entrevista.deivi.dto.ClienteRequestDto;
import com.elektra.entrevista.deivi.validation.ClienteValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class SexoValidator implements ClienteValidator {
    private static final Logger logger = LoggerFactory.getLogger(SexoValidator.class);
    @Override
    public void validate(ClienteRequestDto clienteRequest) throws ClienteTechnicalException {
        if (!StringUtils.hasText(clienteRequest.getSexo())) {
            logger.error("Cliente necesita un sexo a regitrar");
            throw new ClienteTechnicalException("Ingrese un sexo a registrar");
        }
    }
}