package com.example.tp_final.Controllers;

import com.example.tp_final.Entidades.Factura;
import com.example.tp_final.Entidades.Localidad;
import com.example.tp_final.Services.FacturaService;
import com.example.tp_final.Services.LocalidadService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/localidades")
public class LocalidadController extends BaseControllerImpl<Localidad, LocalidadService>{
}
