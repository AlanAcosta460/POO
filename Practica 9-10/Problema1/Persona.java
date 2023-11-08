abstract class Persona{
	String nombre;
	int edad;
	Sx sexo;
	String nacionalidad;

	public String getNombre() {
		return this.nombre;
	}

	public int getEdad() {
		return this.edad;
	}

	public Sx getSexo() {
		return this.sexo;
	}

	public String getNacionalidad() {
		return this.nacionalidad;
	}

	abstract boolean votar();
	abstract boolean servicioMilitar();
	abstract boolean serPresidente();
}

class Mexicano extends Persona {
	String curp; 
	Mexicano(String nombre, int edad, Sx sexo, String curp) {
		this.nombre=nombre;
		this.sexo=sexo;
		this.edad=edad;
		this.curp=curp;
		this.nacionalidad="Mexicano";
	}

	@Override
	boolean votar(){
		return (this.edad >=18) ?  true : false;
	}

	@Override
	boolean servicioMilitar() {
		return (this.edad >=17 && this.sexo == Sx.MASCULINO) ?  true : false;
	}

	@Override
	boolean serPresidente() {
		return (this.edad >=35) ?  true : false;
	}
}

class Extranjero extends Persona {
	Extranjero(String nombre, int edad, Sx sexo) {
		this.nombre=nombre;
		this.sexo=sexo;
		this.edad=edad;
		this.nacionalidad="Extranjero";
	}

	@Override
	boolean votar() {
		return false;
	}

	@Override
	boolean servicioMilitar() {
		return false;
	}

	@Override
	boolean serPresidente() {
		return false;
	}
}
