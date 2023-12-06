import java.util.Random;

class Atleta implements Runnable {
    private Equipo equipo;
    private String nombre;

    public Atleta(Equipo equipo, String nombre) {
        this.equipo = equipo;
        this.nombre = nombre;
    }

    public void correr() {
        Random rand = new Random();
        int tiempo = rand.nextInt(6000) + 3000;

        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(nombre + " ha llegado a la meta");

        equipo.pasarEstafeta();
    }

    @Override
    public void run() {
        correr();
    }
}

class Equipo {
    private String nombre;
    private int contadorAtletas;
    private long tiempoTotal;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.contadorAtletas = 0;
        this.tiempoTotal = 0;
    }

    public synchronized void pasarEstafeta() {
        contadorAtletas++;

        if (contadorAtletas == 4) {
            System.out.println("Todos los atletas del equipo " + nombre + " han terminado la carrera!");
            Problema2.carreraTerminada = true;
        } else 
            System.out.println("\tEstafeta pasada al siguiente atleta del equipo " + nombre);
    }

    public synchronized void sumarTiempo(long tiempo) {
        tiempoTotal += tiempo;
    }

    public long getTiempoTotal() {
        return tiempoTotal;
    }
}

class Problema2 {
    static volatile boolean carreraTerminada = false;

    public static void main(String[] args) throws InterruptedException {
        Equipo equipoAzul = new Equipo("Azul");
        Equipo equipoRojo = new Equipo("Rojo");

        Thread rojo[] = new Thread[4];
        Thread azul[] = new Thread[4];

        for (int i = 0; i < 4; i++) {
            azul[i] = new Thread(new Atleta(equipoAzul, "Atleta " + (i + 1) + " Azul"));
            rojo[i] = new Thread(new Atleta(equipoRojo, "Atleta " + (i + 1) + " Rojo"));
        }

        System.out.println("Comienza la carrera!");

        for (int i = 0; i < 4; i++) {
            azul[i].start();
            rojo[i].start();

            rojo[i].join();
            azul[i].join();
        }
        
        if (equipoAzul.getTiempoTotal() < equipoRojo.getTiempoTotal()) {
            System.out.println("\nEl equipo Azul ha ganado!");
        } else {
            System.out.println("\nEl equipo Rojo ha ganado!");
        }
    }
}