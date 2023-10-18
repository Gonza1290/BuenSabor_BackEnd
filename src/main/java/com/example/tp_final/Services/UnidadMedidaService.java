package com.example.tp_final.Services;

import com.example.tp_final.Entidades.Rubro;
import com.example.tp_final.Entidades.UnidadMedida;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadMedidaService extends BaseServiceImpl<UnidadMedida, Long>{
    @Autowired
    private UnidadMedidaRepository unidadMedidaRepository;

    public UnidadMedidaService(BaseRepository<UnidadMedida, Long> baseRepository, UnidadMedidaRepository unidadMedidaRepository) {
        super(baseRepository);
        this.unidadMedidaRepository = unidadMedidaRepository;
    }
}
