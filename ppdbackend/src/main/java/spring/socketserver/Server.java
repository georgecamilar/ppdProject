package spring.socketserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private boolean running;
    public Server() throws IOException {
        serverSocket = new ServerSocket(8081);
    }


    public void run(){
        running = true;
        while(running){
            try {
                /* accepting connections */
                Socket client = serverSocket.accept();
                //todo add client to clientlist

                //fixme create client class containing in, out streams and connection
                ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
