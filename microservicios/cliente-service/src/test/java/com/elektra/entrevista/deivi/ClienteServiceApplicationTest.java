package com.elektra.entrevista.deivi;

import com.elektra.entrevista.deivi.common.exceptions.ClienteTechnicalException;
import com.elektra.entrevista.deivi.dto.ClienteRequestDto;
import com.elektra.entrevista.deivi.dto.ClienteResponseDto;
import com.elektra.entrevista.deivi.service.ClienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class ClienteServiceApplicationTest {
    @Autowired
    private ClienteService clienteService;

    @Test
    public void testCreateCliente() throws ClienteTechnicalException {
        // Crear un objeto ClienteRequestDto de prueba
        ClienteRequestDto requestDto = new ClienteRequestDto();
        // Ten en cuenta que si existe ya el nombre en la BD arrojará la Exception ClienteTechnicalException
        // Puedes probar el test de prueba testCreateClienteWithDuplicateName
        requestDto.setNombre("TestDev");
        requestDto.setApellidoPaterno("Perez");
        requestDto.setApellidoMaterno("Gomez");
        requestDto.setFechaNacimiento("1990-01-15");
        requestDto.setSexo("M");

        ClienteResponseDto responseDto = clienteService.createCliente(requestDto);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(requestDto.getNombre(), responseDto.getNombre());
        Assertions.assertEquals(requestDto.getApellidoPaterno(), responseDto.getApellidoPaterno());
        Assertions.assertEquals(requestDto.getApellidoMaterno(), responseDto.getApellidoMaterno());

    }
    @Test
    public void testCreateClienteWithDuplicateName() {
        // Crear un objeto ClienteRequestDto de prueba con un nombre que ya existe
        ClienteRequestDto requestDto = new ClienteRequestDto();
        requestDto.setNombre("TestDev");
        requestDto.setApellidoPaterno("Perez");
        requestDto.setApellidoMaterno("Gomez");
        requestDto.setFechaNacimiento("1990-01-15");
        requestDto.setSexo("M");

        // Intenta crear el cliente y verifica que se lance la excepción
        Assertions.assertThrows(ClienteTechnicalException.class, () -> {
            clienteService.createCliente(requestDto);
        }, "No existe el Nombre del cliente en la BD");
    }

}
