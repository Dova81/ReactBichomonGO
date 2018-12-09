package epers.bichomon.service.especie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EspecieServiceTest {

    private MockMvc mvc;

    @Autowired
    private EspecieService especieService;

    @Before
    public void prepare() {
        mvc = MockMvcBuilders.standaloneSetup(especieService)
                .setHandlerExceptionResolvers(getSimpleMappingExceptionResolver())
                .build();
    }

    private SimpleMappingExceptionResolver getSimpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties exceptionMappings = new Properties();
        exceptionMappings.put(EspecieNoExistente.class.getName(), "error/EspecieNoExistente");
        exceptionMappings.put("java.lang.Exception", "error/error");
        exceptionMappings.put("java.lang.RuntimeException", "error/error");
        exceptionResolver.setExceptionMappings(exceptionMappings);

        Properties statusCodes = new Properties();
        statusCodes.put("error/EspecieNoExistente", "404");
        statusCodes.put("error/error", "500");
        exceptionResolver.setStatusCodes(statusCodes);

        exceptionResolver.setDefaultErrorView("error/error");
        exceptionResolver.setDefaultStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());

        return exceptionResolver;
    }

    @Test
    public void crear_especie() throws Exception {
        mvc.perform(post("/especies")
                .contentType(APPLICATION_JSON_UTF8)
                .content("{ \"nombre\": \"Persistemon\", \"tipo\": \"FUEGO\", \"energiaInicial\": 333 }")
        )
//                .andDo(print())
                .andExpect(status().isOk());

        mvc.perform(get("/especies/Persistemon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Persistemon")))
                .andExpect(jsonPath("$.tipo", is("FUEGO")))
                .andExpect(jsonPath("$.energiaInicial", is(333)));
    }

    @Test
    public void all_especies() throws Exception {
        mvc.perform(get("/especies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", anyOf(hasSize(14), hasSize(15))))
                .andExpect(jsonPath("$[0].nombre", is("Amarillomon")))
                .andExpect(jsonPath("$[0].tipo", is("AIRE")))
                .andExpect(jsonPath("$[1].nombre", is("Azulmon")))
                .andExpect(jsonPath("$[1].tipo", is("ELECTRICIDAD")));
    }

    @Test
    public void especie_por_nombre() throws Exception {
        mvc.perform(get("/especies/Amarillomon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Amarillomon")))
                .andExpect(jsonPath("$.tipo", is("AIRE")));
    }

    @Test
    public void especie_por_nombre_inexistente() throws Exception {
        mvc.perform(get("/especies/inexistente"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error/EspecieNoExistente"));
    }

    @Test
    public void crear_bicho() throws Exception {
        mvc.perform(put("/especies/bicho/Amarillomon"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.especie.nombre", is("Amarillomon")))
                .andExpect(jsonPath("$.energia", is(300)));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void populares() throws Exception {
        mvc.perform(get("/especies/populares"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)))
                .andExpect(jsonPath("$[0].nombre", is("Rojomon")))
                .andExpect(jsonPath("$[0].tipo", is("FUEGO")))
                .andExpect(jsonPath("$[1].nombre", is("Turquesamon")))
                .andExpect(jsonPath("$[1].tipo", is("PLANTA")));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void impopulares() throws Exception {
        mvc.perform(get("/especies/impopulares"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].nombre", is("Amarillomon")))
                .andExpect(jsonPath("$[0].tipo", is("AIRE")))
                .andExpect(jsonPath("$[1].nombre", is("Lagartomon")))
                .andExpect(jsonPath("$[1].tipo", is("FUEGO")));
    }

}
