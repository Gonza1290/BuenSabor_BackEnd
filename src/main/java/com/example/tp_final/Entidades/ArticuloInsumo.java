package com.example.tp_final.Entidades;

import com.example.tp_final.Enumeraciones.Estado;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
@Entity
@Table(name="Articulo_Insumo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(referencedColumnName = "id")

public class ArticuloInsumo extends Articulo {

    private double precioCompra;
    private int stockActual;
    private int stockMinimo;

    //Relacion N a 1 con la clase UnidadMedida
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private UnidadMedida unidadMedida;

    public ArticuloInsumo(String denominacion, String descripcion, String Url_Imagen, double precioVenta, Estado estadoArticulo, Rubro rubro, double precioCompra, int stockActual, int stockMinimo, UnidadMedida unidadMedida) {
        super(denominacion, descripcion, Url_Imagen, precioVenta, estadoArticulo, rubro);
        this.precioCompra = precioCompra;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.unidadMedida = unidadMedida;
    }
}
