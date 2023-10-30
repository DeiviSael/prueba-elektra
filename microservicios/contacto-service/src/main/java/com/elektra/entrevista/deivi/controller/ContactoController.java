package com.elektra.entrevista.deivi.controller;

import com.elektra.entrevista.deivi.common.exceptions.ContactoTechnicalException;
import com.elektra.entrevista.deivi.common.response.ResponseBase;
import com.elektra.entrevista.deivi.common.response.ResponseError;
import com.elektra.entrevista.deivi.common.response.ResponseOk;
import com.elektra.entrevista.deivi.dto.ContactoRequestDto;
import com.elektra.entrevista.deivi.dto.ContactoResponseDto;
import com.elektra.entrevista.deivi.service.ContactoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Contacto controller.
 */
@RestController
@RequestMapping("/api/contacto")
@RequiredArgsConstructor
public class ContactoController {
    private static final Logger logger = LoggerFactory.getLogger(ContactoController.class);
    private final ContactoService contactoService;

    /**
     * Service Create contacto.
     *
     * @param contactoRequest the contacto request
     * @return the response entity
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBase> createContacto(@RequestBody ContactoRequestDto contactoRequest){
        try {
            logger.info("Inicio del servicio createContacto");
            ContactoResponseDto contactoResponse = this.contactoService.createContacto(contactoRequest);
            logger.info("Finalizo el servicio createContacto");
            return ResponseEntity.ok(new ResponseOk("Contacto creado correctamenete", contactoResponse));
        } catch (ContactoTechnicalException te) {
            return ResponseEntity.ok(new ResponseError(te));
        }
    }

    /**
     * Service Update contacto.
     *
     * @param contactoRequest the contacto request
     * @return the response entity
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseBase> updateContacto(@RequestBody ContactoRequestDto contactoRequest){
        try {
            logger.info("Inicio del servicio updateContacto");
            ContactoResponseDto contactoResponse = this.contactoService.updateContacto(contactoRequest);
            logger.info("Finalizo el servicio updateContacto");
            return ResponseEntity.ok(new ResponseOk("Contacto actualizado correctamenete", contactoResponse));
        } catch (ContactoTechnicalException te) {
            return ResponseEntity.ok(new ResponseError(te));
        }
    }

    /**
     * Service Get all contactos.
     *
     * @return the response entity
     */
    @GetMapping
    public ResponseEntity<ResponseBase> getAllContactos(){
        try {
            List<ContactoResponseDto> listContactoResponse = this.contactoService.getAllContactos();
            return ResponseEntity.ok(new ResponseOk("Contactos encontrados", listContactoResponse));
        } catch (ContactoTechnicalException te) {
            return ResponseEntity.ok(new ResponseError(te));
        }
    }

    /**
     * Service Get contacto.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<ResponseBase> getContacto(@PathVariable Long id){
        try {
            ContactoResponseDto contactoResponse = this.contactoService.getContacto(id);
            return ResponseEntity.ok(new ResponseOk("Contacto encontrado", contactoResponse));
        } catch (ContactoTechnicalException te) {
            return ResponseEntity.ok(new ResponseError(te));
        }
    }

    /**
     * Service Delete contacto.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseBase> deleteContacto(@PathVariable Long id){
        try {
            this.contactoService.deleteContacto(id);
            return ResponseEntity.ok(new ResponseOk("Contacto eliminado correctamente"));
        } catch (ContactoTechnicalException te) {
            return ResponseEntity.ok(new ResponseError(te));
        }
    }

    /**
     * Service Get all contactos por cliente.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping(path = "/cliente/{id}")
    public ResponseEntity<ResponseBase> getAllContactosPorCliente(@PathVariable Long id){
        try {
            List<ContactoResponseDto> listContactoResponse = this.contactoService.getAllContactosPorCliente(id);
            return ResponseEntity.ok(new ResponseOk("Contactos encontrados", listContactoResponse));
        } catch (ContactoTechnicalException te) {
            return ResponseEntity.ok(new ResponseError(te));
        }
    }

}
