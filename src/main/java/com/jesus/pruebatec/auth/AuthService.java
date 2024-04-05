package com.jesus.pruebatec.auth;

import com.jesus.pruebatec.jwt.JwtService;
import com.jesus.pruebatec.persona.Persona;
import com.jesus.pruebatec.persona.PersonaRespository;
import com.jesus.pruebatec.session.Session;
import com.jesus.pruebatec.session.SessionRespository;
import com.jesus.pruebatec.user.Role;
import com.jesus.pruebatec.user.User;
import com.jesus.pruebatec.user.UserRepository;
import com.jesus.pruebatec.user.UserResponse;
import jakarta.transaction.Status;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PersonaRespository personaRespository;
    private final SessionRespository sessionRespository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /*
    @Transactional
public AuthResponse login(LoginRequest request) {
    User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

    // Verificar si el usuario tiene una sesión activa
    if ("A".equals(user.getSesionStatus())) {
        return AuthResponse.builder()
                .message("Ya tiene una sesión activa")
                .build();
    }

    // Verificar si la cuenta del usuario está bloqueada
    if ("I".equals(user.getUserStatus())) {
        return AuthResponse.builder()
                .message("La cuenta está bloqueada")
                .build();
    }

    // Verificar la contraseña
    if (!request.getPassword().equals(user.getPassword())) {
        // Incrementar el número de intentos fallidos
        user.setIntentosFallidos(user.getIntentosFallidos() + 1);
        if (user.getIntentosFallidos() >= 3) {
            // Bloquear la cuenta si excede los 3 intentos fallidos
            user.setUserStatus("I");
            userRepository.save(user);
            return AuthResponse.builder()
                    .message("Ha excedido el número de intentos permitidos. La cuenta ha sido bloqueada.")
                    .build();
        }
        userRepository.save(user);
        return AuthResponse.builder()
                .message("Contraseña incorrecta. Intentos restantes: " + (3 - user.getIntentosFallidos()))
                .build();
    }

    // Restablecer el contador de intentos fallidos si la contraseña es correcta
    user.setIntentosFallidos(0);
    userRepository.save(user);

    // Resto del código para el inicio de sesión exitoso
    userRepository.updateUser(user.getIdUser(), "A");
    Session session = Session.builder()
            .fechaIngreso(LocalDateTime.now())
            .fechaCierrre(null)
            .userId(user.getIdUser())
            .build();
    System.out.println(session);
    sessionRespository.save(session);
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    UserDetails user2 = userRepository.findByUsername(request.getUsername()).orElseThrow();
    String token=jwtService.getToken(user2);
    return AuthResponse.builder()
            .token(token)
            .data(user.getIdUser().toString())
            .build();
}

     */
    @Transactional
    public AuthResponse login(LoginRequest request) {
        User userSesion = userRepository.findByUsername(request.getUsername()).orElseThrow();
        if ("A".equals(userSesion.getSesionStatus())) {
            return AuthResponse.builder()
                    .message("Ya tiene una sesión activa")
                    .build();
        }
        if ("I".equals(userSesion.getUserStatus())){
            return AuthResponse.builder()
                    .message("Cuenta esta bloqueada")
                    .build();
        }
        if (!passwordEncoder.matches(request.getPassword(), userSesion.getPassword())) {
            userSesion.setNumeroIntento(userSesion.getNumeroIntento() + 1);
            if (userSesion.getNumeroIntento() >= 4) {
                userSesion.setUserStatus("I");
                userRepository.save(userSesion);
                userSesion.setNumeroIntento(0);
                return AuthResponse.builder()
                        .message("Ha excedido el número de intentos permitidos. La cuenta ha sido bloqueada.")
                        .build();
            }
            return AuthResponse.builder()
                    .message("Contraseña incorrecta. Intentos restantes: " + (4 - userSesion.getNumeroIntento() ))
                    .build();
            }
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        userRepository.updateUser(user.getIdUser(), "A");

        Session session = Session.builder()
                .fechaIngreso(LocalDateTime.now())
                .fechaCierrre(null)
                .userId(user.getIdUser())
                .build();
        System.out.println(session);
        sessionRespository.save(session);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user2 = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user2);
        return AuthResponse.builder()
                .token(token)
                .data(user.getIdUser().toString())
                .build();

    }
    @Transactional
    public AuthResponse logout(LogoutRequest request) {
        SecurityContextHolder.clearContext();
        User user = userRepository.findByIdUser(request.getIdUser()).orElseThrow();
        userRepository.updateUser(user.getIdUser(), "I");
        sessionRespository.updateSession(user.getIdUser(), LocalDateTime.now());
        System.out.println("Este es mi id: " + user.getIdUser());
        return AuthResponse.builder().message("Cierrre de sesión exitoso").build();
    }
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return AuthResponse.builder()
                    .message("El nombre de usuario ya está en uso")
                    .build();
        }
        if (personaRespository.findByIdentificacion(request.getIdentificacion()).isPresent()) {
            return AuthResponse.builder()
                    .message("Usuario ya registrado solo puede tener 1 cuenta como maximo")
                    .build();
        }

        String email = generateUniqueEmail(request.getNombres(), request.getApellidos());
        Persona persona = Persona.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .identificacion(request.getIdentificacion())
                .fechaNcimiento(request.getFechaNcimiento())
                .build();


        personaRespository.save(persona);
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(email)
                .sesionStatus("I")
                .userStatus("A")
                .personaId(persona.getIdPersona())
                .role(Role.USER)
                .build();
        userRepository.save(user);



        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .message("Se ha registrado exitosamente")
                .data(user.getEmail())
                .build();
    }

    private String generateUniqueEmail(String nombres, String apellidos) {
        String firstLetter = nombres.substring(0, 1).toLowerCase();
        String[] apellidosArray = apellidos.split(" ");
        String baseEmail = firstLetter + apellidosArray[0].toLowerCase() + "@mail.com";
        String[] parts = baseEmail.split("@");
        String username = parts[0];
        String domain = parts[1];

        String email = baseEmail;
        int counter = 1;
        while (userRepository.findByEmail(email).isPresent()) {
            email = username + counter + "@" + domain;
            counter++;
        }
        return email;
    }
}