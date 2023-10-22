package com.example.tp_final.Entidades;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public abstract class ConfiguracionGeneral {
    public static int tiempoEstimadoCocina = 0;
    public static int cocineros = 1;
}
