package com.example.tp_final.Services;

import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.ArticuloManufacturado;
import com.example.tp_final.Entidades.DetalleArtManufacturado;
import com.example.tp_final.Repositories.ArticuloManufacturadoRepository;
import com.example.tp_final.Repositories.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloManufacturadoService extends BaseServiceImpl<ArticuloManufacturado, Long>{
    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;

    @Override
    @Transactional
    public ArticuloManufacturado save(ArticuloManufacturado entity) throws Exception {
        try{
            calcularCosto(entity);
            entity = articuloManufacturadoRepository.save(entity);
            return entity;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void calcularCosto (ArticuloManufacturado entity) {
        //Se calcula el precioCosto del articulo manufacturado a partir de los precioCompra de los articulos insumos
        double precioCosto = 0;
        for (DetalleArtManufacturado detalleArtManufacturado: entity.getDetallesArtManufacturado()) {
            precioCosto += detalleArtManufacturado.getArticuloInsumo().getPrecioCompra() * detalleArtManufacturado.getCantidad();
        }
        entity.setPrecioCosto(precioCosto);
    }

    public ArticuloManufacturadoService(BaseRepository<ArticuloManufacturado, Long> baseRepository, ArticuloManufacturadoRepository articuloManufacturadoRepository) {
        super(baseRepository);
        this.articuloManufacturadoRepository = articuloManufacturadoRepository;
    }
}
