package domino;

import java.util.ArrayList;
import java.util.Random;

public class Modelo {
    private ArrayList<Ficha> mesa = new ArrayList<Ficha>();
    private ArrayList<Ficha> pozo = new ArrayList<Ficha>();
    private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
    private int turno;

    public Modelo(String nombre) {
        jugadores.add(new Persona(nombre));
        jugadores.add(new Bot("Bot"));

        this.repartirFichas();
        this.decidirPrimerTurno();
        this.turno = 1;
    }

    public Modelo() {
        jugadores.add(new Bot("Bot 1"));
        jugadores.add(new Bot("Bot 2"));

        this.repartirFichas();
        this.decidirPrimerTurno();
        this.turno = 1;
    }

    public Modelo(Configuracion conf) {
        this.pozo = conf.getPozo();
        this.jugadores = conf.getJugadores();
        this.decidirPrimerTurno();
        this.turno = 1;
    }

    public ArrayList<Ficha> getMesa() {
        return mesa;
    }

    public int getTamanioMesa() {
        return mesa.size();
    }

    public ArrayList<Ficha> getPozo() {
        return pozo;
    }

    public int getTamanioPozo() {
        return pozo.size();
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador getJugador(int i) {
        return jugadores.get(i);
    }

    public int getTurno() {
        return turno;
    }

    private void repartirFichas() {
        Random random = new Random();

        for (int i = 0; i <= 6; i++) 
            for (int j = i; j <= 6; j++) 
                pozo.add(new Ficha(i, j));

        for (int i = 0; i < pozo.size(); i++) {
            int j = random.nextInt(pozo.size());
            Ficha aux = pozo.get(i);
            pozo.set(i, pozo.get(j));
            pozo.set(j, aux);
        }

        for (int i = 0; i < 7; i++) {
            for (Jugador jugador : jugadores) {
                jugador.agregarFicha(pozo.get(0));
                pozo.remove(0);
            }
        }
    }

    private void decidirPrimerTurno() {
        int max = -1, turno = -1;

        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 7; i++) {
                if (jugador.getFicha(i).esMula() && jugador.getFicha(i).getSuma() > max) {
                    max = jugador.getFicha(i).getSuma();
                    turno = jugadores.indexOf(jugador);
                }
            }
        }

        if (turno != -1) {
            if (turno == 1) 
                cambiarTurno();
            return;
        }

        max = -1;
        for (Jugador jugador : jugadores) {
            for (int i = 0; i < 7; i++) {
                if (jugador.getFicha(i).getSuma() > max) {
                    max = jugador.getFicha(i).getSuma();
                    turno = jugadores.indexOf(jugador);
                }
            }
        }

        if (turno == 1) 
            cambiarTurno();
    }

    public void cambiarTurno() {
        Jugador aux = jugadores.get(0);
        jugadores.set(0, jugadores.get(1));
        jugadores.set(1, aux);
        turno++;
    }
}
