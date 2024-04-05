package com.jesus.pruebatec.session;
import com.jesus.pruebatec.persona.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

public interface SessionRespository extends JpaRepository<Session, Integer> {
    Optional<Session> findBySessionId(Integer id);
    @Modifying
    @Query("update Session u set u.fechaCierrre=:fechaCierrre where u.userId = :userId")
    void updateSession(
            @Param("userId") Integer id,
            @Param("fechaCierrre") LocalDateTime fechaCierrre)
            ;
}
