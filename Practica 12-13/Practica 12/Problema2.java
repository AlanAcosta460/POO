class Clase {
    public synchronized void entrarClase(String persona) {
        System.out.println(persona + " ingresó a la clase");
    }

    public synchronized void decirBuenosDias() throws InterruptedException {
        System.out.println("Profesor: Buenos días");
        notifyAll();
    }
}

class Alumno extends Thread {
    private Clase clase;
    private String nombre;

    public Alumno(Clase clase, String nombre) {
        this.clase = clase;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        clase.entrarClase(nombre);
        try {
            synchronized (clase) {
                clase.wait();
                System.out.println(nombre + ": Buenos días");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Profesor extends Thread {
    private Clase clase;

    public Profesor(Clase clase) {
        this.clase = clase;
    }

    @Override
    public void run() {
        clase.entrarClase("Profesor");
        try {
            Thread.sleep(1000);
            clase.decirBuenosDias();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Problema2 {
    public static void main(String[] args) {
        Clase clase = new Clase();

        Profesor profesor = new Profesor(clase);
        profesor.start();

        Alumno[] alumnos = new Alumno[5];
        for (int i = 0; i < alumnos.length; i++) {
            alumnos[i] = new Alumno(clase, "Alumno " + (i + 1));
            alumnos[i].start();
        }

        try {
            profesor.join();
            for (Alumno alumno : alumnos) {
                alumno.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
