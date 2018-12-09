package epers.bichomon.dao;

import epers.bichomon.model.bicho.Bicho;
import epers.bichomon.model.ubicacion.Ubicacion;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UbicacionDAOHib extends JpaRepository<Ubicacion, Integer> {

    Ubicacion findByNombre(String ubicacion);

    List<Ubicacion> findByIdIn(List<Integer> ids);

    @Query("select c.campeon from Dojo d inner join d.campeones c where d.nombre = :dojo order by DATEDIFF(IFNULL(c.hasta,NOW()),c.desde) desc")
    List<Bicho> campeonHistorico(String dojo, Pageable pageable);

    default Bicho campeonHistorico(String dojo) {
        return campeonHistorico(dojo, PageRequest.of(0, 1)).get(0);
    }
}
