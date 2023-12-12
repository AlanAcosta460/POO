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
        puedeJugar = false;
        for (Ficha ficha : fichas)
            if (ficha.getCaraIzq() == mesaActual.get(0).getCaraIzq() || 
                    ficha.getCaraDer() == mesaActual.get(0).getCaraIzq() || 
                    ficha.getCaraIzq() == mesaActual.get(mesaActual.size() - 1).getCaraDer() || 
                    ficha.getCaraDer() == mesaActual.get(mesaActual.size() - 1).getCaraDer()) 
                puedeJugar = true;    
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

    public void primerTurno(ArrayList<Ficha> mesaActual) {
        int indice = -1; 
        int max = -1;

        for (Ficha ficha : fichas) {
            if (ficha.esMula() && ficha.getSuma() > max) {
                max = ficha.getSuma();
                indice = fichas.indexOf(ficha);
            }
        }

        if (indice != -1)
            System.out.println(nombre + " juega la mula mas alta: " + fichas.get(indice));
        else {
            for (Ficha ficha : fichas) {
                if (ficha.getSuma() > max) {
                    max = ficha.getSuma();
                    indice = fichas.indexOf(ficha);
                }
            }
            System.out.println(nombre + " juega la ficha mas alta: " + fichas.get(indice));
        }

        mesaActual.add(fichas.get(indice));
        fichas.remove(indice);
    }

    public final void turno(ArrayList<Ficha> mesaActual) {
        int indice = -1;
        char orientacion;
        String respuesta;

        do {
            respuesta = buscarFicha(mesaActual);
            orientacion = respuesta.charAt(0);
            indice = Integer.parseInt(respuesta.substring(1));  
        } while (!validarFicha(mesaActual, indice, orientacion));

        System.out.println(nombre + " juegas la ficha: " + (indice + 1) + " - " + fichas.get(indice));

        jugarFicha(mesaActual, indice, orientacion);
    }

    protected abstract String buscarFicha(ArrayList<Ficha> mesaActual);

    private boolean validarFicha(ArrayList<Ficha> mesaActual, int indice, char orientacion) {
        switch (orientacion) {
            case 'i':
                if (fichas.get(indice).getCaraIzq() == mesaActual.get(0).getCaraIzq()) {
                    fichas.get(indice).girar();
                    return true;
                }
                if (fichas.get(indice).getCaraDer() == mesaActual.get(0).getCaraIzq()) 
                    return true;
                return false;

            case 'd':
                if (fichas.get(indice).getCaraDer() == mesaActual.get(mesaActual.size() - 1).getCaraDer()) {
                    fichas.get(indice).girar();
                    return true;
                }
                if (fichas.get(indice).getCaraIzq() == mesaActual.get(mesaActual.size() - 1).getCaraDer()) 
                    return true;
                return false;

            default:
                return false;   
        }
    }

    private void jugarFicha(ArrayList<Ficha> mesaActual, int indice, char orientacion) {
        if (orientacion == 'i')
            mesaActual.add(0, fichas.get(indice));
        else if (orientacion == 'd')
            mesaActual.add(fichas.get(indice));
        fichas.remove(indice);
    }
}