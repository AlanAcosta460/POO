import java.util.Scanner;

class Genio {
    private static Genio instancia;
    public String nombre;

    private Genio(String nombre) {
        this.nombre = nombre;
    }

    public static Genio getInstancia(String nombre) {
        if (instancia == null)
            instancia = new Genio(nombre);

        return instancia;
    }

    public void pedirDeseo() {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Cual es tu deseo?\n> ");
        String deseo = sc.nextLine();

        System.out.println("Pensando...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Tu deseo se ha cumplido!!!\n");

        sc.close();
    }
}

public class Problema1 {
    public static void main(String[] args) {
        Genio genio = Genio.getInstancia("Genio");

        genio.pedirDeseo();
        genio.pedirDeseo();
        genio.pedirDeseo();
    }
}