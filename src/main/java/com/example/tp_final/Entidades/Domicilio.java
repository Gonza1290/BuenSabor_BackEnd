package com.example.tp_final.Entidades;

import com.example.tp_final.Enumeraciones.Localidad;
import com.example.tp_final.Enumeraciones.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Domicilio")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Domicilio extends Base {

    private String numero;
    private String calle;
    private String codigoPostal;
    private String nroDpt;
    private String pisoDto;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Localidad localidad;

    //Relacion N a 1 con la clase Usuario
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Usuario usuario;

}
