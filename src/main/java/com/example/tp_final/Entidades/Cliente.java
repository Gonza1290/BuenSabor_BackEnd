package com.example.tp_final.Entidades;

import com.example.tp_final.Enumeraciones.Estado;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Cliente")
@NoArgsConstructor
@Setter
@Getter
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Cliente extends Usuario {
    private String comentario;

    //@OneToMany(mappedBy = "cliente", cascade = CascadeType.REMOVE, orphanRemoval = true)
    //private List<Pedido> Pedidos = new ArrayList<>();

    //Constructor
    public Cliente(String nombre, String apellido, String telefono, String email, String password, Estado estadoUsuario) {
        super(nombre, apellido, telefono, email, password, estadoUsuario);
    }

}
