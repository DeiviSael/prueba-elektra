package com.elektra.entrevista.deivi.common.response;

import lombok.Data;

@Data
public class ClienteResponse<R> {
    private String statusResponse;
    private String codeResponse;
    private String messageResponse;
    private Parameters<R> parameters;

    @Data
    public static class Parameters<T> {
        private T data;
    }
}
