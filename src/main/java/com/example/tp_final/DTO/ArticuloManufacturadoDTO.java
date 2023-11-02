package com.example.tp_final.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ArticuloManufacturadoDTO {
    private String denominacion;
    private int totalVendidos;

    public ArticuloManufacturadoDTO(String denominacion, int totalVendidos) {
        this.denominacion = denominacion;
        this.totalVendidos = totalVendidos;
    }
}
