import java.util.Random;

public class Problema1 {
    static final int N = 10;
    static StringBuilder[][] pista = new StringBuilder[N][N];
    static final int ESPERA = 2000;

    class Corredor extends Thread {
        int pos, i, j;
        final char SIMBOLO;

        Corredor(int pos, int i, int j, char SIMBOLO, String nombre) {
            this.pos = pos;
            this.i = i;
            this.j = j;
            this.SIMBOLO = SIMBOLO;
            setName(nombre);
        }

        @Override
        public void run() {
            synchronized (pista) {
                System.out.println("\nTurno de " + getName() + "\tPosicion: " + pos);
                pos = jugada(pos);
                if (pista[i][j].charAt(pista[i][j].length() - 1) == SIMBOLO)
                    pista[i][j].deleteCharAt(pista[i][j].length() - 1);
                else
                    pista[i][j].deleteCharAt(pista[i][j].length() - 2);

                if (pos == 0) {
                    i = 0;
                    j = 0;
                } else {
                    i = (pos - 1) / 10;
                    j = (pos - 1) % 10;
                }

                pista[i][j].append(SIMBOLO);
                imprimirPista();
                System.out.println("Fin del turno");
                
                try {
                    Thread.sleep(ESPERA);
                } catch (InterruptedException e) {
                    System.out.println(e);
                } finally {
                    Thread.yield();
                }
            }
        }
    }

    static void inicializarPista() {
        int numeroCasilla = 2;

        pista[0][0] = new StringBuilder("S");
        pista[9][9] = new StringBuilder("E");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if ((i == 0 && j == 0) || (i == 9 && j == 9)) continue;
                else pista[i][j] = new StringBuilder(Integer.toString(numeroCasilla++));
            }
        }

        pista[0][0].append("HT");
    }

    static void imprimirPista() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(pista[i][j] + "\t");
            System.out.println();
        }
    }

    static int jugada(int posActual) {
        Random rand = new Random();
        int dado, posNueva = posActual;

        dado = rand.nextInt(6) + 1;
        posNueva += dado;

        if (posNueva > 100) posNueva = 100;

        System.out.println("Dado: " + dado);
        System.out.println("Avanza de " + posActual + " a " + posNueva);

        switch (posNueva) {
            case 7, 14, 33, 77, 89 -> {
                System.out.println("Casilla Especial!\tTira de nuevo");
                posNueva = jugada(posNueva);
            } case 6, 23, 42, 56, 82, 90 -> {
                System.out.println("Trampa\tRetrocede de " + posNueva + " a " + (posNueva - 5));
                posNueva -= 5;
            } case 36 -> {
                System.out.println("Casilla Especial!\tAvanza de " + posNueva + " a 71");
                posNueva = 71;
            } case 65 -> {
                System.out.println("Casilla Especial!\ttAvanza de " + posNueva + " a 84");
                posNueva = 84;
            } case 10 -> {
                System.out.println("Trampa\tRegresa al inicio");
                posNueva = 1;
            } case 66 -> {
                System.out.println("Trampa\tRegresa de " + posNueva + " a 40");
                posNueva = 40;
            }
        }

        return posNueva;
    }

    public static void main(String[] args) throws InterruptedException {
        int turno = 0;
        Corredor tortuga = new Problema1().new Corredor(1, 0, 0, 'T', "Tortuga");
        Corredor liebre = new Problema1().new Corredor(1, 0, 0, 'H', "Liebre ");

        inicializarPista();

        System.out.println("Pista inicial");
        imprimirPista();
        
        tortuga.start();
        liebre.start();
        Thread.sleep(ESPERA);
        
        while (true) {
            turno++;
            tortuga.join();
            liebre.join();
            tortuga.run();
            Thread.yield();
            liebre.run();
            if (liebre.pos == 100 || tortuga.pos == 100) break;   
        }

        System.out.println("\nGanador: " + (turno % 2 != 0 ? "Tortuga!" : "Liebre!"));
        System.out.println("Fin del juego");
    }
}