package domino;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Esta clase representa un jugador humano de domino.
 */
public class Persona extends Jugador {
    /**
     * Constructor de la clase Persona.
     * @param nombre Nombre del jugador.
     */
    public Persona(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo sobreescrito para permitir al jugador humano seleccionar una ficha
     * para jugar y la orientacion en la que desea colocarla.
     * @param mesaActual Lista de fichas en la mesa actual.
     * @return Cadena que representa la accion del jugador (orientacion + indice).
     */
    @Override
    protected String buscarFicha(ArrayList<Ficha> mesaActual) {
        Scanner sc = new Scanner(System.in);
        int indice;
        char orientacion;

        do {
            System.out.println("¿Que ficha deseas jugar? (1 - " + fichas.size() + ")");
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
