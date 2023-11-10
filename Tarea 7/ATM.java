class ATM implements Banco {
    private double saldoATM;

    public ATM(double saldoInicialATM) {
        this.saldoATM = saldoInicialATM;
    }

    @Override
    public void retirar(double cantidad) throws OperacionNoValidaException {
        if (cantidad <= 0 || cantidad > saldoATM) {
            throw new OperacionNoValidaException("Operacion de retiro no valida en el ATM");
        }
        saldoATM -= cantidad;
        System.out.println("Retiro exitoso en el ATM. Nuevo saldo del ATM: " + saldoATM);
    }

    @Override
    public void abonar(double cantidad) throws OperacionNoValidaException {
        throw new OperacionNoValidaException("Operacion de abono no permitida en el ATM");
    }

    @Override
    public double consultarSaldo() {
        return saldoATM;
    }

    // Metodo para verificar saldo en la tarjeta
    public void verificarSaldoTarjeta(Tarjeta tarjeta) throws OperacionNoValidaException {
        if (tarjeta.consultarSaldo() <= 0) {
            throw new OperacionNoValidaException("Saldo insuficiente en la tarjeta");
        }
    }
}