package phx;

import static phx.Value._0;
import static phx.Value._2;
import static phx.Value._4;

import java.util.HashMap;

/**
 * Clase que representa una casilla en el juego 2048.
 */
public class Tile {

    /** Valor de la casilla. */
    private final Value val;

    /** Caché de instancias de Tile para valores frecuentemente utilizados. */
    private final static HashMap<Integer, Tile> cache = new HashMap<>();

    /**
     * Instancias reutilizables para valores comunes.
     */
    public final static Tile ZERO = new Tile(_0);
    public final static Tile TWO = new Tile(_2);
    public final static Tile FOUR = new Tile(_4);

    static {
        for (Value v : Value.values()) {
            switch (v) {
            case _0:
                cache.put(v.score(), ZERO);
                break;
            case _2:
                cache.put(v.score(), TWO);
                break;
            case _4:
                cache.put(v.score(), FOUR);
                break;
            default:
                cache.put(v.score(), new Tile(v));
                break;
            }
        }
    }

    /**
     * Constructor de la clase Tile.
     *
     * @param v Valor de la casilla.
     */
    public Tile(Value v) {
        val = v;
    }

    /**
     * Método de fábrica para obtener una instancia de Tile.
     *
     * @param num Número que representa el valor de la casilla.
     * @return Instancia de Tile correspondiente al número proporcionado.
     */
    public static Tile valueOf(int num) {
        return cache.get(num);
    }

    /**
     * Obtiene el valor de la casilla.
     *
     * @return Valor de la casilla.
     */
    Value value() {
        return val;
    }

    /**
     * Utilizado para la fusión, duplica el puntaje.
     *
     * @return Una nueva instancia de Tile cuyo valor es el doble.
     */
    public Tile getDouble() {
        return valueOf(val.score() << 1);
    }

    /**
     * Comprueba si la casilla está vacía o no.
     *
     * @return true si la casilla está vacía, false en caso contrario.
     */
    boolean empty() {
        return val == _0;
    }

    
    /**
     * Devuelve una representación en formato de cadena de la casilla.
     *
     * @return Cadena con el valor de la casilla formateado.
     */
    @Override
    public String toString() {
        return String.format("%1$4d", val.score());
    }

    /**
     * Calcula el código hash de la casilla.
     *
     * @return Código hash de la casilla.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((val == null) ? 0 : val.hashCode());
        return result;
    }

    /**
     * Compara la casilla con otro objeto para determinar si son iguales.
     *
     * @param obj Objeto a comparar con la casilla.
     * @return true si las casillas son iguales, false de lo contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Tile))
            return false;
        Tile other = (Tile) obj;
        if (val != other.val)
            return false;
        return true;
    }

    /**
     * Genera una casilla con valor 2 o 4, con mayores probabilidades de obtener un 2.
     *
     * @return Nueva instancia de Tile con valor aleatorio (2 o 4).
     */
    static Tile randomTile() {
        return Math.random() < 0.15 ? FOUR : TWO;
    }
}
