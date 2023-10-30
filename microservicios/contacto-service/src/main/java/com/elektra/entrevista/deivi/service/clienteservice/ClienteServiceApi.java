package com.elektra.entrevista.deivi.service.clienteservice;

import com.elektra.entrevista.deivi.common.constants.Constantes;
import com.elektra.entrevista.deivi.common.exceptions.ContactoTechnicalException;
import com.elektra.entrevista.deivi.common.response.ClienteResponse;
import com.elektra.entrevista.deivi.dto.ClienteResponseDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class ClienteServiceApi {
    private static final Logger logger = LoggerFactory.getLogger(ClienteServiceApi.class);

    private final WebClient.Builder webClientBuilder;
    public ClienteResponse<ClienteResponseDto> obetenerCliente(Long clienteId)
            throws ContactoTechnicalException {
        try {
            logger.info("Consulta al servicio cliente-service en busca del cliente - {}", clienteId);
            return webClientBuilder.build().get()
                    .uri("http://cliente-service/"+Constantes.ClienteSericeApiPath.GET_REQUEST_OBTENER_CLIENTE+ "/"+clienteId)
                    .headers(h -> {
                        h.add("content-type", "application/json");
                    })
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<ClienteResponse<ClienteResponseDto>>() {
                    }).block();
        }catch (Exception ex){
            logger.error("Error al consultar servicio cliente-service en busca el cliente - {}", clienteId);
            throw new ContactoTechnicalException("Ocurrio un error al consultar el servicio cliente");
        }
    }
}
