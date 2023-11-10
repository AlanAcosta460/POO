import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class ProgramaATM {
    private static Map<String, Cliente> clientesRegistrados = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Menu principal
        int opcion;
        do {
            System.out.println("\n\n\t ATM -/ENGINEERS/- ");
            System.out.println("1. Crear cuenta");
            System.out.println("2. Ingresar a la cuenta");
            System.out.println("3. Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    crearCuenta(scanner);
                    break;
                case 2:
                    ingresarCuenta(scanner);
                    break;
                case 3:
                    System.out.println("Saliendo del programa. Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor, ingrese una opcion valida.");
            }
        } while (opcion != 3);

        scanner.close();
    }

    private static void crearCuenta(Scanner scanner) {
        System.out.println("\nSeleccione el tipo de tarjeta:");
        System.out.println("1. Tarjeta de Debito");
        System.out.println("2. Tarjeta de Credito");
        System.out.print("Ingrese su opcion: ");
        int tipoTarjeta = scanner.nextInt();

        scanner.nextLine(); 

        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        if (clientesRegistrados.containsKey(nombre)) {
            System.out.println("Este usuario ya ha sido creado.");
            return;
        }

        System.out.print("Ingrese su NIP: ");
        int nip = scanner.nextInt();

        Tarjeta tarjeta;
        if (tipoTarjeta == 1) {
            tarjeta = new TarjetaDebito(0, nip); 
        } else if (tipoTarjeta == 2) {
            tarjeta = new TarjetaCredito(5000, nip); 
        } else {
            System.out.println("Opcion no valida. La cuenta no fue creada.");
            return;
        }

        try {
            tarjeta.verificarNIP(nip);
            Cliente nuevoCliente = new Cliente(nombre, tarjeta);
            clientesRegistrados.put(nombre, nuevoCliente);
            System.out.println("Usuario registrado con exito.");
        } catch (NIPIncorrectoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void ingresarCuenta(Scanner scanner) {
        System.out.print("\nIngrese su nombre: ");
        String nombre = scanner.next();

        if (clientesRegistrados.containsKey(nombre)) {
            Cliente cliente = clientesRegistrados.get(nombre);
            System.out.print("Ingrese su NIP: ");
            int nip = scanner.nextInt();
            try {
                cliente.getTarjeta().verificarNIP(nip);
                menuOperaciones(scanner, cliente);
            } catch (NIPIncorrectoException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } else {
            System.out.println("Usuario no registrado. Por favor, cree una cuenta primero.");
        }
    }


    private static void menuOperaciones(Scanner scanner, Cliente cliente) {
        int opcion;
        do {
            System.out.println("\n1. Retirar");
            System.out.println("2. Abonar");
            System.out.println("3. Consultar saldo");
            System.out.println("4. Salir");
            System.out.print("Ingrese su opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("\nIngrese la cantidad a retirar: ");
                    double cantidadRetiro = scanner.nextDouble();
                    try {
                        cliente.retirar(cantidadRetiro);
                    } catch (OperacionNoValidaException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("\nIngrese la cantidad a abonar: ");
                    double cantidadAbono = scanner.nextDouble();
                    try {
                        cliente.abonar(cantidadAbono);
                    } catch (OperacionNoValidaException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Saldo actual: " + cliente.consultarSaldo());
                    break;
                case 4:
                    System.out.println("Saliendo del menu de operaciones.");
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor, ingrese una opcion valida.");
            }
        } while (opcion != 4);
    }
}