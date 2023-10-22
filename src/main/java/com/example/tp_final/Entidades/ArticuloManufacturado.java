package com.example.tp_final.Entidades;

import com.example.tp_final.Enumeraciones.Estado;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Articulo_Manufacturado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(referencedColumnName = "id")

public class ArticuloManufacturado extends Articulo {

    private int tiempoEstimadoCocina; //En minutos
    private double precioCosto;
    private String receta;

    //Relacion 1 a N con DetallerArtManufacturado
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DetalleArtManufacturado> detallesArtManufacturado = new ArrayList<>();

}
