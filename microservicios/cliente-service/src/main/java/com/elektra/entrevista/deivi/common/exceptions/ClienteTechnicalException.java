package com.elektra.entrevista.deivi.common.exceptions;

public class ClienteTechnicalException extends AppException {
    public ClienteTechnicalException(String message) {
        super(TypeException.TECHNICAL, message);
    }
}