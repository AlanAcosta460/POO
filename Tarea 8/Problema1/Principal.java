import java.io.*;
import javax.swing.JOptionPane;

class Persona implements Serializable {
  String nombre;
  String apellidoPaterno;
  String apellidoMaterno;
  int edad;

  public Persona(String nombre, String apellidoPaterno, String apellidoMaterno, int edad) {
    this.nombre = nombre;
    this.apellidoPaterno = apellidoPaterno;
    this.apellidoMaterno = apellidoMaterno;
    this.edad = edad;
  }
}

public class Principal {
  public static void main(String[] args) {
    File archivo = new File("datos.txt");

    String nombre = JOptionPane.showInputDialog(null, "Escribe tu nombre");
    String apellidoPaterno = JOptionPane.showInputDialog(null, "Escribe tu apellido paterno");
    String apellidoMaterno = JOptionPane.showInputDialog(null, "Escribe tu apellido materno");
    int edad = Integer.parseInt(JOptionPane.showInputDialog(null, "Escribe tu edad"));

    Persona persona = new Persona(nombre, apellidoPaterno, apellidoMaterno, edad);

    try {
      if (!archivo.exists()) {
        archivo.createNewFile();
      }

      ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo));
      oos.writeObject(persona);
      oos.close();

      ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo));
      Persona personaLeida = (Persona) ois.readObject();
      ois.close();

      System.out.println("Nombre: " + personaLeida.nombre);
      System.out.println("Apellido Paterno: " + personaLeida.apellidoPaterno);
      System.out.println("Apellido Materno: " + personaLeida.apellidoMaterno);
      System.out.println("Edad: " + personaLeida.edad);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}