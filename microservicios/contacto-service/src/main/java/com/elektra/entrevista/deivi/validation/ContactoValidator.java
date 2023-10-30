package com.elektra.entrevista.deivi.validation;

import com.elektra.entrevista.deivi.common.exceptions.ContactoTechnicalException;
import com.elektra.entrevista.deivi.dto.ContactoRequestDto;

public interface ContactoValidator {
    void validate(ContactoRequestDto contactoRequest) throws ContactoTechnicalException;
}
