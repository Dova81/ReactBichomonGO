package epers.bichomon.service.leaderboard;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeaderboardServiceTest {

    private MockMvc mvc;

    @Autowired
    private LeaderboardService leaderboardService;

    @Before
    public void prepare() {
        mvc = MockMvcBuilders
                .standaloneSetup(leaderboardService)
                .build();
    }

    @Test
    public void campeones() throws Exception {
        mvc.perform(get("/leaderboard/campeones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].nombre", is("Julio")))
                .andExpect(jsonPath("$[0].ubicacion.nombre", is("Tibet Dojo")));
    }

    @Test
    public void especieLider() throws Exception {
        mvc.perform(get("/leaderboard/especieLider"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Naranjamon")))
                .andExpect(jsonPath("$.tipo", is("CHOCOLATE")));
    }

    @Test
    public void lideres() throws Exception {
        mvc.perform(get("/leaderboard/lideres"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)))
                .andExpect(jsonPath("$[0].nombre", is("Julio")))
                .andExpect(jsonPath("$[0].ubicacion.nombre", is("Tibet Dojo")));
    }
}
