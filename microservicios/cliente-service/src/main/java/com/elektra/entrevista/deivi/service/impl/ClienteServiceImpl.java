package com.elektra.entrevista.deivi.service.impl;

import com.elektra.entrevista.deivi.common.exceptions.ClienteTechnicalException;
import com.elektra.entrevista.deivi.dto.ClienteRequestDto;
import com.elektra.entrevista.deivi.dto.ClienteResponseDto;
import com.elektra.entrevista.deivi.model.Cliente;
import com.elektra.entrevista.deivi.repository.ClienteRepository;
import com.elektra.entrevista.deivi.service.ClienteService;
import com.elektra.entrevista.deivi.validation.ClienteValidator;
import com.elektra.entrevista.deivi.validation.Impl.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The type Cliente service.
 */
@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {
    private static final Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);

    private final ClienteRepository clienteRepository;
    private final ModelMapper modelMapper;

    /**
     * Method createCliente for save new cliente.
     *
     * @param clienteRequestDto the data for save to cliente.
     * @return the new Cliente
     */
    @Override
    public ClienteResponseDto createCliente(ClienteRequestDto clienteRequestDto) throws ClienteTechnicalException {
        logger.info("Inicia la validación del objeto clienteRequestDto - {}", clienteRequestDto.getNombre());
        List<ClienteValidator> validators = Arrays.asList(
                new NombreValidator(),
                new FechaNacimientoValidator(),
                new SexoValidator(),
                new FechaValidaValidator(),
                new NombreUnicoValidator(this.clienteRepository)
        );
        for (ClienteValidator validator : validators) {
            validator.validate(clienteRequestDto);
        }
        logger.info("Inicia mapeo de clienteRequestDto a Cliente");
        Cliente cliente = mapDtoToCliente(clienteRequestDto);
        Cliente clienteSave = this.clienteRepository.save(cliente);
        logger.info("Se crea el Cliente id {} correctamente", clienteSave.getId());
        return this.mapClienteToDto(clienteSave);
    }

    /**
     * Method updateCliente for update cliente.
     *
     * @param clienteRequestDto the data for update to cliente.
     * @return the Cliente updated
     */
    @Override
    public ClienteResponseDto updateCliente(ClienteRequestDto clienteRequestDto) throws ClienteTechnicalException {
        logger.info("Inicia la validación del objeto clienteRequestDto - {}", clienteRequestDto.getNombre());
        List<ClienteValidator> validators = Arrays.asList(
                new NombreValidator(),
                new FechaNacimientoValidator(),
                new SexoValidator(),
                new FechaValidaValidator(),
                new NombreUnicoToUpdateValidator(this.clienteRepository)
        );
        for (ClienteValidator validator : validators) {
            validator.validate(clienteRequestDto);
        }
        logger.info("Inicia mapeo de clienteRequestDto a Cliente");
        Cliente cliente = mapDtoToCliente(clienteRequestDto);
        Cliente clienteSave = this.clienteRepository.save(cliente);
        logger.info("Se actualiza el Cliente id {} correctamente", clienteSave.getId());
        return this.mapClienteToDto(clienteSave);
    }

    /**
     * Method getAllClientes for get all clientes.
     *
     * @return all Clientes
     */
    @Override
    public List<ClienteResponseDto> getAllClientes() throws ClienteTechnicalException {
        List<Cliente> listCliente = this.clienteRepository.findAll();
        if(CollectionUtils.isEmpty(listCliente)){
            throw new ClienteTechnicalException("No se econtraron clientes");
        }
       return listCliente.stream()
               .map(this::mapClienteToDto)
               .toList();
    }
    /**
     * Method getCliente for get cliente.
     *
     * @param id the id to cliente.
     * @return the Cliente
     */
    @Override
    public ClienteResponseDto getCliente(Long id) throws ClienteTechnicalException {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        if(!cliente.isPresent()){
            throw new ClienteTechnicalException("No se econtró cliente con Id "+ id);
        }
        return this.mapClienteToDto(cliente.get());
    }

    /**
     * Method deleteCliente for delete cliente.
     *
     * @param id the id to cliente.
     */
    @Override
    public void deleteCliente(Long id) throws ClienteTechnicalException {
        logger.info("Se busca el cliente con id {}", id);
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        if(!cliente.isPresent()){
            throw new ClienteTechnicalException("No se econtró cliente con Id "+ id);
        }
        this.clienteRepository.delete(cliente.get());
        logger.info("Se elimina el cliente con id {}", id);
    }

    private Cliente mapDtoToCliente(ClienteRequestDto clienteResponseDto){
        Cliente cliente = this.modelMapper.map(clienteResponseDto, Cliente.class);
        cliente.setEstado(true);
        LocalDate fechaNacimientoLocal = LocalDate.parse(clienteResponseDto.getFechaNacimiento(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Date fechaNacimientoDate = Date.from(fechaNacimientoLocal.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        cliente.setFechaNacimiento(fechaNacimientoDate);
        return cliente;
    }
    private ClienteResponseDto mapClienteToDto(Cliente cliente){
        ClienteResponseDto clienteMap = this.modelMapper.map(cliente, ClienteResponseDto.class);
        SimpleDateFormat dateParserNacimiento  = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateParserRegistro = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fechaNacimientoStr = dateParserNacimiento.format(cliente.getFechaNacimiento());
        clienteMap.setFechaNacimiento(fechaNacimientoStr);
        if(cliente.getFechaRegistro() != null){
            String fechaRegistroStr = dateParserRegistro.format(cliente.getFechaRegistro());
            clienteMap.setFechaRegistro(fechaRegistroStr);
        }
        return clienteMap;
    }

}
