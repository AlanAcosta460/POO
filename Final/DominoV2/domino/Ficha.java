package domino;

/**
 * Esta clase representa una ficha de domino con dos caras
 */

public class Ficha {
    private int caraIzq;
    private int caraDer;
    private int suma;
    private boolean mula = false;

    /**
     * Metodo constructor que recibe las dos caras de la ficha
     * @param caraIzq El numero de la cara izquierda
     * @param caraDer El numero de la cara derecha
     */
    public Ficha(int caraIzq, int caraDer) {
        this.caraIzq = caraIzq;
        this.caraDer = caraDer;
        this.suma = caraIzq + caraDer;
        if (caraIzq == caraDer)
            mula = true;
    }

    /**
     * Metodo getter para la cara izquierda
     * @return Numero de la cara izquierda
     */
    public int getCaraIzq() {
        return caraIzq;
    }

    /**
     * Metodo getter para la cara derecha
     * @return Numero de la cara derecha
     */
    public int getCaraDer() {
        return caraDer;
    }

    /**
     * Metodo getter para la suma de las caras
     * @return Suma de las caras
     */
    public int getSuma() {
        return suma;
    }

    /**
     * Metodo getter para saber si la ficha es mula
     * @return true si la ficha es mula, false si no
     */
    public boolean esMula() {
        return mula;
    }

    /**
     * Cambia la cara izquierda por la derecha y viceversa
     */
    public void girar() {
        int aux = caraIzq;
        caraIzq = caraDer;
        caraDer = aux;
    }

    /**
     * Imprime la ficha con el formato [caraIzq|caraDer]
     */
    public String toString() {
        return "[" + caraIzq + "|" + caraDer + "]";
    }
}