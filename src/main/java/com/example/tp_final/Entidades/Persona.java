package com.example.tp_final.Entidades;

import com.example.tp_final.DTO.ClienteDTO;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Persona",
        uniqueConstraints = @UniqueConstraint(
                columnNames = "email"
        ))
//Anotaciones para consulta personalizada, mapeando en ClienteDTO
@NamedNativeQuery(
        name = "Persona.rankingClientes",
        query = "SELECT PERSONA.ID, PERSONA.NOMBRE AS nombre, COUNT (*) AS totalPedidos, SUM(PEDIDO.TOTAL) AS importeTotal\n" +
                "FROM PERSONA, PEDIDO, USUARIO AS U\n" +
                "WHERE PERSONA.ID = PEDIDO.ID_PERSONA AND U.ID = PERSONA. USUARIO_ID AND U.ROL = 'Cliente'\n" +
                "GROUP BY PERSONA.ID\n" +
                "ORDER BY totalPedidos DESC",
        resultSetMapping = "Mapping.ClienteDTO")

@NamedNativeQuery(
        name = "Persona.rankingClientesByDate",
        query = "SELECT PERSONA.ID, PERSONA.NOMBRE AS nombre, COUNT (*) AS totalPedidos, SUM(PEDIDO.TOTAL) AS importeTotal\n" +
                "FROM PERSONA, PEDIDO, USUARIO AS U\n" +
                "WHERE PERSONA.ID = PEDIDO.ID_PERSONA AND U.ID = PERSONA. USUARIO_ID AND U.ROL = 'Cliente' AND PEDIDO.FECHA_PEDIDO BETWEEN :fechaInicio AND :fechaFin \n" +
                "GROUP BY PERSONA.ID\n" +
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
public class Persona extends BaseWithDate implements Serializable {

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
