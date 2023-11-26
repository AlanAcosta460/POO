class Cuenta extends Thread{
	private static long saldo = 0;
	public Cuenta(String nombre){
		super(nombre);

	}

	public void run(){
		if (getName().equals("Deposito 1") || getName().equals("Deposito 2")){
			this.depositarDinero(100);
		}else{
			this.extraerDinero(50);
		}
	}

	public synchronized void extraerDinero(int cantidad){
		try{

			if(saldo <= 0){
				System.out.println(getName() + "espera a que depositen lana");
				sleep(5000);

			}

		}catch(InterruptedException e){
			System.out.println(e);

		}

		saldo-= cantidad;
		System.out.println(getName() + "extrajo 50, el saldo es: " + saldo);
		notifyAll();

	}

	public synchronized void depositarDinero(int cantidad){
		saldo += cantidad;
		System.out.println("Se deposito dinero (100), el saldo es: " + saldo );
		notifyAll();

	}
	public static void main(String[] args) {
		new Cuenta("Acceso A").start();
		new Cuenta("Acceso B").start();
		new Cuenta("Deposito 1 ").start();
		new Cuenta("Deposito 2 ").start();
		System.out.println("termina el main");
	}
}