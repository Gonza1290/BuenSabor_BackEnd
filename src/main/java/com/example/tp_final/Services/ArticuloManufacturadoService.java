package com.example.tp_final.Services;

import com.example.tp_final.DTO.ArticuloInsumoDTO;
import com.example.tp_final.DTO.ArticuloManufacturadoDTO;
import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.ArticuloManufacturado;
import com.example.tp_final.Entidades.DetalleArtManufacturado;
import com.example.tp_final.Repositories.ArticuloInsumoRepository;
import com.example.tp_final.Repositories.ArticuloManufacturadoRepository;
import com.example.tp_final.Repositories.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ArticuloManufacturadoService extends BaseServiceImpl<ArticuloManufacturado, Long>{
    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Autowired
    private ArticuloInsumoRepository articuloInsumoRepository;
    @Override
    @Transactional
    public ArticuloManufacturado save(ArticuloManufacturado entity) throws Exception {
        try{
            calcularCosto(entity);
            entity.setFechaAlta(LocalDateTime.now());
            entity = articuloManufacturadoRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ArticuloManufacturado update(Long id, ArticuloManufacturado entity) throws Exception {
        try{
            Optional<ArticuloManufacturado> entityOptional = articuloManufacturadoRepository.findById(id);
            ArticuloManufacturado entityUpdate = entityOptional.get();
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
            entityUpdate = articuloManufacturadoRepository.save(entity);
            return entityUpdate;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public Page<ArticuloManufacturado> searchBydenominacion(String denominacion, Pageable pageable) throws Exception {
        try {
            Page<ArticuloManufacturado> articulosManufacturado = articuloManufacturadoRepository.findByDenominacionIgnoreCaseContaining(denominacion,pageable);
            return articulosManufacturado;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    //Funcion para calcular el costo total del articulo manufacturado
    public void calcularCosto (ArticuloManufacturado entity) {
        //Se calcula el precioCosto del articulo manufacturado a partir de los precioCompra de los articulos insumos
        double precioCosto = 0;
        for (DetalleArtManufacturado detalleArtManufacturado: entity.getDetallesArtManufacturado()) {
            ArticuloInsumo articuloInsumo = articuloInsumoRepository.getById(detalleArtManufacturado.getArticuloInsumo().getId());
            precioCosto += articuloInsumo.getPrecioCompra() * detalleArtManufacturado.getCantidad();
        }
        entity.setPrecioCosto(precioCosto);
    }
    public Page<ArticuloManufacturadoDTO> searchsoldest(Pageable pageable) throws Exception {
        try {
            Page<ArticuloManufacturadoDTO> articuloManufacturadoDTOS = articuloManufacturadoRepository.searchsoldest(pageable);
            return articuloManufacturadoDTOS;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public Page<ArticuloManufacturadoDTO> searchSoldestByDate(LocalDate fechaInicio, LocalDate fechaFin, Pageable pageable) throws Exception {
        try {
            LocalDateTime fechaI = fechaInicio.atStartOfDay();
            LocalDateTime fechaF = fechaFin.atStartOfDay();
            Page<ArticuloManufacturadoDTO> articuloManufacturadoDTOS = articuloManufacturadoRepository.searchSoldestByDate(fechaI,fechaF,pageable);
            return articuloManufacturadoDTOS;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    public ArticuloManufacturadoService(BaseRepository<ArticuloManufacturado, Long> baseRepository, ArticuloManufacturadoRepository articuloManufacturadoRepository) {
        super(baseRepository);
        this.articuloManufacturadoRepository = articuloManufacturadoRepository;
    }
}
