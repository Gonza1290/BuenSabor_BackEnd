package com.example.tp_final.Entidades;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Table(name="Detalle_Pedido")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DetallePedido extends Base {

    @NotNull
    private int cantidad;
    private double subtotal;

    //Relacion N a 1 con la clase articulo
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Articulo articulo;

    //Constructor
    public DetallePedido(int cantidad, Articulo articulo) {
        this.cantidad = cantidad;
        this.articulo = articulo;
        this.subtotal= calcularSubTotal();
    }
    //Metodos
    public double calcularSubTotal (){
        double subtotal = this.articulo.getPrecioVenta() * cantidad;
        return subtotal;
    }
}

