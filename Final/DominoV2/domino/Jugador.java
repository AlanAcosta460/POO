package domino;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Clase abstracta que representa un jugador de domino.
 */
public abstract class Jugador implements Serializable {
    protected String nombre;
    protected ArrayList<Ficha> fichas = new ArrayList<>();
    protected boolean puedeJugar = true;

    /**
     * Obtiene el nombre del jugador.
     * @return Nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene las fichas del jugador.
     * @return Lista de fichas del jugador.
     */
    public ArrayList<Ficha> getFichas() {
        return fichas;
    }

    /**
     * Obtiene una ficha especifica del jugador.
     * @param i Indice de la ficha.
     * @return Ficha del jugador.
     */
    public Ficha getFicha(int i) {
        return fichas.get(i);
    }

    /**
     * Agrega una ficha a las fichas del jugador.
     * @param ficha Ficha a agregar.
     */
    public void agregarFicha(Ficha ficha) {
        fichas.add(ficha);
    }

    /**
     * Indica si el jugador puede jugar.
     * @return `true` si el jugador puede jugar, `false` si no.
     */
    public boolean puedeJugar() {
        return puedeJugar;
    }

    /**
     * Verifica si el jugador puede jugar con las fichas disponibles.
     * @param mesa Fichas de la mesa actual.
     * @return `true` si el jugador puede jugar, `false` si no.
     */
    public boolean puedeJugar(ArrayList<Ficha> mesa) {
        puedeJugar = fichas.stream().anyMatch(ficha ->
                ficha.getCaraIzq() == mesa.get(0).getCaraIzq() ||
                        ficha.getCaraDer() == mesa.get(0).getCaraIzq() ||
                        ficha.getCaraIzq() == mesa.get(mesa.size() - 1).getCaraDer() ||
                        ficha.getCaraDer() == mesa.get(mesa.size() - 1).getCaraDer()
        );
        return puedeJugar;
    }

    /**
     * Roba una ficha del pozo y la agrega a las fichas del jugador.
     * @param pozo Pozo de fichas.
     */
    public void robar(ArrayList<Ficha> pozo) {
        fichas.add(pozo.get(0));
        pozo.remove(0);
    }

    /**
     * Realiza la jugada del primer turno, eligiendo la ficha mas alta.
     * @param mesa Fichas de la mesa.
     */
    public void primerTurno(ArrayList<Ficha> mesa) {
        int indice = -1;
        int max = -1;

        for (Ficha ficha : fichas) {
            if (ficha.esMula() && ficha.getSuma() > max) {
                max = ficha.getSuma();
                indice = fichas.indexOf(ficha);
            }
        }

        if (indice != -1)
            System.out.println("\n" + nombre + " juega la mula mas alta: " + fichas.get(indice));
        else {
            for (Ficha ficha : fichas) {
                if (ficha.getSuma() > max) {
                    max = ficha.getSuma();
                    indice = fichas.indexOf(ficha);
                }
            }
            System.out.println("\n" + nombre + " juega la ficha mas alta: " + fichas.get(indice));
        }

        mesa.add(fichas.get(indice));
        fichas.remove(indice);
    }

    /**
     * Realiza la jugada en un turno normal.
     * @param mesa Fichas de la mesa.
     */
    public final void turno(ArrayList<Ficha> mesa) {
        int indice = -1;
        char orientacion;
        String respuesta;
        boolean validacion;

        do {
            respuesta = buscarFicha(mesa);
            orientacion = respuesta.charAt(0);
            indice = Integer.parseInt(respuesta.substring(1));
            validacion = validarFicha(mesa, indice, orientacion);
            if (!validacion)
                System.out.println("Ficha invalida\n");
        } while (!validacion);

        System.out.println("\n" + nombre + " juegas la ficha: " + (indice + 1) + " - " + fichas.get(indice));

        jugarFicha(mesa, indice, orientacion);
    }

    /**
     * Metodo abstracto para buscar la ficha a jugar.
     * @param mesa Fichas de la mesa.
     * @return Representacion de la jugada (orientacion + indice).
     */
    public abstract String buscarFicha(ArrayList<Ficha> mesa);

    /**
     * Valida si una ficha puede ser jugada en la mesa en la orientacion indicada.
     * @param mesa Fichas de la mesa actual.
     * @param indice indice de la ficha a jugar.
     * @param orientacion Orientacion de la ficha ('i' o 'd').
     * @return true si la ficha es valida para jugar, false si no.
     */
    private boolean validarFicha(ArrayList<Ficha> mesa, int indice, char orientacion) {
        switch (orientacion) {
            case 'i':
                if (fichas.get(indice).getCaraIzq() == mesa.get(0).getCaraIzq()) {
                    fichas.get(indice).girar();
                    return true;
                }
                if (fichas.get(indice).getCaraDer() == mesa.get(0).getCaraIzq()) 
                    return true;
                return false;

            case 'd':
                if (fichas.get(indice).getCaraDer() == mesa.get(mesa.size() - 1).getCaraDer()) {
                    fichas.get(indice).girar();
                    return true;
                }
                if (fichas.get(indice).getCaraIzq() == mesa.get(mesa.size() - 1).getCaraDer()) 
                    return true;
                return false;

            default:
                return false;   
        }
    }

    /**
     * Juega la ficha en la mesa segun la orientación.
     * Este metodo es sincronizado para evitar que dos jugadores jueguen la misma ficha.
     * @param mesa Fichas de la mesa.
     * @param indice Indice de la ficha a jugar.
     * @param orientacion Orientacion de la ficha ('i' o 'd').
     */
    private synchronized void jugarFicha(ArrayList<Ficha> mesa, int indice, char orientacion) {
        if (orientacion == 'i')
            mesa.add(0, fichas.get(indice));
        else if (orientacion == 'd')
            mesa.add(fichas.get(indice));
        fichas.remove(indice);
    }
}