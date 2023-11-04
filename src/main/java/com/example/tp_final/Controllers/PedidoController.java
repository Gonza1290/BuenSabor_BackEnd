package com.example.tp_final.Controllers;

import com.example.tp_final.Entidades.Pedido;

import com.example.tp_final.Services.PedidoService;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoService>{
    @GetMapping("/findByCliente/{Id}")
    public ResponseEntity<?> findByCliente(@PathVariable Long Id, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.findByCliente(Id,pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/movimientosMonerios")
    public ResponseEntity<?> movimientosMonerios( Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.movimientosMonerios(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/movimientosMoneriosByDate")
    public ResponseEntity<?> movimientosMoneriosByDate(@RequestParam LocalDate fechaInicio, @RequestParam LocalDate fechaFin, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.movimientosMoneriosByDate(fechaInicio,fechaFin,pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
