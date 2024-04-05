package com.jesus.pruebatec.auth;

import com.jesus.pruebatec.persona.Persona;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "El usuario es obligatorio")
    @Pattern(
            regexp = "^(?=.*\\d)(?=.*[A-Z])[a-zA-Z0-9]{8,20}$",
            message = "El nombre de usuario no es válido. Debe tener entre 8 y 20 caracteres y contener al menos una letra mayúscula y un número."
    )
    String username;
    @NotBlank(message = "La contraseña es obligatorio")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[^A-Za-z0-9\\s]).{8,}$",
            message = "La contraseña no es válida. Debe tener al menos 8 caracteres, una letra mayúscula, un número y un carácter especial."
    )
    String password;
    @NotBlank(message = "El nombre es obligatorio")
    String nombres;
    @NotBlank(message = "El apellido es obligatorio")
    String apellidos;

    @NotBlank(message = "La identificacón es obligatorio")
    @NotBlank(message = "La identificación es obligatoria")
    @Pattern(regexp = "\\d{10}", message = "La identificación debe tener 10 dígitos")
    @Pattern(regexp = "^(?!.*(.)\\1{3})\\d{10}$", message = "La identificación no debe tener letras y no debe tener cuatro numeros repetidos seguidos")
    String identificacion;
    Date fechaNcimiento;



}
