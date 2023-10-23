package com.example.tp_final.Services;

import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.Cliente;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends BaseServiceImpl<Cliente, Long>{
    @Autowired
    private ClienteRepository clienteRepository;

    public ClienteService(BaseRepository<Cliente, Long> baseRepository, ClienteRepository clienteRepository) {
        super(baseRepository);
        this.clienteRepository = clienteRepository;
    }

    public Page<Cliente> searchByNombre(String nombre, Pageable pageable) throws Exception {
        try {
            Page<Cliente> clientes = clienteRepository.findByNombreContaining(nombre,pageable);
            return clientes;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
