package com.example.tp_final.Repositories;

import com.example.tp_final.DTO.ClienteDTO;
import com.example.tp_final.Entidades.Pedido;
import com.example.tp_final.Entidades.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PersonaRepository extends BaseRepository<Persona,Long> {

    //Buscar por nombre
    Page<Persona> findByNombreIgnoreCaseContaining(String nombre, Pageable pageable);

    @Query(value = "SELECT P.ID,P.FECHA_ALTA,P.FECHA_BAJA,P.FECHA_MODIFICACION, P.NOMBRE, P.APELLIDO, P.TELEFONO, P.EMAIL, P.USUARIO_ID FROM PERSONA AS P ,USUARIO AS U WHERE P.USUARIO_ID=U.ID AND U.ROL = 'Cliente'",
            nativeQuery = true)
    Page<Persona> getAllClientes(Pageable pageable);
    @Query(value = "SELECT P.ID,P.FECHA_ALTA,P.FECHA_BAJA,P.FECHA_MODIFICACION, P.NOMBRE, P.APELLIDO, P.TELEFONO, P.EMAIL, P.USUARIO_ID FROM PERSONA AS P ,USUARIO AS U WHERE P.USUARIO_ID=U.ID AND (U.ROL = 'Cocinero' OR U.ROL = 'Delivery' OR U.ROL = 'Cajero')",
            nativeQuery = true)
    Page<Persona> getAllEmpleados(Pageable pageable);
    @Query(nativeQuery = true)
    Page<ClienteDTO> rankingClientes(Pageable pageable);

    @Query(nativeQuery = true)
    Page<ClienteDTO> rankingClientesByDate(LocalDateTime fechaInicio, LocalDateTime fechaFin, Pageable pageable);


}

