package epers.bichomon.service.bicho;

import epers.bichomon.dao.BichoDAO;
import epers.bichomon.dao.EntrenadorDAO;
import epers.bichomon.dao.EventoDAO;
import epers.bichomon.model.bicho.Bicho;
import epers.bichomon.model.entrenador.Entrenador;
import epers.bichomon.model.evento.EventoAbandono;
import epers.bichomon.model.evento.EventoCaptura;
import epers.bichomon.model.evento.EventoCoronacion;
import epers.bichomon.model.ubicacion.duelo.ResultadoCombate;
import epers.bichomon.service.NotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("bichos")
@Transactional
public class BichoService {

    private EntrenadorDAO entrenadorDAO;
    private EventoDAO eventoDAO;
    private BichoDAO bichoDAO;

    public BichoService(EntrenadorDAO entrenadorDAO, EventoDAO eventoDAO, BichoDAO bichoDAO) {
        this.entrenadorDAO = entrenadorDAO;
        this.eventoDAO = eventoDAO;
        this.bichoDAO = bichoDAO;
    }

    @PutMapping("/buscar/{entrenador}")
    public Bicho buscar(@PathVariable String entrenador) {
        Entrenador e = entrenadorDAO.findByNombre(entrenador);
        Bicho b = e.buscar();
        if (b != null) {
            bichoDAO.save(b);
            entrenadorDAO.save(e);
            eventoDAO.save(new EventoCaptura(e.getNombre(), e.getUbicacion().getNombre(), b.getEspecie().getNombre()));
        }
        return b;
    }

    @PutMapping("/abandonar/{entrenador}/{bicho}")
    public void abandonar(@PathVariable String entrenador, @PathVariable int bicho) {
        Entrenador e = this.entrenadorDAO.findByNombre(entrenador);
        Bicho b = this.bichoDAO.findById(bicho).orElseThrow(NotFoundException::new);
        e.abandonar(b);
        entrenadorDAO.save(e);
        eventoDAO.save(new EventoAbandono(e.getNombre(), e.getUbicacion().getNombre(), b.getEspecie().getNombre()));
    }

    @PutMapping("/duelo/{entrenador}/{bicho}")
    public ResultadoCombate duelo(@PathVariable String entrenador, @PathVariable int bicho) {
        Entrenador e = this.entrenadorDAO.findByNombre(entrenador);
        Bicho b = this.bichoDAO.findById(bicho).orElseThrow(NotFoundException::new);
        ResultadoCombate resultado = e.duelo(b);
        entrenadorDAO.save(e);
        if (resultado.getGanador().equals(b)) {
            String descoronado = resultado.getPerdedor() != null ? resultado.getPerdedor().getEntrenador().getNombre() : "";
            eventoDAO.save(new EventoCoronacion(entrenador, descoronado, e.getUbicacion().getNombre()));
        }
        return resultado;
    }

    @Transactional(readOnly = true)
    @GetMapping("/puedeEvolucionar/{bicho}")
    public boolean puedeEvolucionar(@PathVariable int bicho) {
        return this.bichoDAO.findById(bicho).orElseThrow(NotFoundException::new).puedeEvolucionar();
    }

    @PutMapping("/evolucionar/{bicho}")
    public Bicho evolucionar(@PathVariable int bicho) {
        Bicho b = bichoDAO.findById(bicho).orElseThrow(NotFoundException::new);
        b.evolucionar();
        bichoDAO.save(b);
        return b;
    }
}
