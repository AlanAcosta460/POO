import java.util.HashMap;
import java.util.Scanner;
//import 

class Principal {
	public static void main(String[] args) 
      	throws OuterException, OuterException.InnerException {
		HashMap<String,Persona> gente = new HashMap<String,Persona>();
		Scanner sc = new Scanner(System.in);

		while(true) {
			System.out.print("Ingrese el nombre: ");
			String nombre = sc.next();
			System.out.print("Ingrese la edad: ");
			int edad = sc.nextInt();
			System.out.print("Ingrese el sexo: ");
			Sx sexo = Sx.valueOf(sc.next().toUpperCase());
			System.out.print("Ingrese el CURP: ");
			String curp = sc.next();

			gente.put(curp,new Mexicano(nombre,edad,sexo,curp));
			
			System.out.println("Desea agregar otra persona? (s/n)");
			String resp = sc.next();
			if(resp.equals("n")) 
				break;
			System.out.println(" ");
		}

		gente.forEach((k,p)-> {System.out.println(k + " : Nombre - " 
        	+ p.getNombre() + " : Edad - " + p.getEdad() );});
    
		gente.forEach((k, p) -> {
		try {
			if (!p.votar()) 
			throw new OuterException("no puede votar : " + p.getNombre());
		} catch (OuterException n) {
			System.out.println(n);
		}
		});

		gente.forEach((k, p) -> {
		try {
			if (!p.serPresidente()) 
			throw new OuterException.InnerException
				("no puede ser presidente : " + p.getNombre());
		} catch (OuterException.InnerException n) {
			System.out.println(n);
		}
		});

		gente.forEach((k, p) -> {
			try {
				if (!p.servicioMilitar()) 
				throw new OuterException
					("no puede realizar su servicio militar : " + p.getNombre(), true);
			} catch (OuterException n) {
				return;
			}
		});

		sc.close();
	}
}
