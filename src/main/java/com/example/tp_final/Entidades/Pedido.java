package com.example.tp_final.Entidades;

import com.example.tp_final.Enumeraciones.EstadoPedido;
import com.example.tp_final.Enumeraciones.FormaPago;
import com.example.tp_final.Enumeraciones.Pagado;
import com.example.tp_final.Enumeraciones.TipoEnvio;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Pedido")
@NoArgsConstructor
@Getter
@Setter
public class Pedido extends Base {

    private LocalDateTime fechaPedido;
    private LocalTime horaEstimadaEntrega;
    private double total;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Pagado pagado;

    @NotNull
    @Enumerated(EnumType.STRING) //Anotacion que indica a JPA que los valores de la enumeración se deben almacenar como cadenas de caracteres (strings) en la base de datos.
    private EstadoPedido estadoPedido;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;

    //Relacion N a 1 con la clase Cliente
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    //Relacion 1 a 1 con la clase Factura
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Factura factura;

    //Relacion 1 a N con detalle pedido
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DetallePedido> detallesPedido = new ArrayList<>();

    //Constructor
    public Pedido(Pagado pagado, EstadoPedido estadoPedido, TipoEnvio tipoEnvio, Cliente cliente, List<DetallePedido> detallesPedido, FormaPago formaPago) {
        this.fechaPedido = LocalDateTime.now();
        this.pagado = pagado;
        this.estadoPedido = estadoPedido;
        this.tipoEnvio = tipoEnvio;
        this.cliente = cliente;
        this.detallesPedido = detallesPedido;
        this.total = calcularTotal();
        this.factura = emitirFactura(formaPago);
    }

    public Factura emitirFactura (FormaPago formaPago){
        List<DetalleFactura> detallesFactura = new ArrayList<>();
        for (DetallePedido detallePedido:detallesPedido) {
            DetalleFactura detalleFactura = new DetalleFactura();
            detalleFactura.setCantidad(detallePedido.getCantidad());
            detalleFactura.setArticulo(detallePedido.getArticulo());
        }
        Factura factura = new Factura(formaPago,detallesFactura);
        //Si el tipo envio es retira entonces se aplica un 10% de descuento
        if (this.tipoEnvio.name()=="retira") {
            factura.setDescuento(0.1);
        }else {
            factura.setDescuento(0);
        }
        factura.setTotalFinal(this.total -this.total * factura.getDescuento());
        return factura;
    }

    public double calcularTotal() {
        double Total = 0;
        // Calcular el total del pedido con la suma de los subtotales de los detalles pedido
        for (DetallePedido detalleP : detallesPedido) {
            Total += detalleP.getSubtotal();
        }
        return Total;
    }

}