package epers.bichomon.service.mapa;

public class UbicacionActualException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UbicacionActualException(String ubicacion) {
        super("Ya se encuentra en la ubicacion [" + ubicacion + "]");
    }
}
