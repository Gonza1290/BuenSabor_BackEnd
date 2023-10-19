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

    @NotNull
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

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


}