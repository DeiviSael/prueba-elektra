package com.elektra.entrevista.deivi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoResponseDto {
    private Long Id;
    private Long clienteId;
    private String correo;
    private String telefono;
    private Boolean estado;
    private String fechaRegistro;
}
