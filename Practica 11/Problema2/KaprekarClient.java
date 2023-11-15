import java.io.*;
import java.net.Socket;

public class KaprekarClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream())) {
            int numero;
            do {
                System.out.print("Introduce un número (0 para salir): ");
                numero = Integer.parseInt(System.console().readLine());
                if (numero == 0) 
                    throw new ExitException("Gracias por usar el programa");
                if (numero < 0 || numero > 9999) {
                    System.out.println("El número debe estar entre 0 y 9999");
                    continue;
                }
            } while (numero < 0 || numero > 9999);
            output.writeInt(numero);
            output.flush();

            int pasos = input.readInt();
            System.out.println("Número de pasos para llegar a Kaprekar: " + pasos);
            System.out.println("Gracias por usar el programa");
        } catch (IOException | ExitException e) {
            System.out.println(e.getMessage());
        }
    }
}