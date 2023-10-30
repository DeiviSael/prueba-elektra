package com.elektra.entrevista.deivi.repository;

import com.elektra.entrevista.deivi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Boolean existsByNombre(String nombre);
    Optional<Cliente> findByNombre(String nombre);
    boolean existsById(Long id);

}
