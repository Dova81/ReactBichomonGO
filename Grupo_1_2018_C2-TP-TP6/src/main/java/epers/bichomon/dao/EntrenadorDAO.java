package epers.bichomon.dao;

import epers.bichomon.model.entrenador.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EntrenadorDAO extends JpaRepository<Entrenador, Integer> {
    Entrenador findByNombre(String nombreEntrenador);

    Integer countByUbicacion_Nombre(String ubicacion);

    @Query("select c.campeon.entrenador from Campeon c where c.hasta is null group by c.campeon.entrenador order by min(c.desde)")
    List<Entrenador> campeones();

    @Query("select b.entrenador from Bicho b where b.entrenador is not null group by b.entrenador order by sum(b.energia) desc")
    List<Entrenador> lideres();
}
