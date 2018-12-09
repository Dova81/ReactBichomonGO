package epers.bichomon.service.mapa;

import epers.bichomon.dao.EntrenadorDAO;
import epers.bichomon.dao.EventoDAO;
import epers.bichomon.dao.UbicacionDAO;
import epers.bichomon.model.bicho.Bicho;
import epers.bichomon.model.entrenador.Entrenador;
import epers.bichomon.model.evento.EventoArribo;
import epers.bichomon.model.ubicacion.TipoCamino;
import epers.bichomon.model.ubicacion.Ubicacion;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.ToIntBiFunction;

@RestController
@RequestMapping("mapa")
@Transactional(readOnly = true)
public class MapaService {

    private UbicacionDAO ubicacionDAO;
    private EntrenadorDAO entrenadorDAO;
    private EventoDAO eventoDAO;

    public MapaService(UbicacionDAO ubicacionDAO, EntrenadorDAO entrenadorDAO, EventoDAO eventoDAO) {
        this.ubicacionDAO = ubicacionDAO;
        this.entrenadorDAO = entrenadorDAO;
        this.eventoDAO = eventoDAO;
    }

    private int costoMoverA(Entrenador entrenador, Ubicacion ubicacion, ToIntBiFunction<Ubicacion, Ubicacion> viaje) {
        if (!ubicacionDAO.existeCamino(entrenador.getUbicacion(), ubicacion)) {
            throw new UbicacionMuyLejanaException(ubicacion.getNombre());
        }
        int costo = viaje.applyAsInt(entrenador.getUbicacion(), ubicacion);
        if (entrenador.getMonedas() < costo) {
            throw new CaminoMuyCostosoException(ubicacion.getNombre());
        }
        return costo;
    }

    private void moverA(String nombreEntrenador, String nuevaUbicacion, ToIntBiFunction<Ubicacion, Ubicacion> viaje) {
        Entrenador entrenador = entrenadorDAO.findByNombre(nombreEntrenador);
        String origen = entrenador.getUbicacion().getNombre();
        if (origen.equals(nuevaUbicacion)) {
            throw new UbicacionActualException(origen);
        }
        Ubicacion ubicacion = ubicacionDAO.get(nuevaUbicacion);
        entrenador.pagar(costoMoverA(entrenador, ubicacion, viaje));
        entrenador.moverA(ubicacion);
        entrenadorDAO.save(entrenador);
        eventoDAO.save(new EventoArribo(entrenador.getNombre(), origen, nuevaUbicacion));
    }

    @Transactional
    @PutMapping("/mover/{entrenador}/{ubicacion}")
    public void mover(@PathVariable String entrenador, @PathVariable String ubicacion) {
        moverA(entrenador, ubicacion, ubicacionDAO::viajeMasBarato);
    }

    @Transactional
    @PutMapping("/moverCorto/{entrenador}/{ubicacion}")
    public void moverMasCorto(@PathVariable String entrenador, @PathVariable String ubicacion) {
        moverA(entrenador, ubicacion, ubicacionDAO::viajeMasCorto);
    }

    @GetMapping("/conectados/{ubicacion}/{tipoCamino}")
    public List<Ubicacion> conectados(@PathVariable String ubicacion, @PathVariable TipoCamino tipoCamino) {
        return ubicacionDAO.conectados(ubicacion, tipoCamino);
    }

    @Transactional
    @PostMapping
    public void crearUbicacion(@RequestBody Ubicacion ubicacion) {
        ubicacionDAO.save(ubicacion);
    }

    @Transactional
    @PutMapping("/conectar/{ubicacion1}/{ubicacion2}/{tipoCamino}")
    public void conectar(@PathVariable String ubicacion1, @PathVariable String ubicacion2, @PathVariable TipoCamino tipoCamino) {
        ubicacionDAO.conectar(ubicacion1, ubicacion2, tipoCamino);
    }

    @GetMapping("/cantidad/{ubicacion}")
    public int cantidadEntrenadores(@PathVariable String ubicacion) {
        return entrenadorDAO.countByUbicacion_Nombre(ubicacion);
    }

    @GetMapping("/campeon/{dojo}")
    public Bicho campeon(@PathVariable String dojo) {
        return ubicacionDAO.getDojo(dojo).getCampeon();
    }

    @GetMapping("/campeonHistorico/{dojo}")
    public Bicho campeonHistorico(@PathVariable String dojo) {
        return ubicacionDAO.campeonHistorico(dojo);
    }
}
