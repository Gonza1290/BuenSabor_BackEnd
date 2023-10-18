package com.example.tp_final.Entidades;

import com.example.tp_final.Enumeraciones.FormaPago;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Factura")
@NoArgsConstructor
@Getter
@Setter
public class Factura extends Base {

    private LocalDate fechaFacturacion;
    private double descuento;
    private double totalFinal;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    //Relacion 1 a N con detalle pedido
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DetalleFactura> detallesFactura = new ArrayList<>();

    //Constructor

    public Factura(FormaPago formaPago, List<DetalleFactura> detallesFactura) {
        this.fechaFacturacion = LocalDate.now();
        this.formaPago = formaPago;
        this.detallesFactura = detallesFactura;
    }
}