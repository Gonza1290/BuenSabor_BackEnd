package com.example.tp_final.Services;

import com.example.tp_final.Entidades.Pedido;
import com.example.tp_final.Entidades.Rubro;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService extends BaseServiceImpl<Pedido, Long>{
    @Autowired
    private PedidoRepository pedidoRepository;

    public PedidoService(BaseRepository<Pedido, Long> baseRepository, PedidoRepository pedidoRepository) {
        super(baseRepository);
        this.pedidoRepository = pedidoRepository;
    }
}
