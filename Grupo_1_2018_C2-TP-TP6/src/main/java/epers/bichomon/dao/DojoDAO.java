package epers.bichomon.dao;

import epers.bichomon.model.ubicacion.Dojo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DojoDAO extends JpaRepository<Dojo, Integer> {
    Dojo findByNombre(String dojo);
}
