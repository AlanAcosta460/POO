package domino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import java.io.File;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class Configuracion implements Serializable {
    private ArrayList<Ficha> pozo = new ArrayList<>();
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private static transient ArrayList<String> listaArchivos= new ArrayList<>();

    public Configuracion(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("/historial/configuraciones/" + nombreArchivo + ".txt"))) {
            Configuracion conf = (Configuracion) ois.readObject();
            this.pozo = conf.getPozo();
            this.jugadores = conf.getJugadores();
        } catch (Exception e) {}
    }
    
    private Configuracion(ArrayList<Ficha> pozo, ArrayList<Jugador> jugadores) {
        this.pozo = pozo;
        this.jugadores = jugadores;
    }

    public ArrayList<Ficha> getPozo() {
        return pozo;
    }

    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public static ArrayList<String> getListaArchivos() {
        File carpeta = new File("../configuraciones");

        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();

            if (archivos != null) {
                List<String> nombresArchivos = new ArrayList<>(
                    Arrays.stream(carpeta.listFiles())
                            .map(File::getName)
                            .collect(Collectors.toList())
                );
                listaArchivos.addAll(nombresArchivos);
                return listaArchivos;
            } else
                System.out.println("La carpeta está vacía.");
        } else
            System.out.println("La carpeta no existe o no es un directorio.");

        return null;        
    }

    public static void guardarConfiguracion(String nombreArchivo, ArrayList<Ficha> pozo, ArrayList<Jugador> jugadores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("../configuraciones/" + nombreArchivo + ".txt"))) {
            oos.writeObject(new Configuracion(pozo, jugadores));
        } catch (Exception e) {
            System.out.println("Error al guardar la configuracion");
        }
    }
}