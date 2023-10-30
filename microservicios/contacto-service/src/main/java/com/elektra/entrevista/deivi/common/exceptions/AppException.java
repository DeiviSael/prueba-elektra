package com.elektra.entrevista.deivi.common.exceptions;

public abstract class AppException extends Exception {
    private final TypeException code;

    protected AppException(final TypeException code, final String message) {
        super(message);
        this.code = code;
    }

    public TypeException getCode() {
        return code;
    }

    public enum TypeException {
        TECHNICAL,
        SUCCESS
    }
}
