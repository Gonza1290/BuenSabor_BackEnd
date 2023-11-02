package com.example.tp_final.Controllers;

import com.example.tp_final.Entidades.ArticuloManufacturado;
import com.example.tp_final.Services.ArticuloManufacturadoService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/articulos/manufacturados")
public class ArticuloManufacturadoController extends BaseControllerImpl<ArticuloManufacturado, ArticuloManufacturadoService>{

    @GetMapping("/searchByDenominacion")
    public ResponseEntity<?> searchByDenominacion(@RequestParam String denominacion, Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchBydenominacion(denominacion,pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
    @GetMapping("/searchsoldest")
    public ResponseEntity<?> searchsoldest( Pageable pageable) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(servicio.searchsoldest(pageable));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
