package com.example.tp_final.Repositories;

import com.example.tp_final.Entidades.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends BaseRepository<Pedido,Long> {
    @Query(value = "SELECT * FROM PEDIDO AS P WHERE P.ID_CLIENTE = ?1",
            nativeQuery = true)
    Page<Pedido> findByCliente(Long Id, Pageable pageable);
}
