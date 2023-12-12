package domino;

import java.util.ArrayList;

/**
 * Esta clase representa un jugador bot de domino
 */

public class Bot extends Jugador {
    /**
     * Metodo constructor
     * @param nombre Nombre del bot
     */
    public Bot(String nombre) {
        this.nombre = nombre;
    }

    @Override
    protected String buscarFicha(ArrayList<Ficha> mesaActual) {
        for (Ficha ficha : fichas) {
            if (ficha.getCaraIzq() == mesaActual.get(0).getCaraIzq())
                return "i" + fichas.indexOf(ficha);
            else if (ficha.getCaraDer() == mesaActual.get(0).getCaraIzq()) 
                return "i" + fichas.indexOf(ficha);
            else if (ficha.getCaraIzq() == mesaActual.get(mesaActual.size() - 1).getCaraDer()) 
                return "d" + fichas.indexOf(ficha);
            else if (ficha.getCaraDer() == mesaActual.get(mesaActual.size() - 1).getCaraDer()) 
                return "d" + fichas.indexOf(ficha);
        }

        return null;
    }
}
