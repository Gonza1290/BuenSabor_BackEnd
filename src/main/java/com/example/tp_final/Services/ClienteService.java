package com.example.tp_final.Services;

import com.example.tp_final.Entidades.Cliente;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends BaseServiceImpl<Cliente, Long>{
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(BaseRepository<Cliente, Long> baseRepository, ClienteRepository clienteRepository) {
        super(baseRepository);
        this.clienteRepository = clienteRepository;
    }
}
