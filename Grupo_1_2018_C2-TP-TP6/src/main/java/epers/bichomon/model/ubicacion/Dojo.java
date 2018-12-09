package epers.bichomon.model.ubicacion;

import com.fasterxml.jackson.annotation.JsonBackReference;
import epers.bichomon.model.bicho.Bicho;
import epers.bichomon.model.entrenador.Entrenador;
import epers.bichomon.model.ubicacion.duelo.Campeon;
import epers.bichomon.model.ubicacion.duelo.Duelo;
import epers.bichomon.model.ubicacion.duelo.ResultadoCombate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dojo extends Ubicacion {

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Campeon campeon;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Campeon> campeones = new HashSet<>();

    protected Dojo() {
        super();
    }

    public Dojo(String nombreDojo) {
        super(nombreDojo);
    }

    @Override
    protected Bicho buscarBicho(Entrenador entrenador) {
        if (campeon == null) return null;
        return campeon.getCampeon().getRaiz().crearBicho();
    }

    @Override
    public ResultadoCombate duelo(Bicho bicho) {
        if (campeon != null && campeon.getCampeon().equals(bicho))
            throw new BichoCampeonActualException(bicho.getID());
        ResultadoCombate resultado = new Duelo(campeon, bicho).getResultado();
        nuevoCampeon(resultado.getGanador());
        return resultado;
    }

    private void nuevoCampeon(Bicho ganador) {
        if (campeon != null) {
            if (campeon.getCampeon().equals(ganador)) return;
            campeon.derrotado();
        }
        campeon = new Campeon(ganador);
        campeones.add(campeon);
    }

    public Bicho getCampeon() {
        return campeon != null ? campeon.getCampeon() : null;
    }
}
