package com.example.tp_final.Services;

import com.example.tp_final.DTO.ClienteDTO;
import com.example.tp_final.Entidades.Persona;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class PersonaService extends BaseServiceImpl<Persona, Long>{
    @Autowired
    private PersonaRepository personaRepository;

    public PersonaService(BaseRepository<Persona, Long> baseRepository, PersonaRepository personaRepository) {
        super(baseRepository);
        this.personaRepository = personaRepository;
    }

    public Page<Persona> searchByNombre(String nombre, Pageable pageable) throws Exception {
        try {
            Page<Persona> personas = personaRepository.findByNombreIgnoreCaseContaining(nombre,pageable);
            return personas;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public Page<Persona> getAllClientes(Pageable pageable) throws Exception {
        try {
            Page<Persona> clientes = personaRepository.getAllClientes(pageable);
            return clientes;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public Page<Persona> getAllEmpleados(Pageable pageable) throws Exception {
        try {
            Page<Persona> empleados = personaRepository.getAllEmpleados(pageable);
            return empleados;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public Page<ClienteDTO> rankingClientes( Pageable pageable) throws Exception {
        try {
            Page<ClienteDTO> clienteDTOS = personaRepository.rankingClientes(pageable);
            return clienteDTOS;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public Page<ClienteDTO> rankingClientesByDate(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable) throws Exception {
        try {
            LocalDateTime fechaI = fechaInicio.atStartOfDay();
            LocalDateTime fechaF = LocalDateTime.of(fechaFin, LocalTime.of(23, 59, 59, 999_999_999));
            Page<ClienteDTO> clienteDTOS = personaRepository.rankingClientesByDate(fechaI,fechaF,pageable);
            return clienteDTOS;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Persona save(Persona entity) throws Exception {
        entity.setFechaAlta(LocalDateTime.now());
        return super.save(entity);
    }

    @Override
    public Persona update(Long aLong, Persona entity) throws Exception {
        entity.setFechaModificacion(LocalDateTime.now());
        return super.update(aLong, entity);
    }
}
