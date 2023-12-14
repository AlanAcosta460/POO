package domino;

import java.util.ArrayList;

/**
 * Esta clase representa un jugador bot de domino.
 * Extiende la clase abstracta Jugador.
 */
public class Bot extends Jugador {
    /**
     * Constructor de la clase Bot.
     * @param nombre Nombre del bot.
     */
    public Bot(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo para que el bot elija una ficha para jugar en la mesa.
     * Evalua sus fichas disponibles y selecciona la que maximiza sus posibilidades de juego.
     * @param mesa La lista de fichas en la mesa.
     * @return Intruccion que representa la eleccion de la ficha y su orientacion ('i' o 'd').
     */
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
            if (mejorFicha.getCaraIzq() == mesa.get(0).getCaraIzq()) 
                return "i" + indice;
            else if (mejorFicha.getCaraDer() == mesa.get(0).getCaraIzq()) 
                return "i" + indice;
            else if (mejorFicha.getCaraIzq() == mesa.get(mesa.size() - 1).getCaraDer()) 
                return "d" + indice;
            else if (mejorFicha.getCaraDer() == mesa.get(mesa.size() - 1).getCaraDer()) 
                return "d" + indice;
        }

        return elegirFichaGenerica(mesa);
    }

    /**
     * Evalua una ficha especifica en funcion de las posibilidades de juego en la mesa.
     * @param ficha Ficha a evaluar.
     * @param mesa Lista de fichas en la mesa.
     * @return Cantidad de posibilidades de juego para la ficha.
     */
    private int evaluarFicha(Ficha ficha, ArrayList<Ficha> mesa) {
        int cantidadPosibilidades = contarPosibilidades(ficha, mesa);
        return cantidadPosibilidades;
    }

    /**
     * Cuenta la cantidad de posibilidades de juego para una ficha en la mesa.
     * @param ficha Ficha a evaluar.
     * @param mesa Lista de fichas en la mesa.
     * @return Cantidad de posibilidades de juego para la ficha.
     */
    private int contarPosibilidades(Ficha ficha, ArrayList<Ficha> mesa) {
        int cantidadPosibilidades = 0;

        for (Ficha mesaFicha : mesa) {
            if (ficha.getCaraIzq() == mesaFicha.getCaraIzq() || ficha.getCaraDer() == mesaFicha.getCaraDer()) 
                cantidadPosibilidades++;
            if (ficha.getCaraIzq() == mesaFicha.getCaraDer() || ficha.getCaraDer() == mesaFicha.getCaraIzq()) 
                cantidadPosibilidades++;
        }

        return cantidadPosibilidades;
    }

    /**
     * Elige una ficha generica si el bot no puede seleccionar una especifica.
     * @param mesa Lista de fichas en la mesa.
     * @return Intruccion que representa la eleccion de la ficha y su orientacion ('i' o 'd').
     */
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
