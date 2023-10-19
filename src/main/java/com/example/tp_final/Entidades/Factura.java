package com.example.tp_final.Entidades;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Factura")
@NoArgsConstructor
@Getter
@Setter
public class Factura extends Base {

    private LocalDateTime fechaFacturacion;
    private double descuento;
    private double totalFinal;

    //Relacion 1 a N con detalle pedido
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DetalleFactura> detallesFactura = new ArrayList<>();

    public Factura(LocalDateTime fechaFacturacion, double totalFinal, List<DetalleFactura> detallesFactura) {
        this.fechaFacturacion = fechaFacturacion;
        this.totalFinal = totalFinal;
        this.detallesFactura = detallesFactura;
    }
}