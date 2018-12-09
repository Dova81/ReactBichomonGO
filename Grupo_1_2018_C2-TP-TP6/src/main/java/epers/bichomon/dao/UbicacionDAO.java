package epers.bichomon.dao;

import epers.bichomon.model.bicho.Bicho;
import epers.bichomon.model.ubicacion.Dojo;
import epers.bichomon.model.ubicacion.TipoCamino;
import epers.bichomon.model.ubicacion.Ubicacion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UbicacionDAO {

    private DojoDAO dojoDAO;
    private UbicacionDAOHib ubicacionDAOHib;
    private UbicacionDAONeo4j ubicacionDAONeo4j;

    public UbicacionDAO(UbicacionDAOHib ubicacionDAOHib, UbicacionDAONeo4j ubicacionDAONeo4j, DojoDAO dojoDAO) {
        this.ubicacionDAOHib = ubicacionDAOHib;
        this.ubicacionDAONeo4j = ubicacionDAONeo4j;
        this.dojoDAO = dojoDAO;
    }

    public Ubicacion get(String ubicacion) {
        return ubicacionDAOHib.findByNombre(ubicacion);
    }

    public Dojo getDojo(String dojo) {
        return dojoDAO.findByNombre(dojo);
    }

    public Bicho campeonHistorico(String dojo) {
        return ubicacionDAOHib.campeonHistorico(dojo);
    }

    public void save(Ubicacion ubicacion) {
        ubicacionDAOHib.save(ubicacion);
        ubicacionDAONeo4j.saveUbicacion(ubicacion);
    }

    public void conectar(String ubicacion1, String ubicacion2, TipoCamino tipoCamino) {
        ubicacionDAONeo4j.saveCamino(
                ubicacionDAOHib.findByNombre(ubicacion1).getID(),
                ubicacionDAOHib.findByNombre(ubicacion2).getID(),
                tipoCamino);
    }

    public List<Ubicacion> conectados(String ubicacion, TipoCamino tipoCamino) {
        return ubicacionDAOHib.findByIdIn(ubicacionDAONeo4j.conectados(ubicacionDAOHib.findByNombre(ubicacion).getID(), tipoCamino));
    }

    public List<Ubicacion> conectados(String ubicacion) {
        return ubicacionDAOHib.findByIdIn(ubicacionDAONeo4j.conectados(ubicacionDAOHib.findByNombre(ubicacion).getID()));
    }

    public boolean existeCamino(Ubicacion desde, Ubicacion hasta) {
        return ubicacionDAONeo4j.existeCamino(desde.getID(), hasta.getID());
    }

    public int viajeMasBarato(Ubicacion desde, Ubicacion hasta) {
        return ubicacionDAONeo4j.viajeMasBarato(desde.getID(), hasta.getID());
    }

    public int viajeMasCorto(Ubicacion desde, Ubicacion hasta) {
        return ubicacionDAONeo4j.viajeMasCorto(desde.getID(), hasta.getID());
    }

    public void deleteAll() {
        ubicacionDAONeo4j.deleteAll();
        ubicacionDAOHib.deleteAll();
    }
}
