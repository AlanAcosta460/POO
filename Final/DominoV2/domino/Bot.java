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

    /**
     * Metodo para el primer turno del bot
     * @param mesaActual Mesa actual del juego
     */
    @Override
    public void primerTurno(ArrayList<Ficha> mesaActual) {
        int ficha = -1;
        int max = -1;

        for (Ficha f : fichas) {
            if (f.esMula() && f.getSuma() > max) {
                max = f.getSuma();
                ficha = fichas.indexOf(f);
            }
        }

        if (ficha != -1) 
            System.out.println(nombre + " juega la mula mas alta: " + fichas.get(ficha));
        else { 
            for (int i = 0; i < fichas.size(); i++) {
                if (fichas.get(i).getSuma() > max) {
                    max = fichas.get(i).getSuma();
                    ficha = i;
                }
            }
            System.out.println(nombre + " juega la ficha mas alta: " + fichas.get(ficha));
        }

        mesaActual.add(fichas.get(ficha));
        fichas.remove(ficha);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para el turno del bot
     * @param mesaActual Mesa actual del juego
     */
    @Override
    public void turno(ArrayList<Ficha> mesaActual) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }    

        for (int i = 0; i < fichas.size(); i++) {
            if (fichas.get(i).getCaraIzq() == mesaActual.get(0).getCaraIzq()) {
                fichas.get(i).girar();
                mesaActual.add(0, fichas.get(i));
                System.out.println(nombre + " juega " + fichas.get(i) + " a la izquierda");
                fichas.remove(i); 
                break;
            } else if (fichas.get(i).getCaraDer() == mesaActual.get(0).getCaraIzq()) {
                mesaActual.add(0, fichas.get(i));
                System.out.println(nombre + " juega " + fichas.get(i) + " a la izquierda");
                fichas.remove(i);
                break;
            } else if (fichas.get(i).getCaraIzq() == mesaActual.get(mesaActual.size() - 1).getCaraDer()) {
                mesaActual.add(fichas.get(i));
                System.out.println(nombre + " juega " + fichas.get(i) + " a la derecha");
                fichas.remove(i);
                break;
            } else if (fichas.get(i).getCaraDer() == mesaActual.get(mesaActual.size() - 1).getCaraDer()) {
                fichas.get(i).girar();
                mesaActual.add(fichas.get(i));
                System.out.println(nombre + " juega " + fichas.get(i) + " a la derecha");
                fichas.remove(i);
                break;
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   
    }
}
