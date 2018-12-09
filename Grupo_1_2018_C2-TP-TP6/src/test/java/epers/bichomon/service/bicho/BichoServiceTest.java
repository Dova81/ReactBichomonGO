package epers.bichomon.service.bicho;

import epers.bichomon.model.bicho.BichoNoEvolucionableException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BichoServiceTest {

    private MockMvc mvc;

    @Autowired
    private BichoService bichoService;

    @Before
    public void prepare() {
        mvc = MockMvcBuilders.standaloneSetup(bichoService)
                .setHandlerExceptionResolvers(getSimpleMappingExceptionResolver())
                .build();
    }

    private SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties exceptionMappings = new Properties();
        exceptionMappings.put(BichoNoEvolucionableException.class.getName(), "error/BichoNoEvolucionableException");
        exceptionMappings.put("java.lang.Exception", "error/error");
        exceptionMappings.put("java.lang.RuntimeException", "error/error");
        exceptionResolver.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();
        statusCodes.put("error/BichoNoEvolucionableException", "404");
        statusCodes.put("error/error", "500");
        exceptionResolver.setStatusCodes(statusCodes);

        exceptionResolver.setDefaultErrorView("error/error");
        exceptionResolver.setDefaultStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return exceptionResolver;
    }

    @Test
    public void buscar_Andrea() throws Exception {
        mvc.perform(put("/bichos/buscar/Andrea"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.especie.nombre", is("Turquesamon")))
                .andExpect(jsonPath("$.energia", is(1500)))
                .andExpect(jsonPath("$.victorias", is(0)));
    }

    @Test
    public void abandonar_Lucas() throws Exception {
        mvc.perform(put("/bichos/buscar/Lucas"))
                .andExpect(status().isOk());
        mvc.perform(put("/bichos/abandonar/Lucas/6"))
                .andExpect(status().isOk());
    }

    @Test
    public void duelo_Andrea() throws Exception {
        mvc.perform(put("/bichos/duelo/Andrea/9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ganador.especie.nombre", is("Turquesamon")))
                .andExpect(jsonPath("$.perdedor.especie.nombre", is("Turquesamon")))
                .andExpect(jsonPath("$.ataques", hasSize(2)))
                .andExpect(jsonPath("$.ataques[0].puntos", is(1500)))
                .andExpect(jsonPath("$.ataques[0].atacante.especie.nombre", is("Turquesamon")))
                .andExpect(jsonPath("$.ataques[1].puntos", is(1501)))
                .andExpect(jsonPath("$.ataques[1].atacante.especie.nombre", is("Turquesamon")));
    }

    @Test
    public void puedeEvolucionar() throws Exception {
        mvc.perform(get("/bichos/puedeEvolucionar/8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(false)));
    }

    @Test
    public void evolucionar() throws Exception {
        mvc.perform(put("/bichos/evolucionar/8"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/BichoNoEvolucionableException"));
    }
}
