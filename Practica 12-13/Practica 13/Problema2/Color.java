public class Color {
    String tipo;
    
    public String toString() {
        return tipo;
    }
}

class Red extends Color {
    public Red () {
        tipo = "Rojo";
    }
}

class Blue extends Color {
    public Blue () {
        tipo = "Azul";
    }
}

class Green extends Color {
    public Green () {
        tipo = "Verde";
    }
}

class Yellow extends Color {
    public Yellow () {
        tipo = "Amarillo";
    }
}