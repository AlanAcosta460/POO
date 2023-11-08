import java.util.HashMap;
//import 
class Principal{
  public static void main(String[] args) {
    HashMap<String,Persona> gente = new HashMap<String,Persona>();

    gente.put("ERZ123220PO",new Mexicano("Eric",19,Sx.MASCULINO, "ERZ123220PO"));
    gente.put("AMZ453220LO",new Mexicano("Amir",19,Sx.INDETERMINADO, "AMZ453220LO"));
    gente.put("ABN123980KO",new Mexicano("Abner",16,Sx.MASCULINO, "ABN123980KO"));
    gente.put("SFN154620JO",new Mexicano("Sofia",19,Sx.FEMENINO, "SFN154620JO"));
    gente.put("IRN135220GO",new Mexicano("Irandy",19,Sx.FEMENINO, "IRN135220GO"));

    gente.forEach((k,p)-> {System.out.println(k + " : Nombre - " 
        + p.getNombre() + " : Edad - " + p.getEdad() );});
    
    //levantar excepcion por no poder votar
    gente.forEach((k, p) -> {
      try {
        if (!p.votar()) 
          throw new OuterException("no puede votar : " + p.getNombre());
      } catch (OuterException n) {
        System.out.println(n);
      }
    });

    // levantar excepcion por no poder ser presidente
    gente.forEach((k, p) -> {
      try {
        if (!p.serPresidente()) 
          throw new OuterException.InnerException
              ("no puede ser presidente : " + p.getNombre());
      } catch (OuterException.InnerException n) {
        System.out.println(n);
      }
    });

    // levantas excepcion para poder realizar servicio militar
    gente.forEach((k, p) -> {
      try {
        if (!p.servicioMilitar()) 
          throw new OuterException
              ("no puede realizar su servicio militar : " + p.getNombre(), true);
      } catch (OuterException n) {
        return;
      }
    });
  }
} 