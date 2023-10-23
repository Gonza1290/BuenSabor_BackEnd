package com.example.tp_final.Repositories;

import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends BaseRepository<Cliente,Long> {

    //Buscar por nombre
    Page<Cliente> findByNombreContaining(String nombre, Pageable pageable);

}
