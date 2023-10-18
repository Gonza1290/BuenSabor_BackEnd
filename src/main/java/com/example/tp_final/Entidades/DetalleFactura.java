package com.example.tp_final.Entidades;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name="Detalle_Factura")
@NoArgsConstructor
@Getter
@Setter
public class DetalleFactura extends Base  {
    @NotNull
    private int cantidad;
    private double subtotal;

    //Relacion N a 1 con la clase articulo
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Articulo articulo;

}
