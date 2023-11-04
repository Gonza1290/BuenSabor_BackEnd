package com.example.tp_final.Entidades;

import com.example.tp_final.DTO.ArticuloInsumoDTO;
import com.example.tp_final.DTO.ClienteDTO;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Cliente",
        uniqueConstraints = @UniqueConstraint(
                columnNames = "email"
        ))
//Anotaciones para consulta personalizada, mapeando en articuloInsumoDTO
@NamedNativeQuery(
        name = "Cliente.rankingClientes",
        query = "SELECT C.NOMBRE AS nombre, COUNT (*) AS totalPedidos, SUM(P.TOTAL) AS importeTotal\n" +
                "FROM CLIENTE AS C, PEDIDO AS P\n" +
                "WHERE C.ID = P.ID_CLIENTE \n" +
                "GROUP BY nombre\n" +
                "ORDER BY totalPedidos DESC",
        resultSetMapping = "Mapping.ClienteDTO")

@NamedNativeQuery(
        name = "Cliente.rankingClientesByDate",
        query = "SELECT C.NOMBRE AS nombre, COUNT (*) AS totalPedidos, SUM(P.TOTAL) AS importeTotal\n" +
                "FROM CLIENTE AS C, PEDIDO AS P\n" +
                "WHERE C.ID = P.ID_CLIENTE AND P.FECHA_PEDIDO BETWEEN :fechaInicio AND :fechaFin \n" +
                "GROUP BY nombre\n" +
                "ORDER BY totalPedidos DESC",
        resultSetMapping = "Mapping.ClienteDTO")

@SqlResultSetMapping(
        name = "Mapping.ClienteDTO",
        classes = {
                @ConstructorResult(
                        targetClass = ClienteDTO.class,
                        columns = {
                                @ColumnResult(name = "nombre", type = String.class),
                                @ColumnResult(name = "totalPedidos", type = int.class),
                                @ColumnResult(name = "importeTotal", type = double.class)
                        }
                )
        }
)


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente extends BaseWithDate implements Serializable {

    @NotNull
    private String nombre;
    @NotNull
    private String apellido;
    @NotNull
    private String telefono;
    @NotNull
    private String email;

    //Relacion 1 a 1 con la clase Usuario
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Usuario usuario;

    //Relacion 1 a N con domicilio
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Domicilio> domicilios = new ArrayList<>();

}
