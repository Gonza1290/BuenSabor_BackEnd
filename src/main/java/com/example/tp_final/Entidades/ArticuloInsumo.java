package com.example.tp_final.Entidades;

import com.example.tp_final.DTO.ArticuloInsumoDTO;
import com.example.tp_final.DTO.ArticuloManufacturadoDTO;
import com.example.tp_final.Enumeraciones.Estado;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.io.Serializable;
@Entity
@Table(name="Articulo_Insumo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PrimaryKeyJoinColumn(referencedColumnName = "id")
//Anotaciones para consulta personalizada, mapeando en articuloInsumoDTO
@NamedNativeQuery(
        name = "ArticuloInsumo.searchsoldest",
        query = "SELECT  A.DENOMINACION AS denominación , SUM(D.CANTIDAD) AS totalVendidos\n" +
                "FROM PEDIDO AS P , PEDIDO_DETALLES_PEDIDO AS PDP, DETALLE_PEDIDO AS D, ARTICULO_INSUMO AS AI,  ARTICULO AS A\n" +
                "WHERE P.ID = PDP.PEDIDO_ID AND D.ID = PDP.DETALLES_PEDIDO_ID AND D.ARTICULO_INSUMO_ID  = AI.ID AND A.ID = AI.ID AND P.PAGADO = 'Si'\n" +
                "GROUP BY denominación\n" +
                "ORDER BY totalVendidos DESC",
        resultSetMapping = "Mapping.ArticuloInsumoDTO")
@SqlResultSetMapping(
        name = "Mapping.ArticuloInsumoDTO",
        classes = {
                @ConstructorResult(
                        targetClass = ArticuloInsumoDTO.class,
                        columns = {
                                @ColumnResult(name = "denominacion", type = String.class),
                                @ColumnResult(name = "totalVendidos", type = int.class)
                        }
                )
        }
)
public class ArticuloInsumo extends Articulo {

    private double precioCompra;
    private int stockActual;
    private int stockMinimo;

    //Relacion N a 1 con la clase UnidadMedida
    @NotNull
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private UnidadMedida unidadMedida;

    public ArticuloInsumo(String denominacion, String descripcion, String Url_Imagen, double precioVenta, Estado estadoArticulo, Rubro rubro, double precioCompra, int stockActual, int stockMinimo, UnidadMedida unidadMedida) {
        super(denominacion, descripcion, Url_Imagen, precioVenta, estadoArticulo, rubro);
        this.precioCompra = precioCompra;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.unidadMedida = unidadMedida;
    }
}
