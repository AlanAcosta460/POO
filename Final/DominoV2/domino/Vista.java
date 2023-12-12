package domino;

import java.util.ArrayList;
import java.util.Scanner;

public class Vista {
    public static void limpiarPantalla() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {}

        // Linux
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {}
    }

    public static void pedirNombre() {
        System.out.println("%%%%%%%%%%%%%%% Ingrese su nombre %%%%%%%%%%%%%%%");
        System.out.print("$ ");
    }

    public static void mostrarMenu() {
        System.out.println("%%%%%%%%%%%%%%% Bienvenido a Domino %%%%%%%%%%%%%%%");
        System.out.println("Escoga el modo de juego:");
        System.out.println("1. Jugador vs Bot");
        System.out.println("2. Bot vs Bot");
        System.out.println("3. Historial de partidas");
        System.out.println("4. Cargar partida anterior"); 
        System.out.println("5. Salir");
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

    public static void mostrarHistorial(ArrayList<Partida> historial) {
        System.out.println("%%%%%%%%%%%%%%% Historial de partidas %%%%%%%%%%%%%%%");
        System.out.println("Nombre\t\tTurno\t\tFecha");
        for (Partida partida : historial)
            System.out.println(partida.getNombre() + "\t\t" + partida.getTurno() + "\t\t" + partida.getFecha());
    }

    public static void mostrarArchivos(ArrayList<String> archivos) {

        System.out.println("%%%%%%%%%%%%%%% Archivos de configuracion %%%%%%%%%%%%%%%");
        for (int i = 0; i < archivos.size(); i++)
            System.out.println((i + 1) + ". " + archivos.get(i));
    }
}