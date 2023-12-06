import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

abstract class AbstractSimpleCraps {
    abstract void generarDados();
    abstract int adivinar(int punto);
}

class Dado {
    int valor;

    public Dado(int valor) {
        this.valor = valor;
    }
}

class Problema1 extends AbstractSimpleCraps {
    ArrayList<Dado> dados = new ArrayList<Dado>();
    int suma;
    int turno = 1;
    
    void generarDados() {
        Random rand = new Random();
        dados.clear();

        for (int i = 0; i < 2; i++) 
            dados.add(new Dado(rand.nextInt(6) + 1));

        suma = dados.get(0).valor + dados.get(1).valor;
    }

    int adivinar(int punto) {
        if (turno == 1) {
           switch (suma) {
            case 7: return 1;
            case 12: return -1;
            default:
                if (suma == punto) return 1;
                else return 0;
           }
        } else {
            if (suma == punto) return 1;
            else if (suma == 7) return -1;
            else return -0;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Problema1 juego = new Problema1();

        System.out.print("Ingrese un numero: ");
        int punto = sc.nextInt();

        while(true) {
            juego.generarDados();
            int r = juego.adivinar(punto);

            System.out.print(juego.suma + " ");

            if (r == 1) {
                System.out.println("Ganaste en el turno " + juego.turno);
                break;
            } else if (r == -1) {
                System.out.println("Perdiste en el turno " + juego.turno);
                break;
            } else {
                System.out.println("No se adivino la suma");
                juego.turno++;
            }
        }

        sc.close();
    }
}
