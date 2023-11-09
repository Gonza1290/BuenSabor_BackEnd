package com.example.tp_final.Entidades;

import com.example.tp_final.Enumeraciones.Estado;
import com.example.tp_final.Enumeraciones.FormaPago;
import com.example.tp_final.Enumeraciones.Pagado;
import com.example.tp_final.Enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Usuario", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario implements Serializable {

    @Id
    @GeneratedValue
    Long Id;
    @NotNull
    String username;
    String password;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
