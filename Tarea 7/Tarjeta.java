import java.util.HashSet;
import java.util.Set;


public abstract class Tarjeta {
    protected double saldo;
    protected Set<Integer> nipsUtilizados;

    public Tarjeta(double saldoInicial) {
        this.saldo = saldoInicial;
        this.nipsUtilizados = new HashSet<>();
    }

    public abstract void verificarNIP(int nip) throws NIPIncorrectoException;

    public double consultarSaldo() {
        return saldo;
    }
}

class TarjetaDebito extends Tarjeta {
    private int nip;

    public TarjetaDebito(double saldoInicial, int nip) {
        super(saldoInicial);
        this.nip = nip;
    }

    @Override
    public void verificarNIP(int nipIngresado) throws NIPIncorrectoException {
        if (nipIngresado != nip) {
            throw new NIPIncorrectoException("NIP incorrecto");
        }
    }
}

class TarjetaCredito extends Tarjeta {
    private int nip;

    public TarjetaCredito(double saldoInicial, int nip) {
        super(saldoInicial);
        this.nip = nip;
    }

    @Override
    public void verificarNIP(int nipIngresado) throws NIPIncorrectoException {
        if (nipIngresado != nip) {
            throw new NIPIncorrectoException("NIP incorrecto");
        }
    }
}