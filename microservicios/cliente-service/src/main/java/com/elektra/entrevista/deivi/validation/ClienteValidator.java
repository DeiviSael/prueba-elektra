package com.elektra.entrevista.deivi.validation;

import com.elektra.entrevista.deivi.common.exceptions.ClienteTechnicalException;
import com.elektra.entrevista.deivi.dto.ClienteRequestDto;

public interface ClienteValidator {
    void validate(ClienteRequestDto clienteRequest) throws ClienteTechnicalException;

}
