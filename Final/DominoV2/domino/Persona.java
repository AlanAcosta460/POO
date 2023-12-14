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
    public String buscarFicha(ArrayList<Ficha> mesaActual) {
        Scanner sc = new Scanner(System.in);
        int indice = -1;
        char orientacion = ' ';

        do {
            try {
                System.out.println("\nQue ficha deseas jugar? (1 - " + fichas.size() + ")");
                System.out.print("$ ");
                indice = sc.nextInt() - 1;
                if (indice < 0 || indice >= fichas.size())
                    throw new IndiceFichaInvalidoException("Índice de ficha no válido. Debe ser entre 1 y " + fichas.size());
            } catch (IndiceFichaInvalidoException e) {
                System.out.println(e.getMessage() + "\n");
            }
        } while (indice < 0 || indice >= fichas.size());
        
        do {
            try {
                System.out.println("Donde la quieres jugar? (i o d)");
                System.out.print("$ ");
                orientacion = sc.next().charAt(0);
                if (orientacion != 'i' && orientacion != 'd') 
                    throw new OrientacionInvalidaException("Orientación no válida. Debe ser 'i' o 'd'.");
            } catch (OrientacionInvalidaException e) {
                System.out.println(e.getMessage() + "\n");
            }
        } while (orientacion != 'i' && orientacion != 'd');

        return orientacion + "" + indice;
    }
}
