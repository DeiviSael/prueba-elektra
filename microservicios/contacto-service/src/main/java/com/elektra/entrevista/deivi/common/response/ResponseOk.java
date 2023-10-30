package com.elektra.entrevista.deivi.common.response;

import com.elektra.entrevista.deivi.common.constants.Constantes;
import com.elektra.entrevista.deivi.common.exceptions.AppException;

import java.util.HashMap;
import java.util.Map;

public class ResponseOk extends ResponseBase{
    public ResponseOk(String mensaje){
        super();
        setCodeResponse(AppException.TypeException.SUCCESS);
        setStatusResponse(Constantes.RSP_OK);
        setMessageResponse(mensaje);
    }
    public ResponseOk(String mensaje, Object data){
        super();
        setCodeResponse(AppException.TypeException.SUCCESS);
        setStatusResponse(Constantes.RSP_OK);
        Map<String, Object> obj = new HashMap<>();
        obj.put("data", data);
        setMessageResponse(mensaje);
        setParameters(obj);
    }
}
