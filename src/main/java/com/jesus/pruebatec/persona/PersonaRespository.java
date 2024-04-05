package com.jesus.pruebatec.persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface PersonaRespository extends JpaRepository<Persona, Integer> {

    Optional<Persona> findByIdentificacion(String identificacion);
    @Modifying
    @Query("update Persona p set p.nombres=:nombres," +
            " p.apellidos=:apellidos," +
            "p.identificacion=:identificacion, " +
            "p.fechaNcimiento=:fechaNcimiento " +
            "where p.idPersona = :idPersona ")
    void updatePersona(
            @Param(value = "idPersona") Integer id,
            @Param(value = "nombres") String nombres,
            @Param(value = "apellidos") String apellidos,
            @Param(value = "identificacion") String identificacion,
            @Param(value = "fechaNcimiento") Date fechaNcimiento
    );

}
