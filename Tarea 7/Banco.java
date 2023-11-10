public interface Banco{

	void retirar(double cantidad) throws OperacionNoValidaException; 
	void abonar(double cantidad) throws OperacionNoValidaException; 
	double consultarSaldo();
	 
}