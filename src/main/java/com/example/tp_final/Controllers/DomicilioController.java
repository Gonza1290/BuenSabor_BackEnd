package com.example.tp_final.Controllers;

import com.example.tp_final.Entidades.Domicilio;
import com.example.tp_final.Entidades.Empleado;
import com.example.tp_final.Services.DomicilioService;
import com.example.tp_final.Services.EmpleadoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/domicilios")

public class DomicilioController extends BaseControllerImpl<Domicilio, DomicilioService>{
}
