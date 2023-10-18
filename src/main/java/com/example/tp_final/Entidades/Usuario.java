package com.example.tp_final.Entidades;

import com.example.tp_final.Enumeraciones.Estado;
import com.example.tp_final.Enumeraciones.Pagado;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Usuario")
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Usuario extends BaseWithDate implements Serializable {

    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String telefono;
    @NotNull
    private String email;
    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Estado estadoUsuario;

    //Constructor
    public Usuario(String nombre, String apellido, String telefono, String email, String password, Estado estadoUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.estadoUsuario = estadoUsuario;
    }
}
