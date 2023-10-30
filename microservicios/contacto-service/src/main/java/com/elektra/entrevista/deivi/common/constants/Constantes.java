package com.elektra.entrevista.deivi.common.constants;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Constantes {
    public static final String RSP_OK = "OK";
    public static final String RSP_ERROR = "ERROR";
    public static class ClienteSericeApiPath {
        ClienteSericeApiPath(){}
        public static final String GET_REQUEST_OBTENER_CLIENTE = "/api/cliente";
    }
}
