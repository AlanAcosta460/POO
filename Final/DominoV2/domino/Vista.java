package domino;

import java.util.ArrayList;

public class Vista {
    public static void mostrarMenu() {
        System.out.println("%%%%%%%%%%%%%%% Bienvenido a Domino %%%%%%%%%%%%%%%");
        System.out.println("Escoga el modo de juego:");
        System.out.println("1. Jugador vs Bot");
        System.out.println("2. Bot vs Bot");
        System.out.print("$ ");
    }
    
    public static void mostrarPozo(int pozo) {
        System.out.println("\nNumero de fichas en el Pozo: " + pozo);
    }

    public static void mostrarTurno(String nombre) {
        System.out.println("\n%%%%% Turno de " + nombre + " %%%%%");
    }

    public static void mostrarMesa(ArrayList<Ficha> mesa) {
        System.out.println("Mesa actual:");
        for (Ficha ficha : mesa)
            System.out.print(ficha + "\t");
        System.out.println();
    }

    public static void mostrarFichas(ArrayList<Ficha> fichas) {
        System.out.println("\nTus fichas:");
        for (int i = 1; i <= fichas.size(); i++)
            System.out.print("  " + i + "\t");
        System.out.println();
        for (Ficha ficha : fichas)
            System.out.print(ficha + "\t");
        System.out.println();
    }

    public static void mostrarGanador(String nombre) {
        System.out.println("\n\n%%%%%%%%%%%%%%% " + nombre + " ha ganado! %%%%%%%%%%%%%%%");
    }

    public static void mostrarEmpate() {
        System.out.println("\n\n%%%%%%%%%%%%%%% Empate! %%%%%%%%%%%%%%%");
    }
}
