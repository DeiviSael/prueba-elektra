package com.elektra.entrevista.deivi.controller;

import com.elektra.entrevista.deivi.common.exceptions.ClienteTechnicalException;
import com.elektra.entrevista.deivi.common.response.ResponseBase;
import com.elektra.entrevista.deivi.common.response.ResponseError;
import com.elektra.entrevista.deivi.common.response.ResponseOk;
import com.elektra.entrevista.deivi.dto.ClienteRequestDto;
import com.elektra.entrevista.deivi.dto.ClienteResponseDto;
import com.elektra.entrevista.deivi.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Cliente controller.
 */
@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    private final ClienteService clienteService;

    /**
     * Service Create cliente.
     *
     * @param clienteRequest the cliente request
     * @return the response entity
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBase> createCliente(@RequestBody ClienteRequestDto clienteRequest){
        try {
            logger.info("Inicio del servicio createCliente");
            ClienteResponseDto clienteResponse = this.clienteService.createCliente(clienteRequest);
            logger.info("Finalizo el servicio createCliente");
            return ResponseEntity.ok(new ResponseOk("Cliente creado correctamenete", clienteResponse));
        } catch (ClienteTechnicalException te) {
            return ResponseEntity.ok(new ResponseError(te));
        }
    }

    /**
     * Service Update cliente.
     *
     * @param clienteRequest the cliente request
     * @return the response entity
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBase> updateCliente(@RequestBody ClienteRequestDto clienteRequest){
        try {
            logger.info("Inicio del servicio updateCliente");
            ClienteResponseDto clienteResponse = this.clienteService.updateCliente(clienteRequest);
            logger.info("Finalizo el servicio updateCliente");
            return ResponseEntity.ok(new ResponseOk("Cliente actualizado correctamenete", clienteResponse));
        } catch (ClienteTechnicalException te) {
            return ResponseEntity.ok(new ResponseError(te));
        }
    }

    /**
     * Service Get all clientes.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<ResponseBase> getAllClientes(){
        try {
            List<ClienteResponseDto> listClienteResponse = this.clienteService.getAllClientes();
            return ResponseEntity.ok(new ResponseOk("Clientes encontrados", listClienteResponse));
        } catch (ClienteTechnicalException te) {
            return ResponseEntity.ok(new ResponseError(te));
        }
    }

    /**
     * Service Get cliente.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseBase> getCliente(@PathVariable Long id){
        try {
            ClienteResponseDto clienteResponse = this.clienteService.getCliente(id);
            return ResponseEntity.ok(new ResponseOk("Cliente encontrado", clienteResponse));
        } catch (ClienteTechnicalException te) {
            return ResponseEntity.ok(new ResponseError(te));
        }
    }

    /**
     * Service Delete cliente.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseBase> deleteCliente(@PathVariable Long id){
        try {
            this.clienteService.deleteCliente(id);
            return ResponseEntity.ok(new ResponseOk("Cliente eliminado correctamente"));
        } catch (ClienteTechnicalException te) {
            return ResponseEntity.ok(new ResponseError(te));
        }
    }

}
