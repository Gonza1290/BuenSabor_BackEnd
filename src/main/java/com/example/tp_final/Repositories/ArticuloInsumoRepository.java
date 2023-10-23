package com.example.tp_final.Repositories;

import com.example.tp_final.Entidades.ArticuloInsumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloInsumoRepository extends BaseRepository<ArticuloInsumo,Long> {

    //Buscar por nombre
    Page<ArticuloInsumo> findByDenominacionContaining(String denominacion, Pageable pageable);
}
