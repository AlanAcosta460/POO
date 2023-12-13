package domino;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Esta clase representa el modelo del juego de domino.
 */
public class Modelo {
    private ArrayList<Ficha> mesa = new ArrayList<Ficha>();
    private ArrayList<Ficha> pozo = new ArrayList<Ficha>();
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private int turno;

    /**
     * Constructor para una partida entre una persona y un bot.
     * @param nombre Nombre del jugador persona.
     */
    public Modelo(String nombre) {
        jugadores.add(new Persona(nombre));
        jugadores.add(new Bot("Bot"));

        repartirFichas();
        decidirPrimerTurno();
        turno = 1;
    }

    /**
     * Constructor para una partida entre dos bots.
     */
    public Modelo() {
        jugadores.add(new Bot("Bot 1"));
        jugadores.add(new Bot("Bot 2"));

        repartirFichas();
        decidirPrimerTurno();
        turno = 1;
    }

    /**
     * Constructor para cargar una partida desde una configuracion.
     * @param conf Configuracion de la partida guardada.
     */
    public Modelo(Configuracion conf) {
        pozo = conf.getPozo();
        jugadores = conf.getJugadores();
        turno = 1;
    }

    /**
     * Obtiene las fichas de la mesa.
     * @return Lista de fichas en la mesa.
     */
    public ArrayList<Ficha> getMesa() {
        return mesa;
    }

    /**
     * Obtiene el tamaño de la mesa.
     * @return Tamaño de la mesa.
     */
    public int getTamanioMesa() {
        return mesa.size();
    }

    /**
     * Obtiene las fichas en el pozo.
     * @return Lista de fichas en el pozo.
     */
    public ArrayList<Ficha> getPozo() {
        return pozo;
    }

    /**
     * Obtiene el tamaño del pozo.
     * @return Tamaño del pozo.
     */
    public int getTamanioPozo() {
        return pozo.size();
    }

    /**
     * Obtiene la lista de jugadores.
     * @return Lista de jugadores.
     */
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Obtiene un jugador especifico.
     * @param i Indice del jugador.
     * @return Jugador en la posicion especificada.
     */
    public Jugador getJugador(int i) {
        return jugadores.get(i);
    }

    /**
     * Obtiene el turno actual.
     * @return Numero del turno.
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Reparte las fichas a los jugadores.
     */
    private void repartirFichas() {
        Random random = new Random();

        for (int i = 0; i <= 6; i++) 
            for (int j = i; j <= 6; j++)
                pozo.add(new Ficha(i, j));

        pozo = pozo.stream().sorted((ficha1, ficha2) -> random.nextInt(3) - 1) 
                    .collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < 7; i++)
            jugadores.forEach(jugador -> jugador.agregarFicha(pozo.remove(0)));
    }

    /**
     * Decide quiden empieza el primer turno.
     */
    private void decidirPrimerTurno() {
        int max = -1, posicion = -1;

        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 7; i++) {
                if (jugador.getFicha(i).esMula() && jugador.getFicha(i).getSuma() > max) {
                    max = jugador.getFicha(i).getSuma();
                    posicion = jugadores.indexOf(jugador);
                }
            }
        }

        if (posicion != -1) {
            if (posicion == 1) 
                cambiarTurno();
            return;
        }

        max = -1;
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 7; i++) {
                if (jugador.getFicha(i).getSuma() > max) {
                    max = jugador.getFicha(i).getSuma();
                    posicion = jugadores.indexOf(jugador);
                }
            }
        }

        if (posicion == 1) 
            cambiarTurno();
    }

    /**
     * Cambia el turno entre los jugadores.
     */
    public void cambiarTurno() {
        Jugador aux = jugadores.get(0);
        jugadores.set(0, jugadores.get(1));
        jugadores.set(1, aux);
        turno++;
    }
}
