package domino;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Date;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;

/**
 * Gestiona el historial de partidas guardando y recuperando informacion sobre las mismas.
 */
public class Historial {
    /**
     * Obtiene el historial de partidas guardadas.
     * @return Lista de objetos `Partida` que representan el historial.
     */
    public static ArrayList<Partida> getHistorial() {
        ArrayList<Partida> historial = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("historial/historial.txt"))) {
            br.lines().forEach(linea -> {
                StringTokenizer st = new StringTokenizer(linea, "|");
                String nombre = st.nextToken();
                int turno = Integer.parseInt(st.nextToken());
                String fecha = st.nextToken();
                historial.add(new Partida(nombre, turno, fecha));
            });
        } catch (Exception e) {
            System.out.println("Error al leer el historial");
        }
        return historial;
    }

    /**
     * Guarda el resultado de una partida en el historial.
     * @param nombre Nombre del ganador de la partida.
     * @param turno Numero de turnos que duro la partida.
     */
    public static void guardarResultado(String nombre, int turno) {
        try (PrintWriter ps = new PrintWriter(new FileWriter("historial/historial.txt", true))) {
            Date fecha = new Date();
            ps.println(nombre + "|" + turno + "|" + fecha.toString());
        } catch (Exception e) {
            System.out.println("Error al guardar el resultado");
        }
    }
}
