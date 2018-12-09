package epers.bichomon.model.ubicacion;

import epers.bichomon.model.IRandom;
import epers.bichomon.model.bicho.Bicho;
import epers.bichomon.model.entrenador.Entrenador;
import epers.bichomon.model.especie.Especie;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Iterator;
import java.util.List;

@Entity
public class Pueblo extends Ubicacion {

    @OneToMany(cascade = CascadeType.ALL)
    private List<Probabilidad> probabilidades;

    @Transient
    private IRandom random = UbicacionFactory.getProbabilidadRandom();

    protected Pueblo() {
        super();
    }

    public Pueblo(String nombre, List<Probabilidad> probabilidades) {
        super(nombre);
        this.probabilidades = probabilidades;
    }

    private Especie getEspecie() {
        int nro = random.getInt(1, probabilidades.stream().mapToInt(p -> p.probabilidad).sum());
        int accum = 0;
        Probabilidad p = null;
        Iterator<Probabilidad> it = probabilidades.iterator();
        while (it.hasNext() && accum <= nro) {
            p = it.next();
            accum += p.probabilidad;
        }
        return p == null ? null : p.especie;
    }

    @Override
    protected Bicho buscarBicho(Entrenador entrenador) {
        Especie especie = getEspecie();
        if (especie == null) return null;
        return especie.crearBicho();
    }

    @Override
    public int getPoblacion() {
        return this.entrenadores.size();
    }
}
