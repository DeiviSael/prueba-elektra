package com.elektra.entrevista.deivi.validation.Impl;

import com.elektra.entrevista.deivi.common.Utils.Utils;
import com.elektra.entrevista.deivi.common.exceptions.ClienteTechnicalException;
import com.elektra.entrevista.deivi.dto.ClienteRequestDto;
import com.elektra.entrevista.deivi.validation.ClienteValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FechaValidaValidator implements ClienteValidator {
    private static final Logger logger = LoggerFactory.getLogger(FechaValidaValidator.class);

    @Override
    public void validate(ClienteRequestDto clienteRequest) throws ClienteTechnicalException {
        try {
            if (!Utils.isValidDate(LocalDate.parse(clienteRequest.getFechaNacimiento(), DateTimeFormatter.ofPattern("yyyy-MM-dd")))){
                logger.error("Formato fecha de nacimiento incorrecta {}", clienteRequest.getFechaNacimiento());
                throw new ClienteTechnicalException("Fecha " + clienteRequest.getFechaNacimiento() + " inválida, respeta el formato yyyy-MM-dd");
            }
        }catch (Exception ex){
            logger.error("Fecha de nacimiento invalida {}", clienteRequest.getFechaNacimiento());
            throw new ClienteTechnicalException("Fecha " + clienteRequest.getFechaNacimiento() + " inválida");
        }

    }
}
