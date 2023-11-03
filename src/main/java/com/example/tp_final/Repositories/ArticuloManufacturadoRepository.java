package com.example.tp_final.Repositories;

import com.example.tp_final.DTO.ArticuloInsumoDTO;
import com.example.tp_final.DTO.ArticuloManufacturadoDTO;
import com.example.tp_final.Entidades.ArticuloManufacturado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado,Long> {

    //Buscar por nombre
    Page<ArticuloManufacturado> findByDenominacionIgnoreCaseContaining(String denominacion, Pageable pageable);

    @Query(nativeQuery = true)
    Page<ArticuloManufacturadoDTO> searchsoldest(Pageable pageable);
    @Query(nativeQuery = true)
    Page<ArticuloManufacturadoDTO> searchSoldestByDate(LocalDateTime fechaInicio, LocalDateTime fechaFin, Pageable pageable);

}
