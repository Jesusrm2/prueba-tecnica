package com.jesus.pruebatec.auth;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutRequest {

    @NotNull(message = "La id usuario es obligatoria")
    int idUser;
    String sesionStatus;
}
