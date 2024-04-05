package com.jesus.pruebatec.session;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/session")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:4200"})
public class SessionController {
    private final SessionService sessionService;
    @GetMapping(value = "{id}")
    public ResponseEntity<SessionDTO> getSession(@PathVariable Integer id)
    {
        SessionDTO sessionDTO = sessionService.getSession(id);
        if (sessionDTO==null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sessionDTO);
    }

    @PutMapping()
    public ResponseEntity<SessionResponse> updatePersona(@RequestBody SessionRequest sessionRequest)
    {
        return ResponseEntity.ok(sessionService.updateSession(sessionRequest));
    }
}
