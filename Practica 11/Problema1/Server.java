import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    public static void main(String[] args) throws Exception {
        ArrayList<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact("Steve", "9090909090", "steve@gmail.com"));
        contacts.add(new Contact("Bill", "9988776655", "bill@hotmail.com"));
        contacts.add(new Contact("Jack", "9876543210", "jack@yaho.com"));
        contacts.add(new Contact("Larry", "8877996655", "larry@gmail.com"));

        ServerSocket serversocket = new ServerSocket(1133, 10);
        System.out.println("Contacts server is ready ....");

        while (true) {
            Socket client = serversocket.accept();

            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());

            int choice = ois.readInt();

            switch (choice) {
                case 1: 
                    String name = ois.readUTF();
                    boolean found = false;

                    for (Contact c : contacts) {
                        if (c.getName().equals(name)) {
                            found = true;
                            oos.writeObject(c);
                            break; 
                        }
                    }

                    if (!found)
                        oos.writeObject(new Contact(name, null, null));
                    break;

                case 2: 
                    Contact newContact = (Contact) ois.readObject();
                    contacts.add(newContact);
                    break;
            }   
        }
    }
}
