package domino;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.Date;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;

public class Historial {
    public static ArrayList<Partida> getHistorial() {
        ArrayList<Partida> historial = new ArrayList<>();
        try (Scanner sc = new Scanner(new File("/historial/historial.txt"))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                line = sc.nextLine();
                StringTokenizer tokenizer = new StringTokenizer(line, " | ");
                line = sc.nextLine();
                historial.add(new Partida(tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken())));
            }
        } catch (Exception e) {
            System.out.println("Error al leer el historial");
        }
        return historial;
    } 

    public static void guardarResultado(String nombre, int turno) {
        try (PrintWriter ps = new PrintWriter(new FileWriter("/historial/historial.txt", true))) {
            Date fecha = new Date();
            ps.println(nombre + " | " + turno + " | " + fecha.toString());
        } catch (Exception e) {
            System.out.println("Error al guardar el resultado");
        }
    }
}

class Partida {
    private String nombre;
    private int turno;
    private String fecha;
    
    public Partida(String nombre, int turno) {
        this.nombre = nombre;
        this.turno = turno;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTurno() {
        return turno;
    }

    public String getFecha() {
        return fecha;
    }
}