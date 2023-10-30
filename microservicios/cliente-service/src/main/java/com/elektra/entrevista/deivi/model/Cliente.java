package com.elektra.entrevista.deivi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "t_cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClienteId")
    private Long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "ApellidoPaterno")
    private String apellidoPaterno;

    @Column(name = "ApellidoMaterno")
    private String apellidoMaterno;

    @Column(name = "FechaNacimiento")
    private Date fechaNacimiento;

    @Column(name = "Sexo")
    private String sexo;

    @Column(name = "Estado")
    private Boolean estado;

    @CreationTimestamp
    @Column(name = "FechaRegistro", updatable = false)
    private Timestamp fechaRegistro;

}
