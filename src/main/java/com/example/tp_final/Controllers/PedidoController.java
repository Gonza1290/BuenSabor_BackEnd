package com.example.tp_final.Controllers;

import com.example.tp_final.Entidades.Pedido;
import com.example.tp_final.Entidades.Rubro;
import com.example.tp_final.Services.PedidoService;
import com.example.tp_final.Services.RubroService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/pedidos")
public class PedidoController extends BaseControllerImpl<Pedido, PedidoService>{
}
