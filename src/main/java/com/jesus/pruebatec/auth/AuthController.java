package com.jesus.pruebatec.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {
    private final AuthService authService;
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@Validated @RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }
    @PutMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@Validated @RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
    @PutMapping (value = "logout")
    public ResponseEntity<AuthResponse> logout(@Validated @RequestBody LogoutRequest request)
    {
        return ResponseEntity.ok(authService.logout(request));
    }
}