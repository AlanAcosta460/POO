import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Socket socket = new Socket("localhost", 1133);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            System.out.println("%%%%%% Menu %%%%%%");
            System.out.println("1. Search contact");
            System.out.println("2. Add contact");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            oos.writeInt(choice);
            oos.flush();

            switch (choice) {
                case 1: 
                    System.out.print("Enter person name: ");
                    String name = scanner.nextLine();

                    oos.writeUTF(name);
                    oos.flush();

                    Contact contact = (Contact) ois.readObject();

                    if (contact.getMobile() == null)
                        System.out.printf("Sorry! %s not found\n", name);
                    else {
                        System.out.println("Mobile   : " + contact.getMobile());
                        System.out.println("Email    : " + contact.getEmail());
                    }
                    break;

                case 2: 
                    System.out.print("Enter person name: ");
                    String nameToAdd = scanner.nextLine();

                    System.out.print("Enter mobile number: ");
                    String mobile = scanner.nextLine();

                    System.out.print("Enter email address: ");
                    String email = scanner.nextLine();

                    oos.writeObject(new Contact(nameToAdd, mobile, email));
                    oos.flush();
                    break;

                case 3: 
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }

            socket.close();
            System.out.println();
        }
    }
}
