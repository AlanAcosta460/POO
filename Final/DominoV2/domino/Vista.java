package domino;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase que maneja la interfaz y la presentacion del juego de domino.
 */
public class Vista {
    /**
     * Metodo para limpiar la pantalla.
     */
    public static void limpiarPantalla() {
        // Windows
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {}

        // Linux
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {}
    }

    /**
     * Metodo para mostrar un mensaje y esperar la confirmacion del usuario.
     */
    public static void continuar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\n\nPresione enter para continuar...");
        sc.nextLine();
    }

    /**
     * Metodo para pedir el nombre al usuario.
     */
    public static void pedirNombre() {
        System.out.println("%%%%%%%%%%%%%%% Ingrese su nombre %%%%%%%%%%%%%%%");
        System.out.print("$ ");
    }

    /**
     * Metodo para mostrar el menu del juego.
     */
    public static void mostrarMenu() {
        System.out.println("%%%%%%%%%%%%%%% Bienvenido a Domino %%%%%%%%%%%%%%%");
        System.out.println("Escoja el modo de juego:");
        System.out.println("1. Jugador vs Bot");
        System.out.println("2. Bot vs Bot");
        System.out.println("3. Historial de partidas");
        System.out.println("4. Cargar partida anterior");
        System.out.println("5. Salir");
        System.out.print("$ ");
    }

    /**
     * Metodo para mostrar la cantidad de fichas en el pozo.
     * @param pozo Numero de fichas en el pozo.
     */
    public static void mostrarPozo(int pozo) {
        System.out.println("\nNumero de fichas en el Pozo: " + pozo);
    }

    /**
     * Metodo para mostrar el turno del jugador.
     * @param nombre Nombre del jugador actual.
     */
    public static void mostrarTurno(String nombre) {
        System.out.println("\n%%%%% Turno de " + nombre + " %%%%%");
    }

    /**
     * Metodo para mostrar las fichas en la mesa.
     * @param mesa Lista de fichas en la mesa.
     */
    public static void mostrarMesa(ArrayList<Ficha> mesa) {
        System.out.println("Mesa actual:");
        for (Ficha ficha : mesa)
            System.out.print(ficha + "\t");
        System.out.println();
    }

    /**
     * Metodo para mostrar las fichas del jugador.
     * @param fichas Lista de fichas del jugador.
     */
    public static void mostrarFichas(ArrayList<Ficha> fichas) {
        System.out.println("\nTus fichas:");
        for (int i = 1; i <= fichas.size(); i++)
            System.out.print("  " + i + "\t");
        System.out.println();
        for (Ficha ficha : fichas)
            System.out.print(ficha + "\t");
        System.out.println();
    }

    /**
     * Metodo para mostrar al ganador del juego.
     * @param nombre Nombre del jugador ganador.
     */
    public static void mostrarGanador(String nombre) {
        System.out.println("\n\n%%%%%%%%%%%%%%% " + nombre + " ha ganado! %%%%%%%%%%%%%%%");
    }

    /**
     * Metodo para mostrar un mensaje de empate.
     */
    public static void mostrarEmpate() {
        System.out.println("\n\n%%%%%%%%%%%%%%% Empate! %%%%%%%%%%%%%%%");
    }

    /**
     * Metodo para mostrar el historial de partidas.
     * @param historial Lista de partidas en el historial.
     */
    public static void mostrarHistorial(ArrayList<Partida> historial) {
        System.out.println("%%%%%%%%%%%%%%% Historial de partidas %%%%%%%%%%%%%%%");
        System.out.println("Ganador\t\tTurno\t\tFecha");
        for (Partida partida : historial)
            System.out.println(partida.getNombre() + "\t\t" + partida.getTurno() + "\t\t" + partida.getFecha());
    }

    /**
     * Metodo para mostrar los archivos de configuracion disponibles.
     * @param archivos Lista de nombres de archivos de configuracion.
     */
    public static void mostrarArchivos(ArrayList<String> archivos) {
        System.out.println("%%%%%%%%%%%%%%% Archivos de configuracion %%%%%%%%%%%%%%%");
        for (int i = 0; i < archivos.size(); i++)
            System.out.println((i + 1) + ". " + archivos.get(i));
        System.out.println("Cualquier otro numero para volver al menu principal");
    }
}
