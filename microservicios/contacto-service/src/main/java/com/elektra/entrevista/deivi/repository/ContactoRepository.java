package com.elektra.entrevista.deivi.repository;

import com.elektra.entrevista.deivi.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto, Long> {
    Boolean existsByIdAndClienteId(Long id, Long clienteId);
    Boolean existsByClienteIdAndCorreo(Long clienteId, String correo);
    Boolean existsByClienteIdAndTelefono(Long clienteId, String telefono);
    List<Contacto> findAllByClienteId(Long clienteId);
}
