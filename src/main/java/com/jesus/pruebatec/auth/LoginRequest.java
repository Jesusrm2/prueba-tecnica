package com.jesus.pruebatec.auth;

import com.jesus.pruebatec.user.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "El usuario es obligatorio")
    String username;
    @NotBlank(message = "La contraseña es obligatorio")
    String password;
}
