package com.example.tp_final.Repositories;

import com.example.tp_final.DTO.ArticuloInsumoDTO;
import com.example.tp_final.DTO.ArticuloManufacturadoDTO;
import com.example.tp_final.Entidades.ArticuloInsumo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloInsumoRepository extends BaseRepository<ArticuloInsumo,Long> {

    //Buscar por nombre
    Page<ArticuloInsumo> findByDenominacionIgnoreCaseContaining(String denominacion, Pageable pageable);
    @Query(nativeQuery = true)
    Page<ArticuloInsumoDTO> searchsoldest(Pageable pageable);
}
