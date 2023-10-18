package com.example.tp_final.Entidades;

import com.example.tp_final.Enumeraciones.Estado;
import com.example.tp_final.Enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Entity
@Table(name="Empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Empleado extends Usuario {

    @NotNull
    @Enumerated(EnumType.STRING)
    private Rol rol;

    //Constructor

    public Empleado(String nombre, String apellido, String telefono, String email, String password, Estado estadoUsuario, Rol rol) {
        super(nombre, apellido, telefono, email, password, estadoUsuario);
        this.rol = rol;
    }
}
