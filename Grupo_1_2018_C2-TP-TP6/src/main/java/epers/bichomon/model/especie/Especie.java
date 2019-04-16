package epers.bichomon.model.especie;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import epers.bichomon.model.bicho.Bicho;
import epers.bichomon.model.especie.condicion.Condicion;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Especie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nombre;

    private int altura;

    private int peso;

    @Enumerated(EnumType.STRING)
    private TipoBicho tipo;

    private int energiaInicial;

    private String urlFoto;

    private int cantidadBichos;

    @OneToOne
    @JsonManagedReference
    private Especie evolucion;

    @OneToOne
    @JsonBackReference
    private Especie raiz;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Condicion> condiciones;

    protected Especie() {
    }

    public Especie(String nombre, TipoBicho tipo, int energia) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.energiaInicial = energia;
    }

    public Especie(String nombre, TipoBicho tipo, int energia,String urlFoto) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.energiaInicial = energia;
        this.urlFoto = urlFoto;
    }

    public Especie(String nombre, TipoBicho tipo, int energia, Especie evolucion, Set<Condicion> condiciones) {
        this(nombre, tipo, energia);
        this.evolucion = evolucion;
        this.condiciones = condiciones;
    }

    public Integer getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public Especie getEvolucion() {
        return this.evolucion;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getUrlFoto() {
        return this.urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getEnergiaInicial() {
        return this.energiaInicial;
    }

    public void setEnergiaInicial(int energiaInicial) {
        this.energiaInicial = energiaInicial;
    }

    public TipoBicho getTipo() {
        return this.tipo;
    }

    public void setTipo(TipoBicho tipo) {
        this.tipo = tipo;
    }

    public int getCantidadBichos() {
        return this.cantidadBichos;
    }

    public void setCantidadBichos(int i) {
        this.cantidadBichos = i;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Bicho crearBicho() {
        this.cantidadBichos++;
        return new Bicho(this);
    }

    public Boolean puedeEvolucionar(Bicho bicho) {
        return this.evolucion != null && condiciones.stream().allMatch(c -> c.puedeEvolucionar(bicho));
    }

    public Especie getRaiz() {
        if (raiz == null) return this;
        return raiz;
    }

    public void setRaiz(Especie raiz) {
        this.raiz = raiz;
    }
}
