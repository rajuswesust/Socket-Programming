package simple_client_server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(22222);

        System.out.println("Server Started...");

        while(true) {
            Socket socket = serverSocket.accept();

            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            try {
                Object clientMsg = in.readObject();
                System.out.println("From Client: " + (String) clientMsg);

                String serverMsg = (String) clientMsg;
                serverMsg = serverMsg.toUpperCase();

                out.writeObject(serverMsg);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

}
