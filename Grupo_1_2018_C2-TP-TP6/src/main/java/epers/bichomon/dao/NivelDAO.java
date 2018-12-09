package epers.bichomon.dao;

import epers.bichomon.model.entrenador.Nivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelDAO extends JpaRepository<Nivel, Integer> {
    Nivel findByNro(Integer nro);
}
