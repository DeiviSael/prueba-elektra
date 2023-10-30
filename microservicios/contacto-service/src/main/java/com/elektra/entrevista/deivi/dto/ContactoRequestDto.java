package com.elektra.entrevista.deivi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoRequestDto {
    private Long Id;
    private Long clienteId;
    private String correo;
    private String telefono;
    private Boolean estado;
}
