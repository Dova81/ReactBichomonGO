package epers.bichomon.service.feed;

import epers.bichomon.dao.EntrenadorDAO;
import epers.bichomon.dao.EventoDAO;
import epers.bichomon.dao.UbicacionDAO;
import epers.bichomon.model.entrenador.Entrenador;
import epers.bichomon.model.evento.Evento;
import epers.bichomon.model.ubicacion.Ubicacion;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("feed")
@Transactional(readOnly = true)
public class FeedService {

    private EventoDAO eventoDAO;
    private UbicacionDAO ubicacionDAO;
    private EntrenadorDAO entrenadorDAO;

    public FeedService(EventoDAO eventoDAO, UbicacionDAO ubicacionDAO, EntrenadorDAO entrenadorDAO) {
        this.eventoDAO = eventoDAO;
        this.ubicacionDAO = ubicacionDAO;
        this.entrenadorDAO = entrenadorDAO;
    }

    @GetMapping("/entrenador/{entrenador}")
    public List<Evento> feedEntrenador(@PathVariable String entrenador) {
        return eventoDAO.getByEntrenador(entrenador);
    }

    @GetMapping("/ubicacion/{entrenador}")
    public List<Evento> feedUbicacion(@PathVariable String entrenador) {
        try {
            Entrenador e = entrenadorDAO.findByNombre(entrenador);
            return eventoDAO.getByUbicaciones(conectadasA(e.getUbicacion()));
        } catch (NoResultException e) {
            throw new EntrenadorInexistenteException(entrenador);
        }
    }

    private List<String> conectadasA(Ubicacion ubicacion) {
        List<Ubicacion> ubicaciones = ubicacionDAO.conectados(ubicacion.getNombre());
        ubicaciones.add(ubicacion);
        return ubicaciones.stream().map(Ubicacion::getNombre).collect(Collectors.toList());
    }
}
