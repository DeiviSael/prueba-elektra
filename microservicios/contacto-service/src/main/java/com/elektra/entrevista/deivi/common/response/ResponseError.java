package com.elektra.entrevista.deivi.common.response;

import com.elektra.entrevista.deivi.common.constants.Constantes;
import com.elektra.entrevista.deivi.common.exceptions.AppException;
import com.elektra.entrevista.deivi.common.exceptions.ContactoTechnicalException;

public class ResponseError extends ResponseBase{
    public ResponseError(Exception e){
        super();
        setCodeResponse(AppException.TypeException.TECHNICAL);
        setStatusResponse(Constantes.RSP_ERROR);
        StringBuilder sb = new StringBuilder();
        sb.append(e.getMessage()).append("\n");
        if(e.getCause() != null)
            sb.append(e.getCause().toString()).append("\n").append(e.getCause().getMessage()).append("\n");
        if(e.getStackTrace() != null)
            sb.append(e.getStackTrace().toString());
        setMessageResponse(sb.toString());
    }
    public ResponseError(ContactoTechnicalException te) {
        super();
        setStatusResponse(Constantes.RSP_ERROR);
        setCodeResponse(AppException.TypeException.TECHNICAL);
        setMessageResponse(te.getMessage());
    }

    public ResponseError(String mensaje) {
        super();
        setStatusResponse(Constantes.RSP_ERROR);
        setCodeResponse(AppException.TypeException.TECHNICAL);
        setMessageResponse(mensaje);

    }
}
