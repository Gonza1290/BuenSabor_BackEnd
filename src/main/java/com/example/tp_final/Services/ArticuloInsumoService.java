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

import java.time.LocalDateTime;
import java.util.Optional;

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

    @Override
    public ArticuloInsumo save(ArticuloInsumo entity) throws Exception {
        entity.setFechaAlta(LocalDateTime.now());
        return super.save(entity);
    }

    @Override
    public ArticuloInsumo update(Long id, ArticuloInsumo entity) throws Exception {
        try{
            Optional<ArticuloInsumo> entityOptional = articuloInsumoRepository.findById(id);
            ArticuloInsumo entityUpdate = entityOptional.get();
            entity.setFechaModificacion(LocalDateTime.now());
            entity.setFechaAlta(entityUpdate.getFechaAlta());
            entity.setFechaBaja(entityUpdate.getFechaBaja());
            if (!entityUpdate.getEstadoArticulo().toString().equals(entity.getEstadoArticulo().toString())){
                if (entity.getEstadoArticulo().toString().equals("Alta")){
                    entity.setFechaAlta(LocalDateTime.now());
                }else {
                    entity.setFechaBaja(LocalDateTime.now());
                }
            }
            entityUpdate = articuloInsumoRepository.save(entity);
            return entityUpdate;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
