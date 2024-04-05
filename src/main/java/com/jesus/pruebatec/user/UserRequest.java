package com.jesus.pruebatec.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    int idUser;
    String username;
    String password;
    String email;
    Role status;
    String sesionStatus;
    int personaId;
}
