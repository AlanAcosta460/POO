import java.util.Random;

public class EquipoAzul extends Thread {
    static volatile int progreso = 0;

    public EquipoAzul(ThreadGroup g, String nombre) {
        super(g, nombre);
    }

    @Override 
    public void run() {
        int nMiembros = Thread.activeCount();
        Thread[] miembros = new Thread[nMiembros];
        
        for (int i = 0; i < 4; i++) {
            correr(miembros[i], i);
            progreso += 100;
        }
    }

    public synchronized void correr(Thread a, int i) {
        a.yield();
        Random rand = new Random();
        System.out.println("Atleta Azul " + (i+1) + " empieza");

        try {
            Thread.sleep(rand.nextInt(10000) + 2001);
        } catch(InterruptedException e) {}

        System.out.println("Atleta Azul " + (i+1) + " pasa la estafeta");
    }
}
