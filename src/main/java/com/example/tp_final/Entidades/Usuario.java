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
@AllArgsConstructor

public class Usuario extends BaseWithDate implements Serializable {

    @NotNull
    private String authId;
    @NotNull
    private String username;
}
