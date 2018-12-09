package epers.bichomon.model.ubicacion;

import epers.bichomon.model.bicho.Bicho;
import epers.bichomon.model.entrenador.Entrenador;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Guarderia extends Ubicacion {

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Bicho> bichos = new HashSet<>();

    protected Guarderia() {
        super();
    }

    public Guarderia(String nombreGuarderia) {
        super(nombreGuarderia);
    }

    @Override
    public void abandonar(Bicho bicho) {
        bichos.add(bicho);
    }

    @Override
    protected Bicho buscarBicho(Entrenador entrenador) {
        Bicho bicho = bichos.stream().filter(b -> !b.tuvisteEntrenador(entrenador)).collect(Collectors.toList()).get(0);
        bichos.remove(bicho);
        return bicho;
    }

    public boolean contains(Bicho bicho) {
        return bichos.stream().anyMatch(b -> b.equals(bicho));
    }
}
