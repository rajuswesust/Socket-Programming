package simple_client_server_interaction_threading;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        System.out.println("[Client started...]");
        Socket socket = new Socket("127.0.0.1", 22222);
        System.out.println("Client Connected..." + "(Enter exit to disconnect)\n\n");

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

        //taking UserName
        String name = getName();
        oos.writeObject(name);

        while (true) {
            Scanner sc = new Scanner(System.in);

            String message = sc.nextLine();

            if (message.equals("exit")) {
                break;
            }

            //sent to server...
            oos.writeObject(message);

            try {
                //receive from server..
                Object fromServer = ois.readObject();
                System.out.println("[Server]:" + (String) fromServer);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        socket.close();

    }

    public static String getName() {
        System.out.println("Enter your name: ");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();

        return name;
    }
}