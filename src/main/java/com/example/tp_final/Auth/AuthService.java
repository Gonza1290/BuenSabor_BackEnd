package com.example.tp_final.Auth;

import com.example.tp_final.Entidades.Persona;
import com.example.tp_final.Entidades.Usuario;
import com.example.tp_final.Enumeraciones.Rol;
import com.example.tp_final.Jwt.JwtService;
import com.example.tp_final.Repositories.PersonaRepository;
import com.example.tp_final.Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepository;
    private final PersonaRepository personaRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public AuthResponse register(RegisterRequest request) {

        Usuario user = Usuario.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode( request.getPassword()))
                .rol(Rol.Cliente)
                .build();
        Persona persona = Persona.builder()
                .nombre(request.nombre)
                .apellido(request.apellido)
                .email(request.email)
                .telefono(request.telefono)
                .domicilios(request.domicilios)
                .usuario(user)
                .build();

        personaRepository.save(persona);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}
