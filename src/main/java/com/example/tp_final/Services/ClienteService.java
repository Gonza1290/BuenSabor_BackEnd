package com.example.tp_final.Services;

import com.example.tp_final.DTO.ClienteDTO;
import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.Cliente;
import com.example.tp_final.Entidades.Pedido;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@Service
public class ClienteService extends BaseServiceImpl<Cliente, Long>{
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(BaseRepository<Cliente, Long> baseRepository, ClienteRepository clienteRepository) {
        super(baseRepository);
        this.clienteRepository = clienteRepository;
    }

    public Page<Cliente> searchByNombre(String nombre, Pageable pageable) throws Exception {
        try {
            Page<Cliente> clientes = clienteRepository.findByNombreContaining(nombre,pageable);
            return clientes;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public Page<ClienteDTO> rankingClientes( Pageable pageable) throws Exception {
        try {
            Page<ClienteDTO> clienteDTOS = clienteRepository.rankingClientes(pageable);
            return clienteDTOS;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public Page<ClienteDTO> rankingClientesByDate(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable) throws Exception {
        try {
            LocalDateTime fechaI = fechaInicio.atStartOfDay();
            LocalDateTime fechaF = LocalDateTime.of(fechaFin, LocalTime.of(23, 59, 59, 999_999_999));
            Page<ClienteDTO> clienteDTOS = clienteRepository.rankingClientesByDate(fechaI,fechaF,pageable);
            return clienteDTOS;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Cliente save(Cliente entity) throws Exception {
        entity.setFechaAlta(LocalDateTime.now());
        return super.save(entity);
    }

    @Override
    public Cliente update(Long aLong, Cliente entity) throws Exception {
        entity.setFechaModificacion(LocalDateTime.now());
        return super.update(aLong, entity);
    }
}
