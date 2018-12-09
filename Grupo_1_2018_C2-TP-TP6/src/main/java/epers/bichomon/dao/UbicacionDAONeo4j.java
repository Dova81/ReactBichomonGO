package epers.bichomon.dao;

import epers.bichomon.model.ubicacion.TipoCamino;
import epers.bichomon.model.ubicacion.Ubicacion;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;

public interface UbicacionDAONeo4j extends Neo4jRepository<UbicacionNode, Long> {

    @Query("MERGE (:UbicacionNode {nro: {0}, nombre: {1}})")
    void saveUbicacion(Integer nro, String nombre);

    default void saveUbicacion(Ubicacion ubicacion) {
        saveUbicacion(ubicacion.getID(), ubicacion.getNombre());
    }

    @Query("MATCH (desde:UbicacionNode {nro: {0}}) " +
            "MATCH (hasta:UbicacionNode {nro: {1}}) " +
            "MERGE (desde)-[:Camino {tipo: {2}, costo: {3}}]->(hasta)")
    void saveCamino(Integer desde, Integer hasta, String camino, Integer costo);

    default void saveCamino(Integer desde, Integer hasta, TipoCamino camino) {
        saveCamino(desde, hasta, camino.toString(), camino.getCosto());
    }

    @Query("MATCH(:UbicacionNode {nro: {0}})-[r*]->(:UbicacionNode {nro: {1}}) " +
            "RETURN reduce(total=0, c IN r | total+c.costo ) as total order by total limit 1")
    int viajeMasBarato(Integer desde, Integer hasta);

    @Query("MATCH shortestPath((:UbicacionNode {nro: {0}})-[r*]->(:UbicacionNode {nro: {1}})) " +
            "RETURN reduce(total=0, c IN r | total+c.costo)")
    int viajeMasCorto(Integer desde, Integer hasta);

    @Query("MATCH (:UbicacionNode {nro: {0}})-[:Camino {tipo: {1}}]->(u) RETURN DISTINCT u.nro")
    List<Integer> conectados(Integer ubicacion, TipoCamino tipoCamino);

    @Query("MATCH (:UbicacionNode {nro: {0}})-[]->(u) RETURN DISTINCT u.nro")
    List<Integer> conectados(Integer ubicacion);

    @Query("RETURN EXISTS((:UbicacionNode {nro: {0}})-[*]->(:UbicacionNode {nro: {1}}))")
    Boolean existeCamino(Integer desde, Integer hasta);
}
