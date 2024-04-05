package com.jesus.pruebatec.session;

import com.jesus.pruebatec.auth.AuthResponse;
import com.jesus.pruebatec.auth.RegisterRequest;
import com.jesus.pruebatec.persona.*;
import com.jesus.pruebatec.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRespository sessionRespository;
    @Transactional
    public SessionResponse updateSession(SessionRequest request){
        return new SessionResponse("Session actualizada actualizada");
    }
    public SessionDTO getSession(Integer id) {
        Session s= sessionRespository.findBySessionId(id).orElse(null);
        if (s!=null)
        {
            SessionDTO sessionDTO = SessionDTO.builder()
                    .sessionId(s.sessionId)
                    .fechaIngreso(s.fechaIngreso)
                    .fechaCierrre(s.fechaCierrre)
                    .userId(s.userId)

                    .build();
            return sessionDTO;
        }
        return null;
    }
}
