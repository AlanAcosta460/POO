import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws Exception {
        ArrayList<Contact> contacts = new ArrayList<>();

        contacts.add(new Contact("Steve","9090909090","steve@gmail.com"));
        contacts.add(new Contact("Bill","9988776655","bill@hotmail.com"));
        contacts.add(new Contact("Jack","9876543210","jack@yaho.com"));
        contacts.add(new Contact("Larry","8877996655","larry@gmail.com"));

        ServerSocket serversocket  = new ServerSocket(1133,10);
        System.out.println("Contacts server is ready ....");

        while(true) {
            Socket client = serversocket.accept();

            // take input and output streams
            Scanner scanner  = new Scanner(client.getInputStream());
            ObjectOutputStream oos  = new ObjectOutputStream(client.getOutputStream());

            // find contact with the given name
            String name = scanner.nextLine();
            boolean found = false;

            for(Contact c : contacts) {
                if (c.getName().equals(name)) {
                    found = true;
                    oos.writeObject(c);  // serialize object and send to client
                }
            }
            
			if (!found) {
                // write Contact object only with name when name is not found
                oos.writeObject(new Contact(name,null,null));
            }
            client.close();
        } 
    }
}
