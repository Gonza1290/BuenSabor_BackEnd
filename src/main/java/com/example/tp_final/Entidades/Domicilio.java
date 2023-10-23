package com.example.tp_final.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name="Domicilio",
        uniqueConstraints = @UniqueConstraint(
                columnNames = "numero"
        ))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Domicilio extends Base {

    private String numero;
    private String calle;
    private String codigoPostal;
    private String nroDpt;
    private String pisoDto;

    //Relacion N a 1 con la clase Localidad
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    private Localidad localidad;

}
