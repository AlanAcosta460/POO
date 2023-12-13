package domino;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import java.io.File;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

/**
 * Esta clase representa la configuracion de una partida de domino.
 * Contiene informacion sobre el pozo de fichas y los jugadores.
 */
public class Configuracion implements Serializable {
    private ArrayList<Ficha> pozo = new ArrayList<>();
    private ArrayList<Jugador> jugadores = new ArrayList<>();
    private static transient ArrayList<String> listaArchivos= new ArrayList<>();

    /**
     * Constructor de la clase Configuracion.
     * Carga la configuracion desde un archivo dado.
     * @param nombreArchivo Nombre del archivo de configuracion.
     */
    public Configuracion(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("configuraciones/" + nombreArchivo))) {
            Configuracion conf = (Configuracion) ois.readObject();
            this.pozo = conf.getPozo();
            this.jugadores = conf.getJugadores();
        } catch (Exception e) {
            System.out.println("Error al cargar la configuracion");
        }
    }
    
    /**
     * Constructor privado de la clase Configuracion.
     * Se utiliza para crear una nueva configuracion.
     * @param pozo Lista de fichas en el pozo.
     * @param jugadores Lista de jugadores.
     */
    private Configuracion(ArrayList<Ficha> pozo, ArrayList<Jugador> jugadores) {
        this.pozo = pozo;
        this.jugadores = jugadores;
    }

    /**
     * Obtiene la lista de fichas en el pozo.
     * @return Lista de fichas en el pozo.
     */
    public ArrayList<Ficha> getPozo() {
        return pozo;
    }

    /**
     * Obtiene la lista de jugadores.
     * @return Lista de jugadores.
     */
    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Obtiene la lista de nombres de archivos de configuracion disponibles.
     * @return Lista de nombres de archivos de configuracion.
     */
    public static ArrayList<String> getListaArchivos() {
        listaArchivos.clear();
        File carpeta = new File("configuraciones");

        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles();

            if (archivos != null) {
                listaArchivos.addAll(Arrays.stream(archivos)
                        .map(File::getName)
                        .collect(Collectors.toList()));
                return listaArchivos;
            } else
                System.out.println("La carpeta está vacía.");
        } else
            System.out.println("La carpeta no existe o no es un directorio.");

        return null;     
    }

    /**
     * Guarda una nueva configuracion en un archivo dado.
     * @param nombreArchivo Nombre del archivo de configuracion.
     * @param pozo Lista de fichas en el pozo.
     * @param jugadores Lista de jugadores.
     */
    public static void guardarConfiguracion(String nombreArchivo, ArrayList<Ficha> pozo, ArrayList<Jugador> jugadores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("configuraciones/" + nombreArchivo + ".txt"))) {
            oos.writeObject(new Configuracion(pozo, jugadores));
        } catch (Exception e) {
            System.out.println("Error al guardar la configuracion");
        }
    }
}
