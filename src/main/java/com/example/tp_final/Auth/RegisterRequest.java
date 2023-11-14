package com.example.tp_final.Auth;

import com.example.tp_final.Entidades.Domicilio;
import com.example.tp_final.Enumeraciones.Rol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    String nombre;
    String apellido;
    String telefono;
    String email;
    List<Domicilio> domicilios;

}
