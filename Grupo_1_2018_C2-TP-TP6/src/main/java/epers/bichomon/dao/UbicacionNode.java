package epers.bichomon.dao;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class UbicacionNode {

    @Id
    @GeneratedValue
    private Long id;

    private Integer nro;

    private String nombre;

}
