package com.example.tp_final.Repositories;

import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.Rubro;
import com.example.tp_final.Entidades.UnidadMedida;
import com.example.tp_final.Enumeraciones.Estado;
import com.example.tp_final.Enumeraciones.TipoRubro;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ArticuloInsumoRepositoryTest {

    @Autowired
    EntityManager entityManager;
    @Autowired
    ArticuloInsumoRepository articuloInsumoRepository;
    @Test
    void findByDenominacionContaining() {
        Rubro rubro = new Rubro("Gaseosas", Estado.Alta, TipoRubro.insumo);
        UnidadMedida unidadMedida = new UnidadMedida("litro", "l");
        entityManager.persist(rubro);
        entityManager.persist(unidadMedida);
        entityManager.flush();

        ArticuloInsumo articuloInsumo = new ArticuloInsumo("CocaCola", "gaseosa", "urlImagen", 1000, Estado.Alta, rubro, 500, 3, 1, unidadMedida);
        entityManager.persist(articuloInsumo);

        entityManager.flush();

        // Realiza la búsqueda utilizando el repositorio
        Page<ArticuloInsumo> page = articuloInsumoRepository.findByDenominacionIgnoreCaseContaining("CocaCola", PageRequest.of(0, 5));

        // Verifica si la página contiene el elemento que esperas
        List<ArticuloInsumo> articuloInsumoList = page.getContent();
        assertEquals(1, articuloInsumoList.size()); // Debería haber un elemento
        assertEquals(articuloInsumo.getDenominacion(), articuloInsumoList.get(0).getDenominacion());

        // También puedes verificar otros atributos si es necesario
        // assertEquals(articuloInsumo.getOtroAtributo(), articuloInsumoList.get(0).getOtroAtributo());
    }
}