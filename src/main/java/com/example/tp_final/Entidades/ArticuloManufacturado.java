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

    //Constructor
    public ArticuloManufacturado(String denominacion, String descripcion, String Url_Imagen, double precioVenta, Estado estadoArticulo, Rubro rubro, int tiempoEstimadoCocina, String receta, List<DetalleArtManufacturado> detallesArtManufacturado) {
        super(denominacion, descripcion, Url_Imagen, precioVenta, estadoArticulo, rubro);
        this.tiempoEstimadoCocina = tiempoEstimadoCocina;
        this.receta = receta;
        this.detallesArtManufacturado = detallesArtManufacturado;
    }


    @PrePersist
    public void prePersist() {
        this.precioCosto = calcularPrecioCosto();
    }

    public double calcularPrecioCosto(){
        double precioCosto = 0;
        for (DetalleArtManufacturado detalleArtManufacturado: detallesArtManufacturado) {
            precioCosto += detalleArtManufacturado.getArticuloInsumo().getPrecioCompra() * detalleArtManufacturado.getCantidad();
        }
        return precioCosto;
    }
}
