package com.jesus.pruebatec.persona;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="persona", uniqueConstraints = {@UniqueConstraint(columnNames = {"identificacion"})})
public class Persona {
    @Id
    @GeneratedValue
    Integer idPersona;

    @Column(nullable = false)
    String nombres;

    @Column(nullable = false)
    String apellidos;

    @Column(nullable = false, unique = true)
    String identificacion;

    Date fechaNcimiento;

}
