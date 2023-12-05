package domino;

import java.util.ArrayList;

/**
 * Esta clase abstracta representa un jugador de domino
 */

public abstract class Jugador {
    protected String nombre;
    protected ArrayList<Ficha> fichas = new ArrayList<Ficha>();
    protected boolean puedeJugar = true;

    /**
     * Metodo getter para el nombre del jugador
     * @return Nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo getter para las fichas del jugador
     * @return Fichas del jugador
     */
    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    /**
     * Metodo getter para una ficha especifica del jugador
     * @param i Indice de la ficha
     * @return Ficha del jugador
     */
    public Ficha getFicha(int i) {
        return fichas.get(i);
    }
    
    /**
     * Metodo getter para la cantidad de fichas del jugador
     * @return Cantidad de fichas del jugador
     */
    public int getCantidadFichas() {
        return fichas.size();
    }
    
    /**
     * Agrega una ficha a las fichas del jugador
     * @param ficha Ficha a agregar
     */
    public void agregarFicha(Ficha ficha) {
        fichas.add(ficha);
    }

    /**
     * Metodo getter para saber si el jugador puede jugar
     * @return true si el jugador puede jugar, false si no
     */
    public boolean puedeJugar() {
        return puedeJugar;
    }

    /**
     * Metodo setter para saber si el jugador puede jugar
     * @param mesaActual La fichas de la mesa actual
     * @return puedeJugar true si el jugador puede jugar, false si no
     */
    public boolean puedeJugar(ArrayList<Ficha> mesaActual) {
        for (Ficha ficha : fichas) {
            if (ficha.getCaraIzq() == mesaActual.get(0).getCaraIzq() || 
                    ficha.getCaraDer() == mesaActual.get(0).getCaraIzq() || 
                    ficha.getCaraIzq() == mesaActual.get(mesaActual.size() - 1).getCaraDer() || 
                    ficha.getCaraDer() == mesaActual.get(mesaActual.size() - 1).getCaraDer()) {
                puedeJugar = true;    
                return puedeJugar;
            }
        }
        puedeJugar = false;
        return puedeJugar;
    }

    /**
     * Metodo para robar una ficha del pozo
     * @param pozo El pozo de fichas
     */
    public void robar(ArrayList<Ficha> pozo) {
        fichas.add(pozo.get(0));
        pozo.remove(0);
    }

    /**
     * Metodo abstracto para el primer turno del jugador
     * @param mesaActual La fichas de la mesa actual
     */
    public abstract void primerTurno(ArrayList<Ficha> mesaActual);

    /**
     * Metodo abstracto para el turno del jugador
     * @param mesaActual La fichas de la mesa actual
     */
    public abstract void turno(ArrayList<Ficha> mesaActual);
}