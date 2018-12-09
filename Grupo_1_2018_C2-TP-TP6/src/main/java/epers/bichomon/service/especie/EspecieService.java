package epers.bichomon.service.especie;

import epers.bichomon.dao.BichoDAO;
import epers.bichomon.dao.EspecieDAO;
import epers.bichomon.model.bicho.Bicho;
import epers.bichomon.model.especie.Especie;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("especies")
@Transactional(readOnly = true)
public class EspecieService {

    private EspecieDAO especieDAO;
    private BichoDAO bichoDAO;

    public EspecieService(EspecieDAO especieDAO, BichoDAO bichoDAO) {
        this.especieDAO = especieDAO;
        this.bichoDAO = bichoDAO;
    }

    @Transactional
    @PostMapping
    public void crearEspecie(@RequestBody Especie especie) {
        especieDAO.save(especie);
    }

    @GetMapping("/{nombreEspecie}")
    public Especie getEspecie(@PathVariable String nombreEspecie) {
        Especie especie = especieDAO.findByNombre(nombreEspecie);
        if (especie == null) {
            throw new EspecieNoExistente(nombreEspecie);
        }
        return especie;
    }

    @GetMapping
    public List<Especie> getAllEspecies() {
        return especieDAO.findAllByOrderByNombreAsc();
    }

    @Transactional
    @PutMapping("/bicho/{nombreEspecie}")
    public Bicho crearBicho(@PathVariable String nombreEspecie) {
        Especie especie = especieDAO.findByNombre(nombreEspecie);
        Bicho bicho = especie.crearBicho();
        bichoDAO.save(bicho);
        especieDAO.save(especie);
        return bicho;
    }

    @GetMapping("/populares")
    public List<Especie> populares() {
        return especieDAO.getPopulares();
    }

    @GetMapping("/impopulares")
    public List<Especie> impopulares() {
        return especieDAO.getImpopulares();
    }

}
