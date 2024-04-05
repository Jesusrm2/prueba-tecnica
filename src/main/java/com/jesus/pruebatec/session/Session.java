package com.jesus.pruebatec.session;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue
    Integer sessionId;

    LocalDateTime fechaIngreso;

    LocalDateTime fechaCierrre;
    @NotNull
    Integer userId;
}
