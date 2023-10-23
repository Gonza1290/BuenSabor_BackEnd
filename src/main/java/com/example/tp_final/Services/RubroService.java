package com.example.tp_final.Services;

import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.Rubro;
import com.example.tp_final.Repositories.BaseRepository;
import com.example.tp_final.Repositories.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class RubroService extends BaseServiceImpl<Rubro, Long>{
    @Autowired
    private RubroRepository rubroRepository;

    public RubroService(BaseRepository<Rubro, Long> baseRepository, RubroRepository rubroRepository) {
        super(baseRepository);
        this.rubroRepository = rubroRepository;
    }

    @Override
    public Rubro save(Rubro entity) throws Exception {
        entity.setFechaAlta(LocalDateTime.now());
        return super.save(entity);
    }

    @Override
    public Rubro update(Long id, Rubro entity) throws Exception {
        try{
            Optional<Rubro> entityOptional = rubroRepository.findById(id);
            Rubro entityUpdate = entityOptional.get();
            entity.setFechaModificacion(LocalDateTime.now());
            entity.setFechaAlta(entityUpdate.getFechaAlta());
            entity.setFechaBaja(entityUpdate.getFechaBaja());
            if (!entityUpdate.getEstadoRubro().toString().equals(entity.getEstadoRubro().toString())){
                if (entity.getEstadoRubro().toString().equals("Alta")){
                    entity.setFechaAlta(LocalDateTime.now());
                }else {
                    entity.setFechaBaja(LocalDateTime.now());
                }
            }
            entityUpdate = rubroRepository.save(entity);
            return entityUpdate;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
