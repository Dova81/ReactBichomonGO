package epers.bichomon.service.leaderboard;

import epers.bichomon.dao.EntrenadorDAO;
import epers.bichomon.dao.EspecieDAO;
import epers.bichomon.model.entrenador.Entrenador;
import epers.bichomon.model.especie.Especie;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("leaderboard")
@Transactional(readOnly = true)
public class LeaderboardService {

    private EntrenadorDAO entrenadorDAO;
    private EspecieDAO especieDAO;

    public LeaderboardService(EntrenadorDAO entrenadorDAO, EspecieDAO especieDAO) {
        this.entrenadorDAO = entrenadorDAO;
        this.especieDAO = especieDAO;
    }

    @GetMapping("/campeones")
    public List<Entrenador> campeones() {
        return this.entrenadorDAO.campeones();
    }

    @GetMapping("/especieLider")
    public Especie especieLider() {
        return this.especieDAO.lider();
    }

    @GetMapping("/lideres")
    public List<Entrenador> lideres() {
        return this.entrenadorDAO.lideres();
    }
}
