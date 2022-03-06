package simple_client_server_interaction_threading;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

class ServerThread implements Runnable {

    Socket clientSocket;
    Thread t;
    int threadNumber = 0;
    String clientName;

    ServerThread(Socket clientSocket, int threadNumber) {
        this.clientSocket = clientSocket;
        t = new Thread(this);
        this.threadNumber = threadNumber;
        t.start();
    }


    @Override
    public void run() {

        try {
            ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

            //Taking Client's Name
            Object temp = ois.readObject();
            clientName = (String) temp;

            while (true) {
                //read from client...
                Object cMsg = ois.readObject();

                if (cMsg == null) {
                    break;
                }
                //System.out.println("Client " + threadNumber + ": " + (String) cMsg);
                System.out.println(clientName + ": " + (String) cMsg);

                String serverMsg = (String) cMsg;
                serverMsg = serverMsg.toUpperCase();

                //send to client..
                oos.writeObject(serverMsg);
            }

        } catch (ClassNotFoundException | IOException e) {
            System.out.println("\n[" + clientName + " is disconnected]\n");
            //System.out.println("\n[Client "+threadNumber+" is disconnected...]\n");
            //e.printStackTrace();
        }

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
