package domino;

import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Controlador {
    private static Scanner sc = new Scanner(System.in);
    private static Clip musica = null;
    private static Modelo modelo;
    private static final int ESPERA = 3000;

    public static void main(String[] args) {
        iniciarMusica();

        Vista.mostrarMenu();
        int tipoJuego = sc.nextInt();
        modelo = new Modelo(tipoJuego);

        while (continuarJuego()) {
            limpiarPantalla();

            Vista.mostrarMesa(modelo.getMesa());
            Vista.mostrarPozo(modelo.getTamanioPozo());

            Vista.mostrarTurno(modelo.getJugador(0).getNombre());
            Vista.mostrarFichas(modelo.getJugador(0).getFichas());

            if (modelo.getTurno() == 1) 
                modelo.getJugador(0).primerTurno(modelo.getMesa());
            else {
                if (modelo.getJugador(0).puedeJugar(modelo.getMesa())) 
                    modelo.getJugador(0).turno(modelo.getMesa());
                else { 
                    if (modelo.getTamanioPozo() == 0) { 
                        System.out.println("No puedes jugar, pasas el turno");
                        esperar();
                    } else {
                        esperar();
                        System.out.println("No puedes jugar, robas una ficha");
                        esperar();
                        modelo.getJugador(0).robar(modelo.getPozo());
                        Vista.mostrarFichas(modelo.getJugador(0).getFichas());
                        esperar();
                    }
                }
            }

            if (modelo.getJugador(0).getFichas().isEmpty())
                break;
        
            modelo.cambiarTurno();
        }

        musicaFinal();
        String resultado = decidirGanador();
        if (resultado.equals("Empate"))
            Vista.mostrarEmpate();
        else
            Vista.mostrarGanador(resultado);
        esperar();
    }

    static private void esperar() {
        try {
            Thread.sleep(ESPERA);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static private void iniciarMusica() {
        try {
            musica = AudioSystem.getClip();
            musica.open(AudioSystem.getAudioInputStream(Controlador.class.getResource("/recursos/MoonlightSonata.wav")));
            musica.loop(Clip.LOOP_CONTINUOUSLY); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static private void musicaFinal() {
        try {
            if (musica != null)
                musica.stop();
            
            musica = AudioSystem.getClip();
            musica.open(AudioSystem.getAudioInputStream(Controlador.class.getResource("/recursos/Victoria.wav")));
            musica.start();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }

    static private boolean continuarJuego() {
        return (modelo.getJugador(0).puedeJugar() || 
                modelo.getJugador(1).puedeJugar()) || 
                modelo.getTamanioPozo() != 0;
    }

    static private void limpiarPantalla() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {}

        // Linux
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (Exception e) {}
    }

    static private String decidirGanador() {
        if (!modelo.getJugador(0).puedeJugar() && 
                !modelo.getJugador(1).puedeJugar() &&
                modelo.getTamanioPozo() == 0) {
            int fichasJugador1 = modelo.getJugador(0).getFichas().size();
            int fichasJugador2 = modelo.getJugador(1).getFichas().size();

            if (fichasJugador1 < fichasJugador2)
                return modelo.getJugador(0).getNombre();
            else if (fichasJugador1 > fichasJugador2)
                return modelo.getJugador(1).getNombre();
            else
                return "Empate";
        } else 
            return modelo.getJugador(0).getNombre();
    }
}
