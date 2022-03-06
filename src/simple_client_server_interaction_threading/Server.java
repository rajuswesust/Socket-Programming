package simple_client_server_interaction_threading;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(22222);
        System.out.println("Server Started...\n\n");

        int cnt = 1;
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("\n[Client "+ cnt+ " is connected]\n");

            // new Server Thread Start.....
            ServerThread serverThread = new ServerThread(socket, cnt);
            //System.out.println("is alive "+serverThread.t.isAlive());

            cnt++;
        }
    }
}
