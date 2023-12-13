package domino;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Esta clase actua como el controlador principal del juego de domino.
 */
public class Controlador {
    private static Scanner sc = new Scanner(System.in);
    private static Modelo modelo;
    private static Musica musica = new Musica();
    private static final int ESPERA = 3000;
    private static int opcion = 0;

    /**
     * Metodo principal que inicia y controla el juego de domino.
     * Permite a los jugadores iniciar una nueva partida, cargar una partida guardada o ver el historial.
     * Ademas, gestiona el desarrollo del juego, la logica del turno y decide al ganador.
     */
    public static void main(String[] args) {
        while (true) {
            Vista.limpiarPantalla();

            Vista.mostrarMenu();
            opcion = sc.nextInt();

            Vista.limpiarPantalla();
            switch (opcion) {
                case 1:
                    Vista.pedirNombre();
                    String nombre = sc.next();
                    modelo = new Modelo(nombre);
                    guardarConfiguracion();
                    break;
                case 2:
                    modelo = new Modelo();
                    guardarConfiguracion();
                    break;
                case 3:
                    Vista.mostrarHistorial(Historial.getHistorial());
                    Vista.continuar();
                    continue;
                case 4:
                    ArrayList<String> configuraciones = Configuracion.getListaArchivos();
                    if (configuraciones == null) {
                        Vista.continuar();
                        continue;
                    } else {
                        Vista.mostrarArchivos(configuraciones);
                        System.out.println("\nIngrese el numero de la partida que desea cargar");
                        System.out.print("$ ");
                        int respuesta = sc.nextInt();
                        if (respuesta > 0 && respuesta <= configuraciones.size()) {
                            Configuracion conf = new Configuracion(
                                configuraciones.get(respuesta - 1));
                            modelo = new Modelo(conf);
                        } else
                            continue;
                    }
                    break;
                default:
                    System.exit(0);
            }

            musica.reiniciarMusica();
            while (continuarJuego()) {
                Vista.limpiarPantalla();

                Vista.mostrarMesa(modelo.getMesa());
                Vista.mostrarPozo(modelo.getTamanioPozo());

                Vista.mostrarTurno(modelo.getJugador(0).getNombre());
                Vista.mostrarFichas(modelo.getJugador(0).getFichas());

                if (modelo.getTurno() == 1)
                    modelo.getJugador(0).primerTurno(modelo.getMesa());
                else {
                    if (modelo.getJugador(0).puedeJugar(modelo.getMesa())) {
                        if (modelo.getJugador(0) instanceof Bot)
                            esperar(ESPERA);
                        modelo.getJugador(0).turno(modelo.getMesa());
                    } else {
                        if (modelo.getTamanioPozo() == 0)
                            System.out.println("No puedes jugar, pasas el turno");
                        else {
                            esperar(ESPERA);
                            System.out.println("No puedes jugar, robas una ficha");
                            esperar(ESPERA);
                            modelo.getJugador(0).robar(modelo.getPozo());
                            Vista.mostrarFichas(modelo.getJugador(0).getFichas());
                        }
                    }
                }

                esperar(ESPERA);

                if (modelo.getJugador(0).getFichas().isEmpty())
                    break;

                modelo.cambiarTurno();
            }

            Vista.limpiarPantalla();
            Vista.mostrarMesa(modelo.getMesa());

            musica.musicaFinal();
            String resultado = decidirGanador();
            if (resultado.equals("Empate"))
                Vista.mostrarEmpate();
            else
                Vista.mostrarGanador(resultado);

            Historial.guardarResultado(resultado, modelo.getTurno());
            Vista.continuar();
        }
    }

    /**
     * Hace que el programa espere un tiempo determinado.
     * @param tiempo Tiempo en milisegundos.
     */
    private static void esperar(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pregunta al jugador si desea guardar la configuracion de la partida.
     * Si la respuesta es afirmativa, solicita un nombre y guarda la configuracion.
     */
    private static void guardarConfiguracion() {
        System.out.println("\n¿Quieres guardar la configuracion de la partida? (s/n)");
        System.out.print("$ ");
        char respuesta = sc.next().charAt(0);
        respuesta = Character.toLowerCase(respuesta);
        if (respuesta == 's') {
            System.out.println("\nIngrese el nombre de la partida");
            System.out.print("$ ");
            String nombre = sc.next();
            Configuracion.guardarConfiguracion(nombre, modelo.getPozo(), modelo.getJugadores());
        }
    }

    /**
     * Verifica si el juego debe continuar, basandose en si los jugadores pueden jugar o si hay fichas en el pozo.
     * @return `true` si el juego debe continuar, `false` si no.
     */
    private static boolean continuarJuego() {
        return (modelo.getJugador(0).puedeJugar() || 
                modelo.getJugador(1).puedeJugar()) || 
                modelo.getTamanioPozo() != 0;
    }

    /**
     * Decide al ganador basandose en las condiciones del juego.
     * @return Nombre del ganador o "Empate".
     */
    private static String decidirGanador() {
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
