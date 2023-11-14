package com.example.tp_final.Repositories;

import com.example.tp_final.DTO.PedidoDTO;
import com.example.tp_final.Entidades.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long> {
    @Query(value = "SELECT * FROM PEDIDO AS P WHERE P.ID_PERSONA = ?1",
            nativeQuery = true)
    Page<Pedido> findByCliente(Long Id, Pageable pageable);
    @Query(nativeQuery = true)
    Page<PedidoDTO> movimientosMonerios(Pageable pageable);
    @Query(nativeQuery = true)
    Page<PedidoDTO> movimientosMoneriosByDate(LocalDateTime fechaInicio, LocalDateTime fechaFin,Pageable pageable);
}
