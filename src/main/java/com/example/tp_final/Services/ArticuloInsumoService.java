package com.example.tp_final.Services;

import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.Cliente;
import com.example.tp_final.Repositories.ArticuloInsumoRepository;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticuloInsumoService extends BaseServiceImpl<ArticuloInsumo, Long>{
    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;

    public ArticuloInsumoService(BaseRepository<ArticuloInsumo, Long> baseRepository, ArticuloInsumoRepository articuloInsumoRepository) {
        super(baseRepository);
        this.articuloInsumoRepository = articuloInsumoRepository;
    }

    public Page<ArticuloInsumo> searchBydenominacion(String denominacion, Pageable pageable) throws Exception {
        try {
            Page<ArticuloInsumo> articulosInsumo = articuloInsumoRepository.findByDenominacionContaining(denominacion,pageable);
            return articulosInsumo;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
