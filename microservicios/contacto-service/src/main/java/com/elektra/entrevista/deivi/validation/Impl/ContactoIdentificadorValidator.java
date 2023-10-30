package com.elektra.entrevista.deivi.validation.Impl;

import com.elektra.entrevista.deivi.common.exceptions.ContactoTechnicalException;
import com.elektra.entrevista.deivi.dto.ContactoRequestDto;
import com.elektra.entrevista.deivi.repository.ContactoRepository;
import com.elektra.entrevista.deivi.validation.ContactoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactoIdentificadorValidator implements ContactoValidator {
    private static final Logger logger = LoggerFactory.getLogger(ContactoIdentificadorValidator.class);
    private ContactoRepository contactoRepository;
    public ContactoIdentificadorValidator(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }
    @Override
    public void validate(ContactoRequestDto contactoRequest) throws ContactoTechnicalException {
        if(contactoRequest.getId() == null){
            logger.error("Identificador del contacto invalido");
            throw new ContactoTechnicalException("Verifique el identificador de contacto");
        }
        if(!contactoRepository.existsByIdAndClienteId(contactoRequest.getId(), contactoRequest.getClienteId())){
            logger.error("Verifique los identificadores ingresados, contactoId {}, clienteId {} ",
                    contactoRequest.getId(), contactoRequest.getClienteId());
            throw new ContactoTechnicalException("Verifique los identificadores ingresados");
        }
    }
}
