package domino;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Esta clase representa la mesa de juego
 */

public class Mesa {
    private static ArrayList<Ficha> mesa = new ArrayList<Ficha>();
    private static ArrayList<Ficha> pozo = new ArrayList<Ficha>();
    private static List<Jugador> jugadores = new ArrayList<Jugador>() {{
        add(new Humano("Jugador 1"));
        add(new Bot("Bot 1"));
        add(new Bot("Bot 2"));
    }};
    
    /**
     * Metodo constructor
     * @param tipoJuego Tipo de juego (1: Jugador vs Bot, 2: Bot vs Bot)
     */
    public Mesa(int tipoJuego) {
        for (int i = 0; i <= 6; i++) 
            for (int j = i; j <= 6; j++) 
                mesa.add(new Ficha(i, j));
        this.mezclar();

        if (tipoJuego == 1)  
            jugadores.remove(2);
        else if (tipoJuego == 2)
            jugadores.remove(0);

        this.repartir(); 
        this.decidirPrimerTurno();
        mesa.clear(); 
    }

    /**
     * Metodo getter para las fichas de la mesa
     * @return Fichas de la mesa
     */
    public ArrayList<Ficha> getMesa() {
        return mesa;
    }

    /**
     * Metodo getter para las fichas del pozo
     * @return Fichas del pozo
     */
    public ArrayList<Ficha> getPozo() {
        return pozo;
    }

    /**
     * Metodo getter para el ArrayList de jugadores
     * @return Jugadores
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Metodo para mezclar las fichas de la mesa
     */
    private void mezclar() {
        Random random = new Random();
        for (int i = 0; i < mesa.size(); i++) {
            int j = random.nextInt(mesa.size());
            Ficha aux = mesa.get(i);
            mesa.set(i, mesa.get(j));
            mesa.set(j, aux);
        }
    }

    /**
     * Metodo para repartir 7 fichas a cada jugador
     */
    private void repartir() {
        for (int i = 0; i < 7; i++) {
            jugadores.get(0).agregarFicha(mesa.get(i));
            jugadores.get(1).agregarFicha(mesa.get(i + 7));
        }
        for (int i = 14; i < mesa.size(); i++) 
            pozo.add(mesa.get(i));
    }

    /**
     * Metodo para decidir quien empieza el juego
     */
    private void decidirPrimerTurno() {
        int max = -1, mano = -1;

        // Busca la mula con el numero mas alto
        for (int i = 0; i < 7; i ++) { 
            if (jugadores.get(0).getFicha(i).esMula() && jugadores.get(0).getFicha(i).getSuma() > max) {
                max = jugadores.get(0).getFicha(i).getSuma();
                mano = 0;
            }
            if (jugadores.get(1).getFicha(i).esMula() && jugadores.get(1).getFicha(i).getSuma() > max) {
                max = jugadores.get(1).getFicha(i).getSuma();
                mano = 1;
            }
        }

        if (mano != -1) {
            if (mano == 1)
                cambioDeTurno();
            return;
        }
        
        max = -1;
        for (int i = 0; i < 7; i ++) {
            if (jugadores.get(0).getFicha(i).getSuma() > max) {
                max = jugadores.get(0).getFicha(i).getSuma();
                mano = 0;
            }
            if (jugadores.get(1).getFicha(i).getSuma() > max) {
                max = jugadores.get(1).getFicha(i).getSuma();
                mano = 1;
            }
        }

        if (mano == 1)
            cambioDeTurno();
    }

    /**
     * Metodo para cambiar el turno de los jugadores
     */
    public void cambioDeTurno() {
        Jugador aux = jugadores.get(1);
        jugadores.set(1, jugadores.get(0));
        jugadores.set(0, aux);
    }

    /**
     * Metodo para imprimir las fichas de la mesa
     */
    public void imprimir() {
        for (Ficha ficha : mesa)
            System.out.print(ficha + "\t");
        System.out.println();
    }

    /**
     * Metodo para imprimir las fichas de un ArrayList
     * @param fichas ArrayList de fichas
     */
    public void imprimir(ArrayList<Ficha> fichas) {
        for (int i = 1; i <= fichas.size(); i++)
            System.out.print("  " + i + "\t");
        System.out.println();

        for (Ficha ficha : fichas)
            System.out.print(ficha + "\t");
        System.out.println();
    }    
}
