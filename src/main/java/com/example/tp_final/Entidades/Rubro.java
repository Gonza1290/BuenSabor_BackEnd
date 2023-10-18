package com.example.tp_final.Entidades;

import com.example.tp_final.Enumeraciones.Estado;
import com.example.tp_final.Enumeraciones.TipoRubro;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table(name="Rubro")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rubro extends BaseWithDate {

    private String denominacion;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Estado estadoRubro;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoRubro tipoRubro;

}