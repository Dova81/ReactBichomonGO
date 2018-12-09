package epers.bichomon.model.ubicacion;

public class BichoCampeonActualException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    BichoCampeonActualException(Integer bicho) {
        super("Bicho es el actual campeon [" + bicho.toString() + "]");
    }
}
