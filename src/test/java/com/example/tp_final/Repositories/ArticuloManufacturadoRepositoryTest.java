package com.example.tp_final.Repositories;

import com.example.tp_final.DTO.ArticuloManufacturadoDTO;
import com.example.tp_final.Entidades.*;
import com.example.tp_final.Enumeraciones.Estado;
import com.example.tp_final.Enumeraciones.TipoRubro;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ArticuloManufacturadoRepositoryTest {
    @Autowired
    EntityManager entityManager;
    @Autowired
    ArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Test
    void findByDenominacionContaining() {
        Rubro rubro1 = new Rubro("Quesos", Estado.Alta, TipoRubro.insumo);
        Rubro rubro2 = new Rubro("Pizzas", Estado.Alta, TipoRubro.manufacturado);
        UnidadMedida unidadMedida = new UnidadMedida("gramo", "g");
        entityManager.persist(rubro1);
        entityManager.persist(rubro2);
        entityManager.persist(unidadMedida);
        entityManager.flush();

        ArticuloInsumo articuloInsumo = new ArticuloInsumo("Queso muzzarella", "...", "urlImagen", 1000, Estado.Alta, rubro1, 500, 3, 1, unidadMedida);
        entityManager.persist(articuloInsumo);
        entityManager.flush();

        DetalleArtManufacturado detalleArtManufacturado = new DetalleArtManufacturado(1,articuloInsumo);
        List<DetalleArtManufacturado> detalleArtManufacturadoList = new ArrayList<>();
        detalleArtManufacturadoList.add(detalleArtManufacturado);
        ArticuloManufacturado articuloManufacturado = new ArticuloManufacturado("Pizza","pizza sencilla","urlImagen",2500,Estado.Alta,rubro2,25,1500,"receta.",detalleArtManufacturadoList);
        entityManager.persist(articuloManufacturado);
        entityManager.flush();

        // Realiza la búsqueda utilizando el repositorio
        Page<ArticuloManufacturado> page = articuloManufacturadoRepository.findByDenominacionIgnoreCaseContaining("Pizza", PageRequest.of(0, 5));

        // Verifico si la página contiene el elemento
        List<ArticuloManufacturado> articuloManufacturadoList = page.getContent();
        assertEquals(1, articuloManufacturadoList.size()); // Debería haber un elemento
        assertEquals(articuloManufacturado.getDenominacion(), articuloManufacturadoList.get(0).getDenominacion());

        // También puedes verificar otros atributos si es necesario
        // assertEquals(articuloInsumo.getOtroAtributo(), articuloInsumoList.get(0).getOtroAtributo());
    }
    @Test
    void searchsoldest(){
        Page<ArticuloManufacturadoDTO> articulosManufacturadosDTOS = articuloManufacturadoRepository.searchsoldest(PageRequest.of(0, 5));
        List<ArticuloManufacturadoDTO> articuloManufacturadoDTOList = articulosManufacturadosDTOS.getContent();
        for (ArticuloManufacturadoDTO articuloManufacturadoDTO: articuloManufacturadoDTOList) {
            assertEquals(articuloManufacturadoDTO.getDenominacion(),"string");
            assertEquals(articuloManufacturadoDTO.getTotalVendidos(),36);
        }
    }
}