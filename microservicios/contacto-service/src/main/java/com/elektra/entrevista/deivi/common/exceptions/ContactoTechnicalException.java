package com.elektra.entrevista.deivi.common.exceptions;

public class ContactoTechnicalException extends AppException {
    public ContactoTechnicalException(String message) {
        super(TypeException.TECHNICAL, message);
    }
}
