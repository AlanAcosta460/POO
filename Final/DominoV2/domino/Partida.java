package domino;

class Partida {
    private String nombre;
    private int turno;
    private String fecha;
    
    public Partida(String nombre, int turno, String fecha) {
        this.nombre = nombre;
        this.turno = turno;
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTurno() {
        return turno;
    }

    public String getFecha() {
        return fecha;
    }
}