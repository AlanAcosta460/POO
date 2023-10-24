import java.util.ArrayList;
import java.util.Scanner;

public class Humano extends Jugador {
    public Humano(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public void primerTurno(ArrayList<Ficha> mesaActual) {
        Scanner sc = new Scanner(System.in);
        int ficha = -1;
        int max = -1;

        for (Ficha f : fichas) {
            if (f.esMula() && f.getSuma() > max) {
                max = f.getSuma();
                ficha = fichas.indexOf(f);
            }
        }

        if (ficha != -1)
            System.out.println(nombre + " juegas la mula mas alta: " + fichas.get(ficha));
        else {
            for (int i = 0; i < fichas.size(); i++) {
                if (fichas.get(i).getSuma() > max) {
                    max = fichas.get(i).getSuma();
                    ficha = i;
                }
            }
            System.out.println(nombre + " juegas la ficha mas alta: " + fichas.get(ficha));
        }

        mesaActual.add(fichas.get(ficha));
        fichas.remove(ficha);
        
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void turno(ArrayList<Ficha> mesaActual) {
        Scanner sc = new Scanner(System.in);
        int ficha = 0;
        char lado = ' ';
        
        do {
            do {
                System.out.println("¿Qué ficha quieres jugar? (1 - " + fichas.size() + ")");
                ficha = sc.nextInt() - 1;
                if (ficha < 0 || ficha >= fichas.size())
                    System.out.println("Ficha inválida");
            } while (ficha < 0 || ficha >= fichas.size());

            System.out.println("¿Dónde la quieres jugar? (izquierda o derecha)");
            lado = sc.next().charAt(0);
            lado = Character.toLowerCase(lado);
            
            if (lado == 'i') {
                if (fichas.get(ficha).getCaraIzq() == mesaActual.get(0).getCaraIzq()) {
                    fichas.get(ficha).girar();
                    mesaActual.add(0, fichas.get(ficha));
                    fichas.remove(ficha);
                    return;
                } else if (fichas.get(ficha).getCaraDer() == mesaActual.get(0).getCaraIzq()) {
                    mesaActual.add(0, fichas.get(ficha));
                    fichas.remove(ficha);
                    return;
                }
            } else if (lado == 'd') {
                if (fichas.get(ficha).getCaraIzq() == mesaActual.get(mesaActual.size() - 1).getCaraDer()) {
                    mesaActual.add(fichas.get(ficha));
                    fichas.remove(ficha);
                    return;
                } else if (fichas.get(ficha).getCaraDer() == mesaActual.get(mesaActual.size() - 1).getCaraDer()) {
                    fichas.get(ficha).girar();
                    mesaActual.add(fichas.get(ficha));
                    fichas.remove(ficha);
                    return;
                }
            }

            System.out.println("No puedes jugar esa ficha. Intenta de nuevo.");
        } while (true);
    }
}
