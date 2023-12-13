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
        Ficha mejorFicha = null;
        int mejorPuntuacion = Integer.MIN_VALUE;

        for (Ficha ficha : fichas) {
            int puntuacion = evaluarFicha(ficha, mesa);
            if (puntuacion > mejorPuntuacion) {
                mejorPuntuacion = puntuacion;
                mejorFicha = ficha;
            }
        }

        if (mejorFicha != null) {
            int indice = fichas.indexOf(mejorFicha);
            if (mejorFicha.getCaraIzq() == mesa.get(0).getCaraIzq()) {
                return "i" + indice;
            } else if (mejorFicha.getCaraDer() == mesa.get(0).getCaraIzq()) {
                return "i" + indice;
            } else if (mejorFicha.getCaraIzq() == mesa.get(mesa.size() - 1).getCaraDer()) {
                return "d" + indice;
            } else if (mejorFicha.getCaraDer() == mesa.get(mesa.size() - 1).getCaraDer()) {
                return "d" + indice;
            }
        }

        return elegirFichaGenerica(mesa);
    }

    private int evaluarFicha(Ficha ficha, ArrayList<Ficha> mesa) {
        int cantidadPosibilidades = contarPosibilidades(ficha, mesa);
        return cantidadPosibilidades;
    }

    private int contarPosibilidades(Ficha ficha, ArrayList<Ficha> mesa) {
        int cantidadPosibilidades = 0;

        for (Ficha mesaFicha : mesa) {
            if (ficha.getCaraIzq() == mesaFicha.getCaraIzq() || ficha.getCaraDer() == mesaFicha.getCaraDer()) {
                cantidadPosibilidades++;
            }
            if (ficha.getCaraIzq() == mesaFicha.getCaraDer() || ficha.getCaraDer() == mesaFicha.getCaraIzq()) {
                cantidadPosibilidades++;
            }
        }

        return cantidadPosibilidades;
    }

    private String elegirFichaGenerica(ArrayList<Ficha> mesa) {
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
