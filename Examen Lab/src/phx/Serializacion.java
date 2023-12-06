package phx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Clase que realiza la serialización y deserialización de objetos Jugador.
 */
public class Serializacion {
    public static void main(String[] args) {
        ArrayList<Jugador> registros = new ArrayList<>();
        
        try (Scanner sc = new Scanner(new File("phx/archivo.txt"))) {
            while (sc.hasNextLine()) {
                StringTokenizer st = new StringTokenizer(sc.nextLine(), ":");
                registros.add(new Jugador(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 

        try (ObjectOutputStream serializador = new ObjectOutputStream(new FileOutputStream("phx/archivo.ser"))) {
            System.out.println("Serializando...");
            serializador.writeObject(registros);
            serializador.close();
            Thread.sleep(3000);
            System.out.println("Archivo seralizado");
        } catch (Exception e) {
            e.printStackTrace();
        }

        registros.clear();

        try (ObjectInputStream deserializador = new ObjectInputStream(new FileInputStream("phx/archivo.ser"))) {
            System.out.println("\nDeserializando...");
            registros = (ArrayList<Jugador>) deserializador.readObject();
            deserializador.close();
            Thread.sleep(3000);
            System.out.println("Archivo deserializado\n");
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Jugador registro : registros) 
            System.err.println(registro.nickName + " : " + registro.maxTileValue + " : " + registro.numberOfMoves);
    }
}
