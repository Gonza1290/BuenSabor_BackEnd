package com.example.tp_final.Services;

import com.example.tp_final.Entidades.Localidad;
import com.example.tp_final.Entidades.Pedido;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.LocalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalidadService extends BaseServiceImpl<Localidad, Long>{
    @Autowired
    private LocalidadRepository localidadRepository;

    public LocalidadService(BaseRepository<Localidad, Long> baseRepository, LocalidadRepository localidadRepository) {
        super(baseRepository);
        this.localidadRepository = localidadRepository;
    }
}
