package epers.bichomon.model.entrenador;

import javax.persistence.*;

@Entity
public class Nivel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private Integer nro;

    private Integer limite;

    @OneToOne(cascade = CascadeType.ALL)
    private Nivel next;

    private int cantMax;

    protected Nivel() {
    }

    public Nivel(Integer nro, Integer limite, Nivel next) {
        this(nro, limite);
        this.next = next;
    }

    public Nivel(Integer nro, Integer limite) {
        this.nro = nro;
        this.limite = limite;
        this.cantMax = nro * 3;
    }

    void subeNivel(Entrenador entrenador) {
        if (this.next != null && entrenador.getXP() > this.limite) {
            entrenador.setNivel(this.next);
            this.next.subeNivel(entrenador);
        }
    }

    Integer getCantMax() {
        return this.cantMax;
    }

    Integer getNro() {
        return this.nro;
    }

    public static Nivel create() {
        Nivel n = new Nivel(10, 8000);
        for (int i = 9; i >= 3; i--) {
            n = new Nivel(i, (i - 2) * 1000, n);
        }
        return new Nivel(1, 99, new Nivel(2, 400, n));
    }
}