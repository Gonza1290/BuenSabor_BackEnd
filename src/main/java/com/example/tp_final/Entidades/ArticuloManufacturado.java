package com.example.tp_final.Entidades;

import com.example.tp_final.DTO.ArticuloManufacturadoDTO;
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
//Anotaciones para consulta personalizada, mapeando en articuloManufacturadoDTO
@NamedNativeQuery(
        name = "ArticuloManufacturado.searchsoldest",
        query = "SELECT A.DENOMINACION AS denominacion, SUM(D.CANTIDAD) AS totalVendidos " +
                "FROM PEDIDO AS P, PEDIDO_DETALLES_PEDIDO AS PDP, DETALLE_PEDIDO AS D, " +
                "ARTICULO_MANUFACTURADO AS AM, ARTICULO AS A " +
                "WHERE P.ID = PDP.PEDIDO_ID AND D.ID = PDP.DETALLES_PEDIDO_ID " +
                "AND D.ARTICULO_MANUFACTURADO_ID = AM.ID AND A.ID = AM.ID AND P.PAGADO = 'Si'" +
                "GROUP BY denominacion ORDER BY totalVendidos DESC",
        resultSetMapping = "Mapping.ArticuloManufacturadoDTO")
@SqlResultSetMapping(
        name = "Mapping.ArticuloManufacturadoDTO",
        classes = {
                @ConstructorResult(
                        targetClass = ArticuloManufacturadoDTO.class,
                        columns = {
                                @ColumnResult(name = "denominacion", type = String.class),
                                @ColumnResult(name = "totalVendidos", type = int.class)
                        }
                )
        }
)
public class ArticuloManufacturado extends Articulo {

    private int tiempoEstimadoCocina; //En minutos
    private double precioCosto;
    private String receta;

    //Relacion 1 a N con DetallerArtManufacturado
    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DetalleArtManufacturado> detallesArtManufacturado = new ArrayList<>();

    public ArticuloManufacturado(String denominacion, String descripcion, String Url_Imagen, double precioVenta, Estado estadoArticulo, Rubro rubro, int tiempoEstimadoCocina, double precioCosto, String receta, List<DetalleArtManufacturado> detallesArtManufacturado) {
        super(denominacion, descripcion, Url_Imagen, precioVenta, estadoArticulo, rubro);
        this.tiempoEstimadoCocina = tiempoEstimadoCocina;
        this.precioCosto = precioCosto;
        this.receta = receta;
        this.detallesArtManufacturado = detallesArtManufacturado;
    }
}
