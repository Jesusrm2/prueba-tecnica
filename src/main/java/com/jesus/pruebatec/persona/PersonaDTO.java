package com.jesus.pruebatec.persona;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonaDTO {
    int idPersona;
    String nombres;
    String apellidos;
    String identificacion;
    Date fechaNcimiento;
}
