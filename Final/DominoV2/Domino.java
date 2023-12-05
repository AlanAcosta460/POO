package domino;

import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Esta clase representa el juego de domino
 */

public class Domino {
    /**
     * Metodo main.
     * Contiene la logica del juego.
     * @param args Argumentos de la linea de comandos
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Clip musica = null;

        try {
            musica = AudioSystem.getClip();
            musica.open(AudioSystem.getAudioInputStream(Domino.class.getResource("/recursos/MoonlightSonata.wav")));
            musica.loop(Clip.LOOP_CONTINUOUSLY); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("%%%%%%%%%%%%%%% Bienvenido a Domino %%%%%%%%%%%%%%%");
        System.out.println("Escoga el modo de juego:");
        System.out.println("1. Jugador vs Bot");
        System.out.println("2. Bot vs Bot");
        System.out.print("$ ");
        int tipoJuego = sc.nextInt();
        Mesa mesa = new Mesa(tipoJuego);

        while ((mesa.getJugadores().get(0).puedeJugar() || 
                mesa.getJugadores().get(1).puedeJugar()) || 
                !mesa.getPozo().isEmpty()) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("\n\nMesa actual:");
            mesa.imprimir();
            System.out.println("\nNumero de fichas en el Pozo: " + mesa.getPozo().size());
            
            System.out.println("\n%%%%% Turno de " + mesa.getJugadores().get(0).getNombre() + " %%%%%");
            mesa.imprimir(mesa.getJugadores().get(0).getFichas());

            if (mesa.getMesa().isEmpty())
                mesa.getJugadores().get(0).primerTurno(mesa.getMesa());
            else {
                if (mesa.getJugadores().get(0).puedeJugar(mesa.getMesa())) 
                    mesa.getJugadores().get(0).turno(mesa.getMesa());
                else { 
                    if (mesa.getPozo().isEmpty()) { 
                        System.out.println("No puedes jugar y no hay fichas en el pozo, pasas tu turno");

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (mesa.getJugadores().get(0) instanceof Humano)
                            System.out.println("Necesitas robar una ficha");

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(mesa.getJugadores().get(0).getNombre() + " roba 1 ficha y pasa su turno");
                        mesa.getJugadores().get(0).robar(mesa.getPozo());
                        mesa.imprimir(mesa.getJugadores().get(0).getFichas());

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            
            if (mesa.getJugadores().get(0).getCantidadFichas() == 0) {
                System.out.println("\n\nMesa final:");
                mesa.imprimir(mesa.getMesa());
                break;
            }

            mesa.cambioDeTurno();
        }
        
        try {
            if (musica != null)
                musica.stop();
            
            musica = AudioSystem.getClip();
            musica.open(AudioSystem.getAudioInputStream(Domino.class.getResource("/recursos/Victoria.wav")));
            musica.start();
        } catch (Exception e) {
            e.printStackTrace();
        }        

        if (!mesa.getJugadores().get(0).puedeJugar() && 
                !mesa.getJugadores().get(1).puedeJugar() &&
                mesa.getPozo().isEmpty() ) {
            int suma1 = 0, suma2 = 0;
            System.out.println("Se acabaron las fichas");

            for (int i = 0; i < mesa.getJugadores().get(0).getCantidadFichas(); i++)
                suma1 += mesa.getJugadores().get(0).getFicha(i).getSuma();
            for (int i = 0; i < mesa.getJugadores().get(1).getCantidadFichas(); i++)
                suma2 += mesa.getJugadores().get(1).getFicha(i).getSuma();
            
            if (suma1 < suma2)
                System.out.println("El ganador es " + mesa.getJugadores().get(0).getNombre());
            else if (suma1 > suma2)
                System.out.println("El ganador es " + mesa.getJugadores().get(1).getNombre());
            else
                System.out.println("Empate");
        } else 
            System.out.println("\n%%%%%%%%%% El ganador es " + mesa.getJugadores().get(0).getNombre() + " %%%%%%%%%%");
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 

        sc.close();
    }
}
