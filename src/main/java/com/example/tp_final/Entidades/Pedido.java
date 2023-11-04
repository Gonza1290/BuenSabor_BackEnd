package com.example.tp_final.Entidades;


import com.example.tp_final.DTO.PedidoDTO;
import com.example.tp_final.Enumeraciones.EstadoPedido;
import com.example.tp_final.Enumeraciones.FormaPago;
import com.example.tp_final.Enumeraciones.Pagado;
import com.example.tp_final.Enumeraciones.TipoEnvio;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;



import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Pedido")
@NoArgsConstructor
@Getter
@Setter
@NamedNativeQuery(
        name = "Pedido.movimientosMonerios",
        query = "SELECT SUM(P.TOTAL) AS total,  SUM(P.TOTAL_COSTO) AS totalCosto, (SUM(P.TOTAL) - SUM(P.TOTAL_COSTO)) AS ganancias\n" +
                "FROM PEDIDO AS P",
        resultSetMapping = "Mapping.PedidoDTO")
@NamedNativeQuery(
        name = "Pedido.movimientosMoneriosByDate",
        query = "SELECT SUM(P.TOTAL) AS total,  SUM(P.TOTAL_COSTO) AS totalCosto, (SUM(P.TOTAL) - SUM(P.TOTAL_COSTO)) AS ganancias\n" +
                "FROM PEDIDO AS P WHERE P.FECHA_PEDIDO BETWEEN :fechaInicio AND :fechaFin",
        resultSetMapping = "Mapping.PedidoDTO")
@SqlResultSetMapping(
        name = "Mapping.PedidoDTO",
        classes = {
                @ConstructorResult(
                        targetClass = PedidoDTO.class,
                        columns = {
                                @ColumnResult(name = "total", type = double.class),
                                @ColumnResult(name = "totalCosto", type = double.class),
                                @ColumnResult(name = "ganancias", type = double.class)
                        }
                )
        }
)
public class Pedido extends Base {

    private LocalDateTime fechaPedido;
    private LocalTime horaEstimadaEntrega;
    private int horaEstimadaPreparacion;
    private double total;
    private double totalCosto;

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
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    //Relacion N a 1 con la clase domicilio
    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Domicilio domicilio;

    //Relacion 1 a 1 con la clase Factura
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Factura factura;

    //Relacion 1 a N con detalle pedido
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DetallePedido> detallesPedido = new ArrayList<>();


}