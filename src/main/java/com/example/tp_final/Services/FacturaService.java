package com.example.tp_final.Services;

import com.example.tp_final.Entidades.Empleado;
import com.example.tp_final.Entidades.Factura;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaService extends BaseServiceImpl<Factura, Long>{
    @Autowired
    private FacturaRepository facturaRepository;

    public FacturaService(BaseRepository<Factura, Long> baseRepository, FacturaRepository facturaRepository) {
        super(baseRepository);
        this.facturaRepository = facturaRepository;
    }
}
