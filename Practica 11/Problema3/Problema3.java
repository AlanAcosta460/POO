import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class Problema3 implements Serializable {
    public static class Alumno implements Serializable {
        String nombre;
        String apellidoPaterno;
        String apellidoMaterno;
        int edad;
        double estatura;
        int peso;
        double promedio;
        int semestre;

        public Alumno(String nombre, String apellidoPaterno, String apellidoMaterno, 
            int edad, double estatura, int peso, double promedio, int semestre) {
            this.nombre = nombre;
            this.apellidoPaterno = apellidoPaterno;
            this.apellidoMaterno = apellidoMaterno;
            this.edad = edad;
            this.estatura = estatura;
            this.peso = peso;
            this.promedio = promedio;
            this.semestre = semestre;
        }
    }

    public static void main(String[] args) {
        String[] nombres = {"Juan", "María", "Carlos", "Laura", 
            "Roberto", "Ana", "Pedro", "Sofía", "Miguel", "Elena"};
        String[] apellidos = {"Gómez", "Martínez", "López", "Rodríguez", 
            "Pérez", "García", "Fernández", "Díaz", "Hernández", "Moreno"};

        final int MINEDAD = 17, MAXEDAD = 25;
        final double MINESTATURA = 1.45, MAXESTATURA = 2.00;
        final int MINPESO = 30, MAXPESO = 120;
        final double MINPROMEDIO = 5.0, MAXPROMEDIO = 10.0;
        final int MINSEMESTRE = 1, MAXSEMESTRE = 10;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("alumnos.txt"))) {
            Random random = new Random();

            for (int i = 0; i < 60; i++) {
                String nombre = nombres[random.nextInt(nombres.length)];
                String apellidoPaterno = apellidos[random.nextInt(apellidos.length)];
                String apellidoMaterno = apellidos[random.nextInt(apellidos.length)];
                int edad = random.nextInt(MAXEDAD - MINEDAD + 1) + MINEDAD;
                double estatura = MINESTATURA + (MAXESTATURA - MINESTATURA) * random.nextDouble();
                int peso = random.nextInt(MAXPESO - MINPESO + 1) + MINPESO;
                double promedio = MINPROMEDIO + (MAXPROMEDIO - MINPROMEDIO) * random.nextDouble();
                int semestre = random.nextInt(MAXSEMESTRE - MINSEMESTRE + 1) + MINSEMESTRE;

                String linea = String.format("%s | %s| %s | %d | %.2f | %d | %.2f | %d",
                        nombre, apellidoPaterno, apellidoMaterno, edad, 
                        estatura, peso, promedio, semestre);
                writer.write(linea);
                writer.newLine();
            }

            System.out.println("Archivo creado exitosamente.");

            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
        }

        ArrayList<Alumno> lista = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("alumnos.txt"))) {
            while (scanner.hasNextLine()) {
                StringTokenizer st = new StringTokenizer(scanner.nextLine(), " | ");
                lista.add(new Alumno(st.nextToken(), st.nextToken(), st.nextToken(),
                        Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()),
                        Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()),
                        Integer.parseInt(st.nextToken())));
            }

            System.out.println("Lista de alumnos creada exitosamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("lista.ser"))) {
            oos.writeObject(lista);
            oos.close();
            System.out.println("Lista de alumnos serializada exitosamente.");
        } catch(IOException e) {
            e.printStackTrace();
        }

        lista.clear();

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("lista.ser"))) {
            lista = (ArrayList) ois.readObject();
            ois.close();
            System.out.println("Lista de alumnos deserializada exitosamente.");
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }  

        double promedio = lista.stream().mapToInt(a -> a.edad).average().getAsDouble();
        System.out.printf("\nPromedio de edad: %.2f\n",  promedio);
        int moda = lista.stream().mapToInt(a -> a.edad).max().getAsInt();
        System.out.println("Moda de edad: " + moda);
        int mediana = lista.stream().mapToInt(a -> a.edad).sorted().toArray()[lista.size() / 2];
        System.out.println("Mediana de edad: " + mediana);

        promedio = lista.stream().mapToDouble(a -> a.estatura).average().getAsDouble();
        System.out.printf("\nPromedio de estatura: %.2f\n", promedio);
        double modaEstatura = lista.stream().mapToDouble(a -> a.estatura).max().getAsDouble();
        System.out.println("Moda de estatura: " + modaEstatura);
        double medianaEstatura = lista.stream().mapToDouble(a -> a.estatura).sorted().toArray()[lista.size() / 2];
        System.out.println("Mediana de estatura: " + medianaEstatura);

        promedio = lista.stream().mapToInt(a -> a.peso).average().getAsDouble();
        System.out.printf("\nPromedio de peso: %.2f\n", promedio);
        moda = lista.stream().mapToInt(a -> a.peso).max().getAsInt();
        System.out.println("Moda de peso: " + moda);
        mediana = lista.stream().mapToInt(a -> a.peso).sorted().toArray()[lista.size() / 2];
        System.out.println("Mediana de peso: " + mediana);

        promedio = lista.stream().mapToDouble(a -> a.promedio).average().getAsDouble();
        System.out.printf("\nPromedio de peso: %.2f\n", promedio);
        double modaPromedio = lista.stream().mapToDouble(a -> a.promedio).max().getAsDouble();
        System.err.println("Moda de promedio: " + modaPromedio);
        double medianaPromedio = lista.stream().mapToDouble(a -> a.promedio).sorted().toArray()[lista.size() / 2];
        System.err.println("Mediana de promedio: " + medianaPromedio);

        promedio = lista.stream().mapToInt(a -> a.semestre).average().getAsDouble();
        System.out.printf("\nPromedio de peso: %.2f\n", promedio);
        moda = lista.stream().mapToInt(a -> a.semestre).max().getAsInt();
        System.err.println("Moda de semestre: " + moda);
        mediana = lista.stream().mapToInt(a -> a.semestre).sorted().toArray()[lista.size() / 2];
        System.err.println("Mediana de semestre: " + mediana);
    }
}
