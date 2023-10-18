package com.example.tp_final.Controllers;

import com.example.tp_final.Entidades.Rubro;
import com.example.tp_final.Entidades.UnidadMedida;
import com.example.tp_final.Services.RubroService;
import com.example.tp_final.Services.UnidadMedidaService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/unidadmedidas")
public class UnidadMedidaController extends BaseControllerImpl<UnidadMedida, UnidadMedidaService>{
}
