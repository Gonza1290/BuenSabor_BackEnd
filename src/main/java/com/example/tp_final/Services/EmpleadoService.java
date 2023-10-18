package com.example.tp_final.Services;

import com.example.tp_final.Entidades.Empleado;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService extends BaseServiceImpl<Empleado, Long>{
    @Autowired
    private EmpleadoRepository empleadoRepository;

    public EmpleadoService(BaseRepository<Empleado, Long> baseRepository, EmpleadoRepository empleadoRepository) {
        super(baseRepository);
        this.empleadoRepository = empleadoRepository;
    }
}
