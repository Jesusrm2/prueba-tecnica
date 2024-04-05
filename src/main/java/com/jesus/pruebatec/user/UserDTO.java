package com.jesus.pruebatec.user;

import com.jesus.pruebatec.persona.Persona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    int idUser;
    String username;
    String password;
    String email;
    String sesionStatus;
    String userStatus;
    int personaId;
}

