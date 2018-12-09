package epers.bichomon.service;

import epers.bichomon.dao.*;
import epers.bichomon.model.bicho.Bicho;
import epers.bichomon.model.entrenador.Entrenador;
import epers.bichomon.model.entrenador.Nivel;
import epers.bichomon.model.entrenador.XPuntos;
import epers.bichomon.model.especie.Especie;
import epers.bichomon.model.especie.TipoBicho;
import epers.bichomon.model.especie.condicion.CondicionEnergia;
import epers.bichomon.model.especie.condicion.CondicionNivel;
import epers.bichomon.model.ubicacion.*;
import epers.bichomon.service.bicho.BichoService;
import epers.bichomon.service.mapa.MapaService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@Transactional
public class DataLoader implements ApplicationRunner {

    private MapaService mapaService;
    private BichoService bichoService;

    private XPDAO xpDAO;
    private NivelDAO nivelDAO;
    private EspecieDAO especieDAO;
    private UbicacionDAO ubicacionDAO;
    private BichoDAO bichoDAO;
    private EntrenadorDAO entrenadorDAO;
    private EventoDAO eventoDAO;

    public DataLoader(MapaService mapaService, BichoService bichoService, XPDAO xpDAO, NivelDAO nivelDAO,
                      EspecieDAO especieDAO, UbicacionDAO ubicacionDAO, BichoDAO bichoDAO,
                      EntrenadorDAO entrenadorDAO, EventoDAO eventoDAO) {
        this.mapaService = mapaService;
        this.bichoService = bichoService;
        this.xpDAO = xpDAO;
        this.nivelDAO = nivelDAO;
        this.especieDAO = especieDAO;
        this.ubicacionDAO = ubicacionDAO;
        this.bichoDAO = bichoDAO;
        this.entrenadorDAO = entrenadorDAO;
        this.eventoDAO = eventoDAO;
    }

    private Entrenador newEntrenador(String nombre, Set<Bicho> bichos) {
        return entrenadorDAO.save(new Entrenador(nombre, nivelDAO.findByNro(1),
                xpDAO.findById(1).orElseThrow(NotFoundException::new), bichos));
    }

    private Entrenador newEntrenador(String nombre, String ubicacion, Set<Bicho> bichos) {
        Entrenador e = newEntrenador(nombre, bichos);
        e.moverA(ubicacionDAO.get(ubicacion));
        return entrenadorDAO.save(e);
    }

    private Entrenador newEntrenador(String nombre, String ubicacion) {
        return newEntrenador(nombre, ubicacion, new HashSet<>(Collections.emptyList()));
    }

    private Bicho crearBicho(String especie) {
        return bichoDAO.save(especieDAO.findByNombre(especie).crearBicho());
    }

    @Override
    public void run(ApplicationArguments args) {
        eventoDAO.deleteAll();
//        ubicacionDAO.deleteAll();

        xpDAO.save(new XPuntos());
        nivelDAO.save(Nivel.create());
        this.createEspecies();
        this.createUbicaciones();

        Bicho b = crearBicho("Lagartomon");
        Ubicacion poke = ubicacionDAO.get("Poke");
        poke.abandonar(b);
        ubicacionDAO.save(poke);

        Bicho bichin = crearBicho("Rojomon");
        Ubicacion place = ubicacionDAO.get("St.Blah");
        place.abandonar(bichin);
        ubicacionDAO.save(place);

        newEntrenador("Pedro", "Poke");
        newEntrenador("Ash", "St.Blah");
        newEntrenador("Misty", "St.Blah");

        bichoService.buscar("Misty");

        Bicho b1 = crearBicho("Rojomon");
        Bicho b2 = crearBicho("Rojomon");
        newEntrenador("Pepe", "Poke", new HashSet<>(Arrays.asList(b1, b2)));
        bichoService.abandonar("Pepe", b1.getID());

        b1 = crearBicho("Amarillomon");
        b2 = crearBicho("Amarillomon");
        newEntrenador("Lucas", "Poke", new HashSet<>(Arrays.asList(b1, b2)));
        bichoService.abandonar("Lucas", b1.getID());

        b1 = crearBicho("Naranjamon");
        newEntrenador("Alberto", "Tibet Dojo", new HashSet<>(Arrays.asList(b1)));
        b2 = crearBicho("Turquesamon");
        newEntrenador("Julio", "Tibet Dojo", new HashSet<>(Arrays.asList(b2)));

        bichoService.duelo("Alberto", b1.getID());
        bichoService.duelo("Julio", b2.getID());

        newEntrenador("Andrea", "Tibet Dojo");
        bichoService.buscar("Andrea");

        newEntrenador("Ana", "Lagartolandia");
        bichoService.buscar("Ana");
        mapaService.mover("Ana", "Agualandia");
        mapaService.mover("Ana", "Plantalandia");
        mapaService.mover("Ana", "Agualandia");
    }

    private void createEspecies() {
        especieDAO.save(new Especie("Rojomon", TipoBicho.FUEGO, 100));
        especieDAO.save(new Especie("Amarillomon", TipoBicho.AIRE, 300));
        especieDAO.save(new Especie("Verdemon", TipoBicho.PLANTA, 500));
        especieDAO.save(new Especie("Violetamon", TipoBicho.TIERRA, 500));
        especieDAO.save(new Especie("Azulmon", TipoBicho.ELECTRICIDAD, 500));
        especieDAO.save(new Especie("Naranjamon", TipoBicho.CHOCOLATE, 500));
        especieDAO.save(new Especie("Marronmon", TipoBicho.AGUA, 500));
        especieDAO.save(new Especie("Lilamon", TipoBicho.AIRE, 500));
        especieDAO.save(new Especie("Celestemon", TipoBicho.AGUA, 500));
        especieDAO.save(new Especie("Ocremon", TipoBicho.FUEGO, 500));
        especieDAO.save(new Especie("Turquesamon", TipoBicho.PLANTA, 1500));

        Especie Dragonmon = especieDAO.save(new Especie("Dragonmon", TipoBicho.FUEGO, 10));
        Especie Reptilomon = especieDAO.save(new Especie("Reptilomon", TipoBicho.FUEGO, 10,
                Dragonmon, new HashSet<>(Arrays.asList(new CondicionNivel(2), new CondicionEnergia(10)))));
        Especie Lagartomon = especieDAO.save(new Especie("Lagartomon", TipoBicho.FUEGO, 10,
                Reptilomon, new HashSet<>(Arrays.asList(new CondicionNivel(1)))));
        Dragonmon.setRaiz(Lagartomon);
        especieDAO.save(Dragonmon);
        Reptilomon.setRaiz(Lagartomon);
        especieDAO.save(Reptilomon);
    }

    private void createUbicaciones() {
        ubicacionDAO.save(new Pueblo("Plantalandia", Collections.singletonList(
                new Probabilidad(especieDAO.findByNombre("Rojomon"), 100))));
        ubicacionDAO.save(new Pueblo("Agualandia", Collections.singletonList(
                new Probabilidad(especieDAO.findByNombre("Amarillomon"), 100))));
        ubicacionDAO.save(new Pueblo("Bicholandia", Collections.singletonList(
                new Probabilidad(especieDAO.findByNombre("Verdemon"), 100))));
        ubicacionDAO.save(new Pueblo("Lagartolandia", Collections.singletonList(
                new Probabilidad(especieDAO.findByNombre("Lagartomon"), 100))));
        ubicacionDAO.save(new Pueblo("Isla", Collections.singletonList(
                new Probabilidad(especieDAO.findByNombre("Lilamon"), 100))));
        ubicacionDAO.save(new Guarderia("St.Blah"));
        ubicacionDAO.save(new Guarderia("Poke"));
        ubicacionDAO.save(new Dojo("Tibet Dojo"));
        ubicacionDAO.save(new Dojo("CobraKai"));
        ubicacionDAO.save(new Dojo("Miyagi"));
        ubicacionDAO.conectar("St.Blah", "Plantalandia", TipoCamino.Aereo);
        ubicacionDAO.conectar("St.Blah", "Agualandia", TipoCamino.Terrestre);
        ubicacionDAO.conectar("Agualandia", "St.Blah", TipoCamino.Terrestre);
        ubicacionDAO.conectar("Agualandia", "Plantalandia", TipoCamino.Maritimo);
        ubicacionDAO.conectar("Plantalandia", "Agualandia", TipoCamino.Maritimo);
        ubicacionDAO.conectar("Agualandia", "Lagartolandia", TipoCamino.Maritimo);
        ubicacionDAO.conectar("Lagartolandia", "Agualandia", TipoCamino.Maritimo);
        ubicacionDAO.conectar("Agualandia", "Bicholandia", TipoCamino.Maritimo);
        ubicacionDAO.conectar("Bicholandia", "Lagartolandia", TipoCamino.Terrestre);
        ubicacionDAO.conectar("Lagartolandia", "Bicholandia", TipoCamino.Terrestre);
        ubicacionDAO.conectar("Bicholandia", "Tibet Dojo", TipoCamino.Aereo);
        ubicacionDAO.conectar("Tibet Dojo", "Bicholandia", TipoCamino.Aereo);
        ubicacionDAO.conectar("Tibet Dojo", "Plantalandia", TipoCamino.Terrestre);
        ubicacionDAO.conectar("Poke", "Plantalandia", TipoCamino.Terrestre);
        ubicacionDAO.conectar("CobraKai", "Poke", TipoCamino.Aereo);
        ubicacionDAO.conectar("Miyagi", "CobraKai", TipoCamino.Aereo);
    }
}
