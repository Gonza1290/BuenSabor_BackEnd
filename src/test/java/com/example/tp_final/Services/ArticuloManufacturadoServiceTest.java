package com.example.tp_final.Services;

import com.example.tp_final.Entidades.*;
import com.example.tp_final.Enumeraciones.Estado;
import com.example.tp_final.Enumeraciones.TipoRubro;
import com.example.tp_final.Repositories.ArticuloInsumoRepository;
import com.example.tp_final.Repositories.ArticuloManufacturadoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class ArticuloManufacturadoServiceTest {
    @MockBean
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Autowired
    ArticuloManufacturadoService articuloManufacturadoService;
    @Test
    void searchBydenominacion() throws Exception {
        Rubro rubro1 = new Rubro("Quesos", Estado.Alta, TipoRubro.insumo);
        Rubro rubro2 = new Rubro("Pizzas", Estado.Alta, TipoRubro.manufacturado);
        UnidadMedida unidadMedida = new UnidadMedida("gramo", "g");

        ArticuloInsumo articuloInsumo = new ArticuloInsumo("Queso muzzarella", "...", "urlImagen", 1000, Estado.Alta, rubro1, 500, 3, 1, unidadMedida);

        DetalleArtManufacturado detalleArtManufacturado = new DetalleArtManufacturado(1,articuloInsumo);
        List<DetalleArtManufacturado> detalleArtManufacturadoList = new ArrayList<>();
        detalleArtManufacturadoList.add(detalleArtManufacturado);
        ArticuloManufacturado articuloManufacturado = new ArticuloManufacturado("Pizza","pizza sencilla","urlImagen",2500,Estado.Alta,rubro2,25,1500,"receta.",detalleArtManufacturadoList);
        List<ArticuloManufacturado> articuloManufacturadoList = new ArrayList<>();
        articuloManufacturadoList.add(articuloManufacturado);
        Page<ArticuloManufacturado> page = new PageImpl<>(articuloManufacturadoList, PageRequest.of(0, 5), articuloManufacturadoList.size());

        when(articuloManufacturadoRepository.findByDenominacionContaining("Pizza",PageRequest.of(0, 5))).thenReturn(page);


        assertEquals(page, articuloManufacturadoService.searchBydenominacion("Pizza",PageRequest.of(0, 5)));
    }
}