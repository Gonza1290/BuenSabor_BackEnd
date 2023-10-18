package com.example.tp_final.Services;

import com.example.tp_final.Entidades.Domicilio;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioService extends BaseServiceImpl<Domicilio, Long>{
    @Autowired
    private DomicilioRepository domicilioRepository;

    public DomicilioService(BaseRepository<Domicilio, Long> baseRepository, DomicilioRepository domicilioRepository) {
        super(baseRepository);
        this.domicilioRepository = domicilioRepository;
    }
}
