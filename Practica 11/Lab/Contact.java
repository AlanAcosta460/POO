import java.io.Serializable;

public class Contact implements Serializable {
  private String name, mobile, email;
  private transient String address;
   
  public Contact(String n, String t, String e) {
    this.name = n;
    this.mobile = t;
    this.email = e;
  }

  public Contact() {}

  public void setAddress(String s) {this.address = s;}
  public String getAddress() {return address;}

  //public void setName(String s) {this.name = s;}
  public String getName() {return name;}

  public String getMobile() {return mobile;}
  public String getEmail() {return email;}

  public String toString() {
    return "El contacto en la agenda POO: \nSe llama:" + this.name + 
        "\nSu telefono: " + this.mobile	+ 
        "\nSu correo: " + this.email + 
        "\nSu domicilio: "+ this.address;
  }
}
