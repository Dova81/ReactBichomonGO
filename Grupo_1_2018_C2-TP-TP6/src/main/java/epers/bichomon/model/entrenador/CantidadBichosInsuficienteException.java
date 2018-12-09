package epers.bichomon.model.entrenador;

public class CantidadBichosInsuficienteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public CantidadBichosInsuficienteException(Integer id) {
        super("Cantidad de bichos insuficiente para abandonar [" + id.toString() + "]");
    }
}
