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
    protected String buscarFicha(ArrayList<Ficha> mesa) {
        for (Ficha ficha : fichas) {
            if (ficha.getCaraIzq() == mesa.get(0).getCaraIzq())
                return "i" + fichas.indexOf(ficha);
            else if (ficha.getCaraDer() == mesa.get(0).getCaraIzq()) 
                return "i" + fichas.indexOf(ficha);
            else if (ficha.getCaraIzq() == mesa.get(mesa.size() - 1).getCaraDer()) 
                return "d" + fichas.indexOf(ficha);
            else if (ficha.getCaraDer() == mesa.get(mesa.size() - 1).getCaraDer()) 
                return "d" + fichas.indexOf(ficha);
        }

        return null;
    }
}
