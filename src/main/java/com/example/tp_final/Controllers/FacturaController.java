package com.example.tp_final.Controllers;

import com.example.tp_final.Entidades.Factura;
import com.example.tp_final.Entidades.Rubro;
import com.example.tp_final.Services.FacturaService;
import com.example.tp_final.Services.RubroService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/facturas")
public class FacturaController extends BaseControllerImpl<Factura, FacturaService>{
}
