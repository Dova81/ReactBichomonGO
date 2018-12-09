package epers.bichomon.dao;

import epers.bichomon.model.bicho.Bicho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BichoDAO extends JpaRepository<Bicho, Integer> {
}
