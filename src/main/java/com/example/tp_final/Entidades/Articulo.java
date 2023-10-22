package com.example.tp_final.Entidades;

import com.example.tp_final.Enumeraciones.Estado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;

@Entity
@Table(name="Articulo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Articulo extends BaseWithDate {

    private String denominacion;
    private String descripcion;
    private String Url_Imagen;
    private double precioVenta;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Estado estadoArticulo;

    //Relacion N a 1 con la clase Rubro
    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Rubro rubro;

}
