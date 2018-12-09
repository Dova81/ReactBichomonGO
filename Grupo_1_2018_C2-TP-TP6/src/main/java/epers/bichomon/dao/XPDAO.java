package epers.bichomon.dao;

import epers.bichomon.model.entrenador.XPuntos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XPDAO extends JpaRepository<XPuntos, Integer> {
}
