package com.example.tp_final.Repositories;

import com.example.tp_final.DTO.ArticuloInsumoDTO;
import com.example.tp_final.DTO.ClienteDTO;
import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.Cliente;
import com.example.tp_final.Entidades.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente,Long> {

    //Buscar por nombre
    Page<Cliente> findByNombreContaining(String nombre, Pageable pageable);

    @Query(nativeQuery = true)
    Page<ClienteDTO> rankingClientes(Pageable pageable);

    @Query(nativeQuery = true)
    Page<ClienteDTO> rankingClientesByDate(LocalDateTime fechaInicio, LocalDateTime fechaFin, Pageable pageable);


}

