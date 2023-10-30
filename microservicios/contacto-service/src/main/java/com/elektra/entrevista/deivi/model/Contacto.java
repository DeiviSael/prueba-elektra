package com.elektra.entrevista.deivi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "t_contacto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContactoId")
    private Long id;

    @Column(name = "ClienteId")
    private Long clienteId;

    @Column(name = "Correo")
    private String correo;

    @Column(name = "Telefono")
    private String telefono;

    @Column(name = "Estado")
    private Boolean estado;

    @CreationTimestamp
    @Column(name = "FechaRegistro", updatable = false)
    private Timestamp fechaRegistro;

}
