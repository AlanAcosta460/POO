package phx;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Clase que gestiona la escritura de puntajes en un archivo.
 */
public class ScoresFile {
    
    /**
     * Escribe los puntajes en un archivo.
     *
     * @param nickName      Nombre de usuario del jugador.
     * @param maxTileValue  Valor máximo alcanzado en una casilla.
     * @param numberOfMoves Número total de movimientos realizados.
     */
    public static void write(String nickName, int maxTileValue, int numberOfMoves) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("phx/archivo.txt", true))) {
            writer.println(nickName + ":" + maxTileValue + ":" + numberOfMoves);
        } catch (IOException e) {
            System.out.println("Error al guardar los puntajes");
        }
    }
}
