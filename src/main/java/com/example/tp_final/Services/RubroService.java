package com.example.tp_final.Services;

import com.example.tp_final.Entidades.Empleado;
import com.example.tp_final.Entidades.Rubro;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.EmpleadoRepository;
import com.example.tp_final.Repositories.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RubroService extends BaseServiceImpl<Rubro, Long>{
    @Autowired
    private RubroRepository rubroRepository;

    public RubroService(BaseRepository<Rubro, Long> baseRepository, RubroRepository rubroRepository) {
        super(baseRepository);
        this.rubroRepository = rubroRepository;
    }
}
