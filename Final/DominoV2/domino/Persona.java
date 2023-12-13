package domino;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase representa un jugador humano de domino
 */

public class Persona extends Jugador {
    /**
     * Metodo constructor
     * @param nombre Nombre del jugador
     */
    public Persona(String nombre) {
        this.nombre = nombre;
    }

    @Override
    protected String buscarFicha(ArrayList<Ficha> mesaActual) {
        Scanner sc = new Scanner(System.in);
        int indice;
        char orientacion;

        do {
            System.out.println("Que ficha quieres jugar? (1 - " + fichas.size() + ")");
            System.out.print("$ ");
            indice = sc.nextInt() - 1;
            if (indice < 0 || indice >= fichas.size())
                System.out.println("Ficha inválida");
        } while (indice < 0 || indice >= fichas.size());

        do {
            System.out.println("Donde la quieres jugar? (i o d)");
            System.out.print("$ ");
            orientacion = sc.next().charAt(0);
        } while (orientacion != 'i' && orientacion != 'd');

        return orientacion + "" + indice;
    }
}
