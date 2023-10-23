package com.example.tp_final.Repositories;

import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado,Long> {

    //Buscar por nombre
    Page<ArticuloManufacturado> findByDenominacionContaining(String denominacion, Pageable pageable);
}
