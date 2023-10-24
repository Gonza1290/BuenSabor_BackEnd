package com.example.tp_final.Controllers;

import com.example.tp_final.Entidades.*;
import com.example.tp_final.Enumeraciones.Estado;
import com.example.tp_final.Enumeraciones.TipoRubro;
import com.example.tp_final.Services.ArticuloInsumoService;
import com.example.tp_final.Services.ArticuloManufacturadoService;
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
@WebMvcTest(ArticuloManufacturadoController.class)
class ArticuloManufacturadoControllerTest {
    @MockBean
    private ArticuloManufacturadoService articuloManufacturadoService;

    @Autowired
    private MockMvc mockMvc;
    @Test
    void searchByDenominacion() throws Exception {
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

        when(articuloManufacturadoService.searchBydenominacion("Pizza",PageRequest.of(0, 5))).thenReturn(page);
        mockMvc.perform(get("/api/v1/articulos/manufacturados/searchByDenominacion")
                        .param("denominacion", "Pizza")
                        .param("page", "0")  // Agrega los parámetros de paginación
                        .param("size", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].denominacion", is("Pizza")));
    }
}