import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception  {
        Socket socket  = new Socket("localhost",1133);
        Scanner scanner  = new Scanner(System.in);
        ObjectInputStream ois  = new ObjectInputStream(socket.getInputStream());
        
        // take name from keyboard
        System.out.print("Enter person name : ");
        String name = scanner.nextLine();

        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        pw.println(name);

        // read Contact object from server and deserialize it
        Contact contact = (Contact) ois.readObject();

        if (contact.getMobile() == null) // contact not found
            System.out.printf("Sorry! %s not found\n", name);
        else {
            System.out.println("Mobile   : " + contact.getMobile());
            System.out.println("Email    : " + contact.getEmail());
            // System.out.println("Address    : " + contact.getAddress());
        }
    }
}
