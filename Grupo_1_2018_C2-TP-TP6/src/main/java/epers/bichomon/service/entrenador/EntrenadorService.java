package epers.bichomon.service.entrenador;

import epers.bichomon.dao.EntrenadorDAO;
import epers.bichomon.model.entrenador.Entrenador;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("entrenador")
@Transactional
public class EntrenadorService {

    private EntrenadorDAO entrenadorDAO;


    public EntrenadorService(EntrenadorDAO entrenadorDAO) {
        this.entrenadorDAO = entrenadorDAO;
    }



    @GetMapping("/{entrenador}")
    public Entrenador buscarEntrenador(@PathVariable String entrenador) {
        Entrenador e = entrenadorDAO.findByNombre(entrenador);
        return e;
    }

}
