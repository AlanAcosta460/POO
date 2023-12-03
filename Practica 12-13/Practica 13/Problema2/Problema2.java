public class Problema2 {
    public static void main(String[] args) {
        Circle circulo = new Circle(new Red(), "Circulo 1");
        Rectangle rectangulo = new Rectangle(new Blue(), "Rectangulo 1");
        Triangle triangulo = new Triangle(new Green(), "Triangulo 1");
        Square cuadrado = new Square(new Yellow(), "Cuadrado 1");

        System.out.println(circulo);
        System.out.println(rectangulo);
        System.out.println(triangulo);
        System.out.println(cuadrado);
    }
}