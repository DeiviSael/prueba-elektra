package com.elektra.entrevista.deivi.service.impl;

import com.elektra.entrevista.deivi.common.exceptions.ContactoTechnicalException;
import com.elektra.entrevista.deivi.dto.ContactoRequestDto;
import com.elektra.entrevista.deivi.dto.ContactoResponseDto;
import com.elektra.entrevista.deivi.model.Contacto;
import com.elektra.entrevista.deivi.repository.ContactoRepository;
import com.elektra.entrevista.deivi.service.ContactoService;
import com.elektra.entrevista.deivi.service.clienteservice.ClienteServiceApi;
import com.elektra.entrevista.deivi.validation.ContactoValidator;
import com.elektra.entrevista.deivi.validation.Impl.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The type Contacto service.
 */
@Service
@RequiredArgsConstructor
public class ContactoServiceImpl implements ContactoService {
    private static final Logger logger = LoggerFactory.getLogger(ContactoServiceImpl.class);

    private final ContactoRepository contactoRepository;
    private final ModelMapper modelMapper;
    private final ClienteServiceApi clienteServiceApi;

    /**
     * Method createContacto for save new contacto.
     *
     * @param contactoRequestDto the data for save to contacto.
     * @return the new contacto
     */
    @Override
    public ContactoResponseDto createContacto(ContactoRequestDto contactoRequestDto) throws ContactoTechnicalException {
        logger.info("Inicia la validación del objeto contactoRequestDto - clienteId {}", contactoRequestDto.getClienteId());
        List<ContactoValidator> validators = Arrays.asList(
                new ClienteIdentificadorValidator(this.clienteServiceApi),
                new DatosContactoValidator(),
                new CorreoValidator(this.contactoRepository),
                new TelefonoValidator(this.contactoRepository)
        );
        for (ContactoValidator validator : validators) {
            validator.validate(contactoRequestDto);
        }
        logger.info("Inicia mapeo de contactoRequestDto a Contacto");
        Contacto contacto = mapDtoToContacto(contactoRequestDto);
        Contacto contactoSave = this.contactoRepository.save(contacto);
        logger.info("Se crea el Contacto id {} correctamente", contactoSave.getId());
        return this.mapContactoToDto(contactoSave);
    }

    /**
     * Method updateContacto for update contacto.
     *
     * @param contactoRequestDto the data for update to cliente.
     * @return the cliente updated
     */
    @Override
    public ContactoResponseDto updateContacto(ContactoRequestDto contactoRequestDto) throws ContactoTechnicalException {
        logger.info("Inicia la validación del objeto contactoRequestDto - clienteId {} - contactoId {}",
                contactoRequestDto.getClienteId(), contactoRequestDto.getId());
        List<ContactoValidator> validators = Arrays.asList(
                new ClienteIdentificadorValidator(this.clienteServiceApi),
                new DatosContactoValidator(),
                new ContactoIdentificadorValidator(this.contactoRepository),
                new CorreoValidator(this.contactoRepository),
                new TelefonoValidator(this.contactoRepository)
        );
        for (ContactoValidator validator : validators) {
            validator.validate(contactoRequestDto);
        }
        logger.info("Inicia mapeo de contactoRequestDto a Contacto");
        Contacto contacto = mapDtoToContacto(contactoRequestDto);
        Contacto contactoSave = this.contactoRepository.save(contacto);
        logger.info("Se actualiza el Contacto id {} Cliente Id {} correctamente", contactoSave.getId(), contactoSave.getClienteId());
        return this.mapContactoToDto(contactoSave);
    }

    /**
     * Method getAllContactos for get all contactos.
     *
     * @return all Contactos
     */
    @Override
    public List<ContactoResponseDto> getAllContactos() throws ContactoTechnicalException {
        List<Contacto> listContacto = this.contactoRepository.findAll();
        if(CollectionUtils.isEmpty(listContacto)){
            throw new ContactoTechnicalException("No se econtraron contactos");
        }
        return listContacto.stream()
                .map(this::mapContactoToDto)
                .toList();
    }

    /**
     * Method getContacto for get contacto.
     *
     * @param id the id to contacto.
     * @return the Contacto
     */
    @Override
    public ContactoResponseDto getContacto(Long id) throws ContactoTechnicalException {
        Optional<Contacto> contacto = this.contactoRepository.findById(id);
        if(!contacto.isPresent()){
            throw new ContactoTechnicalException("No se econtró contacto con Id "+ id);
        }
        return this.mapContactoToDto(contacto.get());
    }

    /**
     * Method deleteContacto for delete contacto.
     *
     * @param id the id to contacto.
     */
    @Override
    public void deleteContacto(Long id) throws ContactoTechnicalException {
        logger.info("Se busca el contacto con id {}", id);
        Optional<Contacto> cliente = this.contactoRepository.findById(id);
        if(!cliente.isPresent()){
            throw new ContactoTechnicalException("No se econtró contacto con Id "+ id);
        }
        this.contactoRepository.delete(cliente.get());
        logger.info("Se elimina el contacto con id {}", id);
    }

    /**
     * Method getAllContactosPorCliente for get cliente with all contacto.
     *
     * @param clienteId the id to cliente.
     * @return list Contacto
     */
    @Override
    public List<ContactoResponseDto> getAllContactosPorCliente(Long clienteId) throws ContactoTechnicalException {
        List<Contacto> listContacto = this.contactoRepository.findAllByClienteId(clienteId);
        if(CollectionUtils.isEmpty(listContacto)){
            throw new ContactoTechnicalException("No se econtraron contactos");
        }
        return listContacto.stream()
                .map(this::mapContactoToDto)
                .toList();
    }

    private Contacto mapDtoToContacto(ContactoRequestDto contactoResponseDto){
        Contacto contacto = this.modelMapper.map(contactoResponseDto, Contacto.class);
        contacto.setEstado(true);
        return contacto;
    }
    private ContactoResponseDto mapContactoToDto(Contacto contacto){
        ContactoResponseDto contactoMap = this.modelMapper.map(contacto, ContactoResponseDto.class);
        SimpleDateFormat dateParserRegistro = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(contacto.getFechaRegistro() != null){
            String fechaRegistroStr = dateParserRegistro.format(contacto.getFechaRegistro());
            contactoMap.setFechaRegistro(fechaRegistroStr);
        }
        return contactoMap;
    }
}
