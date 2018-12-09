package epers.bichomon.dao;

import epers.bichomon.model.especie.Especie;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EspecieDAO extends JpaRepository<Especie, Integer> {
    Especie findByNombre(String nombreEspecie);

    List<Especie> findAllByOrderByNombreAsc();

    @Query("select i.especie from Bicho i where i.entrenador <> null group by i.especie order by count(*) desc, i.especie.nombre")
    List<Especie> getPopulares(Pageable pageable);

    default List<Especie> getPopulares() {
        return getPopulares(PageRequest.of(0, 10));
    }

    @Query("select i.especie from Bicho i where i.entrenador = null group by i.especie order by count(*), i.especie.nombre")
    List<Especie> getImpopulares(Pageable pageable);

    default List<Especie> getImpopulares() {
        return getImpopulares(PageRequest.of(0, 10));
    }

    @Query("select c.campeon.especie from Campeon c group by c.campeon.especie order by COUNT(DISTINCT c.campeon) desc, c.campeon.especie.nombre")
    List<Especie> lider(Pageable pageable);

    default Especie lider() {
        return lider(PageRequest.of(0, 1)).get(0);
    }
}
