package phx;

import java.io.Serializable;

/**
 * Clase que permite guardar los registros del archivo.
 */
public class Jugador implements Serializable {

    /** Nombre del jugador. */
    public String nickName;

    /** Valor máximo alcanzado en una casilla por el jugador. */
    public int maxTileValue;

    /** Número total de movimientos realizados por el jugador. */
    public int numberOfMoves;

    /**
     * Constructor de la clase Jugador.
     *
     * @param nickName      Nombre de usuario del jugador.
     * @param maxTileValue  Valor máximo alcanzado en una casilla.
     * @param numberOfMoves Número total de movimientos realizados.
     */
    public Jugador(String nickName, int maxTileValue, int numberOfMoves) {
        this.nickName = nickName;
        this.maxTileValue = maxTileValue;
        this.numberOfMoves = numberOfMoves;
    }  
}