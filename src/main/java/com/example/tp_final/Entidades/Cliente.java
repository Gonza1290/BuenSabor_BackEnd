package com.example.tp_final.Entidades;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Cliente",
        uniqueConstraints = @UniqueConstraint(
                columnNames = "email"
        ))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends BaseWithDate implements Serializable {

    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String telefono;
    @NotNull
    private String email;

    //Relacion 1 a 1 con la clase Usuario
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;

    //Relacion 1 a N con domicilio
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Domicilio> domicilios = new ArrayList<>();

}
