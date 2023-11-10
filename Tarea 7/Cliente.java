class Cliente implements Banco {
    private String nombre;
    private Tarjeta tarjeta;

    public Cliente(String nombre, Tarjeta tarjeta) {
        this.nombre = nombre;
        this.tarjeta = tarjeta;
    }

    @Override
    public void retirar(double cantidad) throws OperacionNoValidaException {
        if (cantidad <= 0 || cantidad > tarjeta.consultarSaldo()) {
            throw new OperacionNoValidaException("Operacion de retiro no valida");
        }
        tarjeta.saldo -= cantidad;
        System.out.println("Retiro exitoso. Nuevo saldo: " + tarjeta.consultarSaldo());
    }

    @Override
    public void abonar(double cantidad) throws OperacionNoValidaException {
        if (cantidad <= 0) {
            throw new OperacionNoValidaException("Operacion de abono no valida");
        }
        tarjeta.saldo += cantidad;
        System.out.println("Abono exitoso. Nuevo saldo: " + tarjeta.consultarSaldo());
    }

    @Override
    public double consultarSaldo() {
        return tarjeta.consultarSaldo();
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }
}