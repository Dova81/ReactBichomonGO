package epers.bichomon.service.mapa;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapaServiceTest {

    private MockMvc mvc;

    @Autowired
    private MapaService mapaService;

    @Before
    public void prepare() {
        mvc = MockMvcBuilders
                .standaloneSetup(mapaService)
                .build();
    }

    @Test
    public void crear_ubicacion() throws Exception {
        mvc.perform(post("/mapa")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{ \"type\": \"Guarderia\", \"nombre\": \"UNQ\" }")
        )
                .andExpect(status().isOk());
    }

    @Test
    public void mover() throws Exception {
        mvc.perform(put("/mapa/mover/Lucas/Plantalandia"))
                .andExpect(status().isOk());
    }

    @Test
    public void mover_mas_corto() throws Exception {
        mvc.perform(put("/mapa/moverCorto/Ash/Plantalandia"))
                .andExpect(status().isOk());
    }

    @Test
    public void conectados() throws Exception {
        mvc.perform(get("/mapa/conectados/Poke/Terrestre"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Plantalandia")));
    }

    @Test
    public void conectar() throws Exception {
        mvc.perform(put("/mapa/conectar/Isla/Miyagi/Aereo"))
                .andExpect(status().isOk());

        mvc.perform(get("/mapa/conectados/Isla/Aereo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Miyagi")));
    }

    @Test
    public void cantidad_entrenadores() throws Exception {
        mvc.perform(get("/mapa/cantidad/Agualandia/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(1)));
    }

    @Test
    public void campeon() throws Exception {
        mvc.perform(get("/mapa/campeon/Tibet Dojo/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.especie.nombre", is("Turquesamon")))
                .andExpect(jsonPath("$.especie.tipo", is("PLANTA")))
                .andExpect(jsonPath("$.energia", is(1501)));
    }

    @Test
    public void campeon_historico() throws Exception {
        mvc.perform(get("/mapa/campeonHistorico/Tibet Dojo/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.especie.nombre", is("Naranjamon")))
                .andExpect(jsonPath("$.especie.tipo", is("CHOCOLATE")))
                .andExpect(jsonPath("$.energia", is(502)));
    }
}
