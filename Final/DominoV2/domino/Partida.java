package domino;

/**
 * Esta clase representa una partida de domino con informacion sobre el nombre del jugador,
 * el turno en el que finalizo la partida y la fecha en la que se jugo.
 */
public class Partida {
    private String nombre;
    private int turno;
    private String fecha;

    /**
     * Constructor de la clase Partida.
     * @param nombre Nombre del jugador.
     * @param turno Turno en el que finalizo la partida.
     * @param fecha Fecha en la que se jugo la partida.
     */
    public Partida(String nombre, int turno, String fecha) {
        this.nombre = nombre;
        this.turno = turno;
        this.fecha = fecha;
    }

    /**
     * Obtiene el nombre del jugador.
     * @return Nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el turno en el que finalizo la partida.
     * @return Turno en el que finalizo la partida.
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Obtiene la fecha en la que se jugo la partida.
     * @return Fecha en la que se jugo la partida.
     */
    public String getFecha() {
        return fecha;
    }
}
