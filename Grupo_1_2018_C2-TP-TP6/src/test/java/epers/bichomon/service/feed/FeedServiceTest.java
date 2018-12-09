package epers.bichomon.service.feed;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class FeedServiceTest {

    private MockMvc mvc;

    @Autowired
    private FeedService feedService;

    @Before
    public void prepare() {
        mvc = MockMvcBuilders.standaloneSetup(feedService).build();
    }

    @Test
    public void entrenador_Julio() throws Exception {
        mvc.perform(get("/feed/entrenador/Julio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].type", is("EventoCoronacion")))
                .andExpect(jsonPath("$[0].coronado", is("Julio")))
                .andExpect(jsonPath("$[0].descoronado", is("Alberto")))
                .andExpect(jsonPath("$[0].ubicacion", is("Tibet Dojo")));
    }

    @Test
    public void ubicacion_Julio() throws Exception {
        mvc.perform(get("/feed/ubicacion/Julio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].type", is("EventoArribo")))
                .andExpect(jsonPath("$[0].entrenador", is("Ana")))
                .andExpect(jsonPath("$[0].origen", is("Plantalandia")))
                .andExpect(jsonPath("$[0].destino", is("Agualandia")));
    }
}
