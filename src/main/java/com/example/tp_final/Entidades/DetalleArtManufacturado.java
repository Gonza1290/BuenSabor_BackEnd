package com.example.tp_final.Entidades;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
@Entity
@Table(name="Detalle_Art_Manufacturado")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetalleArtManufacturado extends Base {

    @NotNull
    private int cantidad;

    //Relacion N a 1 con la clase ArticuloInsumo
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private ArticuloInsumo articuloInsumo;
}
