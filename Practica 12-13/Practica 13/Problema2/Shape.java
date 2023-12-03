public class Shape {
    Color color;
    String nombre;

    public Shape(Color color, String nombre) {
        this.color = color;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + "\tColor: " + color;
    }
}

class Circle extends Shape {
    public Circle(Color color, String nombre) {
        super(color, nombre);
    }
}

class Rectangle extends Shape {
    public Rectangle(Color color, String nombre) {
        super(color, nombre);
    }
}

class Triangle extends Shape {
    public Triangle(Color color, String nombre) {
        super(color, nombre);
    }
}

class Square extends Shape {
    public Square(Color color, String nombre) {
        super(color, nombre);
    }
}