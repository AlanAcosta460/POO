import java.util.ArrayList;

public class Carrera {
    static volatile int progresoR;
    static volatile int progresoA;

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup equipoRojo = new ThreadGroup("Equipo Rojo");
        Thread r1 = new EquipoRojo(equipoRojo, "");
        Thread r2 = new EquipoRojo(equipoRojo, "");
        Thread r3 = new EquipoRojo(equipoRojo, "");
        Thread r4 = new EquipoRojo(equipoRojo, "");

        ThreadGroup equipoAzul = new ThreadGroup("Equipo Azul");
        Thread a1 = new EquipoAzul(equipoAzul, "");
        Thread a2 = new EquipoAzul(equipoAzul, "");
        Thread a3 = new EquipoAzul(equipoAzul, "");
        Thread a4 = new EquipoAzul(equipoAzul, "");

        System.out.println("Comienza la carrera");

        r1.start();
        r2.start();
        r3.start();
        r4.start();
        r1.join();
        r2.join();
        r3.join();
        r4.join();

        a1.start();
        a2.start();
        a3.start();
        a4.start();
        a1.join();
        a2.join();
        a3.join();
        a4.join();
    }
}
