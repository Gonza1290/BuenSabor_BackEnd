package com.example.tp_final.Controllers;

import com.example.tp_final.Entidades.ArticuloInsumo;
import com.example.tp_final.Entidades.Rubro;
import com.example.tp_final.Entidades.UnidadMedida;
import com.example.tp_final.Enumeraciones.Estado;
import com.example.tp_final.Enumeraciones.TipoRubro;
import com.example.tp_final.Services.ArticuloInsumoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
@WebMvcTest(ArticuloInsumoController.class)
class ArticuloInsumoControllerTest {
    @MockBean
    private ArticuloInsumoService articuloInsumoService;

    @Autowired
    private MockMvc mockMvc;
    @Test
    void searchByDenominacion() throws Exception {
        Rubro rubro = new Rubro("Gaseosas", Estado.Alta, TipoRubro.insumo);
        UnidadMedida unidadMedida = new UnidadMedida("litro", "l");

        ArticuloInsumo articuloInsumo = new ArticuloInsumo("CocaCola", "gaseosa", "urlImagen", 1000, Estado.Alta, rubro, 500, 3, 1, unidadMedida);
        List<ArticuloInsumo> articuloInsumoList = new ArrayList<>();
        articuloInsumoList.add(articuloInsumo);
        Page<ArticuloInsumo> page = new PageImpl<>(articuloInsumoList, PageRequest.of(0, 5), articuloInsumoList.size());

        when(articuloInsumoService.searchBydenominacion("CocaCola", PageRequest.of(0, 5))).thenReturn(page);

        mockMvc.perform(get("/api/v1/articulos/insumos/searchByDenominacion")
                        .param("denominacion", "CocaCola")
                        .param("page", "0")  // Agrega los parámetros de paginación
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].denominacion", is("CocaCola")));


    }
}
