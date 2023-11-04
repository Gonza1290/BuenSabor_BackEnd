package com.example.tp_final.Controllers;

import com.example.tp_final.Entidades.Cliente;
import com.example.tp_final.Services.ClienteService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/clientes")

public class ClienteController extends BaseControllerImpl<Cliente, ClienteService>{

    @GetMapping("/searchByNombre")
    public ResponseEntity<?> searchByNombre(@RequestParam String nombre, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchByNombre(nombre,pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @GetMapping("/rankingClientes")
    public ResponseEntity<?> rankingClientes( Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.rankingClientes(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/rankingClientesByDate")
    public ResponseEntity<?> rankingClientesByDate(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.rankingClientesByDate(fechaInicio,fechaFin,pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

}
