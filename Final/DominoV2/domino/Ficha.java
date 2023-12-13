package domino;

import java.io.Serializable;

/**
 * Representa una ficha de domino con dos caras.
 * Cada cara tiene un numero, y la ficha puede ser una "mula" si ambas caras son iguales.
 */
public class Ficha implements Serializable {
    private int caraIzq;
    private int caraDer;
    private int suma;
    private boolean mula;

    /**
     * Constructor que recibe las dos caras de la ficha.
     * @param caraIzq Numero de la cara izquierda.
     * @param caraDer Numero de la cara derecha.
     */
    public Ficha(int caraIzq, int caraDer) {
        this.caraIzq = caraIzq;
        this.caraDer = caraDer;
        this.suma = caraIzq + caraDer;
        this.mula = (caraIzq == caraDer);
    }

    /**
     * Obtiene el numero de la cara izquierda.
     * @return Numero de la cara izquierda.
     */
    public int getCaraIzq() {
        return caraIzq;
    }

    /**
     * Obtiene el numero de la cara derecha.
     * @return Numero de la cara derecha.
     */
    public int getCaraDer() {
        return caraDer;
    }

    /**
     * Obtiene la suma de las caras.
     * @return Suma de las caras.
     */
    public int getSuma() {
        return suma;
    }

    /**
     * Comprueba si la ficha es una mula (ambas caras son iguales).
     * @return `true` si la ficha es una mula, `false` si no.
     */
    public boolean esMula() {
        return mula;
    }

    /**
     * Gira la ficha intercambiando las caras izquierda y derecha.
     */
    public void girar() {
        int aux = caraIzq;
        caraIzq = caraDer;
        caraDer = aux;
    }

    /**
     * Representacion en cadena de la ficha con el formato [caraIzq|caraDer].
     * @return Cadena que representa la ficha.
     */
    @Override
    public String toString() {
        return "[" + caraIzq + "|" + caraDer + "]";
    }
}
