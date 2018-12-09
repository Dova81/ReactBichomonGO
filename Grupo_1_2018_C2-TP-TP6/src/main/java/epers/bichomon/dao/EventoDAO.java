package epers.bichomon.dao;

import epers.bichomon.model.evento.Evento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface EventoDAO extends MongoRepository<Evento, String> {

    @Query(value = "{ $or: [{ubicacion: {$in:?0}}, {origen: {$in:?0}}, {destino: {$in:?0}}] }", sort = "{ fecha : -1 }")
    List<Evento> getByUbicaciones(List<String> ubicaciones);

    @Query(value = "{ $or: [{ coronado: ?0 }, { descoronado: ?0 }, { entrenador: ?0 }] }", sort = "{ fecha : -1 }")
    List<Evento> getByEntrenador(String entrenador);

}