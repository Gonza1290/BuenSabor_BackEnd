package com.example.tp_final.Services;

import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.Rubro;
import com.example.tp_final.Entidades.UnidadMedida;
import com.example.tp_final.Enumeraciones.Estado;
import com.example.tp_final.Enumeraciones.TipoRubro;
import com.example.tp_final.Repositories.ArticuloInsumoRepository;
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
class ArticuloInsumoServiceTest {
    @MockBean
    private ArticuloInsumoRepository articuloInsumoRepository;
    @Autowired
    ArticuloInsumoService articuloInsumoService;
    @Test
    void searchBydenominacion() throws Exception {
        Rubro rubro = new Rubro("Gaseosas", Estado.Alta, TipoRubro.insumo);
        UnidadMedida unidadMedida = new UnidadMedida("litro", "l");

        ArticuloInsumo articuloInsumo = new ArticuloInsumo("CocaCola", "gaseosa", "urlImagen", 1000, Estado.Alta, rubro, 500, 3, 1, unidadMedida);
        List<ArticuloInsumo> articuloInsumoList = new ArrayList<>();
        articuloInsumoList.add(articuloInsumo);
        Page<ArticuloInsumo> page = new PageImpl<>(articuloInsumoList, PageRequest.of(0, 5), articuloInsumoList.size());

        when(articuloInsumoRepository.findByDenominacionIgnoreCaseContaining("CocaCola",PageRequest.of(0, 5))).thenReturn(page);


        assertEquals(page, articuloInsumoService.searchBydenominacion("CocaCola",PageRequest.of(0, 5)));

    }
}