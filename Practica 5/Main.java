interface Poligono {
    // firma de métodos
    void getArea(int a, int b);
}  

class Rectangulo implements Poligono {
    // implementacion del método de la interfaz
    public void getArea(int a, int b) {
        System.out.println("El área del rectangulo es: " + (a * b));
    }
}

class Main {
    public static void main(String[] args) {
        Rectangulo r = new Rectangulo();
        r.getArea(4, 8);
    }
}
