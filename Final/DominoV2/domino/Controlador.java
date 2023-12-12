package domino;

import java.util.Scanner;
import java.util.ArrayList;

public class Controlador {
    private static Scanner sc = new Scanner(System.in);
    private static Modelo modelo;
    private static Musica musica = new Musica();
    private static final int ESPERA = 000;
    private static int opcion = 0;

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
                    break;
                case 2: 
                    modelo = new Modelo();
                    break;
                case 3: 
                    Vista.mostrarHistorial(Historial.getHistorial());
                    System.out.println("\n\nPresione enter para continuar...");
                    sc.nextLine();
                    sc.nextLine();
                    continue;
                case 4:
                    ArrayList<String> configuraciones = Configuracion.getListaArchivos();
                    if (configuraciones == null) {
                        System.out.println("\n\nPresione enter para continuar...");
                        sc.nextLine();      
                        sc.nextLine();
                        continue;
                    } else {
                        Vista.mostrarArchivos(configuraciones);
                        while (true) {
                            System.out.println("\nIngrese el numero de la partida que desea cargar");
                            System.out.print("$ ");
                            int respuesta = sc.nextInt();
                            if (respuesta > 0 && respuesta <= configuraciones.size()) {
                                Configuracion conf = new Configuracion(
                                    configuraciones.get(respuesta - 1));
                                modelo = new Modelo(conf);
                                break;
                            } else {
                                System.out.println("Ingrese un numero valido");
                                esperar(2000);
                            }
                        }
                    }
                    break;
                default:
                    System.exit(0);
            }

            ArrayList<Ficha> copiaPozo = new ArrayList<Ficha>(modelo.getPozo());
            ArrayList<Jugador> copiaJugadores = new ArrayList<Jugador>(modelo.getJugadores());
            
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
                    }
                    else { 
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
            esperar(5000);   

            System.out.println("\nQuieres guardar la configuracion de la partida? (s/n)");
            String respuesta = sc.next();
            if (respuesta.equals("s")) {
                System.out.println("Ingrese el nombre de la partida");
                String nombre = sc.next();
                Configuracion.guardarConfiguracion(nombre, copiaPozo, copiaJugadores);
            }
        }
    }

    static private void esperar(int tiempo) {
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static private boolean continuarJuego() {
        return (modelo.getJugador(0).puedeJugar() || 
                modelo.getJugador(1).puedeJugar()) || 
                modelo.getTamanioPozo() != 0;
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
