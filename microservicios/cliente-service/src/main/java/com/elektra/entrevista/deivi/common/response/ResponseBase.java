package com.elektra.entrevista.deivi.common.response;

import java.io.Serializable;
import java.util.Map;

import com.elektra.entrevista.deivi.common.exceptions.AppException;
import lombok.Data;

@Data
public abstract class ResponseBase implements Serializable{
    private static final long serialVersionUID = 1L;
    private String statusResponse;
    private AppException.TypeException codeResponse;
    private String messageResponse;
    private Map<String, Object> parameters;
}
