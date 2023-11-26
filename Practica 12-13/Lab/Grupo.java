public class Grupo extends Thread{
	public Grupo(ThreadGroup G, String n){
		super(G,n);

	}
	public void run(){
		for (int i=0;i<10 ;i++ ) {
			System.out.println(getName());
			
		}
	}

	public static void listarHilos(ThreadGroup grupoActual){
		int numhilos;
		Thread[]listaHilos;

		numhilos = grupoActual.activeCount();
		listaHilos = new Thread[numhilos];
		System.out.println("Numero de hilos activos en el grupo " + numhilos);
		for (int i=0; i<numhilos ;i++ ) {
			System.out.println("Hilo Activo: " + (i+1) + "=" + listaHilos[i].getName());
			
		}
	
	}

	public static void main(String[] args) {

		ThreadGroup grupoH = new ThreadGroup("Grupo de Hilos prioridad normal");
		Thread hilo1 = new Grupo(grupoH, "Hilo 1 con prioridad normal");
		Thread hilo2 = new Grupo(grupoH, "Hilo 2 con prioridad normal");
		Thread hilo3 = new Grupo(grupoH, "Hilo 3 con prioridad normal");
		Thread hilo4 = new Grupo(grupoH, "Hilo 4 con prioridad normal");
		Thread hilo5 = new Grupo(grupoH, "Hilo 5 con prioridad normal");
		Thread hilo6 = new Grupo(grupoH, "Hilo 6 con prioridad normal");
		Thread hilo7 = new Grupo(grupoH, "Hilo 7 con prioridad normal");
		Thread hilo8 = new Grupo(grupoH, "Hilo 8 con prioridad normal");
		Thread hilo9 = new Grupo(grupoH, "Hilo 9 con prioridad normal");
		Thread hilo10 = new Grupo(grupoH, "Hilo 10 con prioridad normal");

		hilo7.setPriority(Thread.MAX_PRIORITY);
		hilo10.setPriority(Thread.MIN_PRIORITY);
		grupoH.setMaxPriority(Thread.NORM_PRIORITY);

		System.out.println("La prioridad del grupo es de : " + grupoH.getMaxPriority());

		System.out.println("La prioridad del Thread es de : " + hilo1.getPriority());
		System.out.println("La prioridad del Thread es de : " + hilo2.getPriority());
		System.out.println("La prioridad del Thread es de : " + hilo3.getPriority());
		System.out.println("La prioridad del Thread es de : " + hilo4.getPriority());
		System.out.println("La prioridad del Thread es de : " + hilo5.getPriority());
		System.out.println("La prioridad del Thread es de : " + hilo6.getPriority());
		System.out.println("La prioridad del Thread es de : " + hilo7.getPriority());
		System.out.println("La prioridad del Thread es de : " + hilo8.getPriority());
		System.out.println("La prioridad del Thread es de : " + hilo9.getPriority());
		System.out.println("La prioridad del Thread es de : " + hilo10.getPriority());

		hilo1.start();
		hilo2.start();
		hilo3.start();
		hilo4.start();
		hilo5.start();
		hilo6.start();
		hilo7.start();
		hilo8.start();
		hilo9.start();
		hilo10.start();

		listarHilos(grupoH);
			
	}
	
}