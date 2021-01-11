package spring.socketserver;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    private ServerSocket serverSocket;
    private boolean running;
    private Queue<String> clientOrders;
    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

    public Server() throws IOException {
        clientOrders = new LinkedList<>();
        serverSocket = new ServerSocket(8081);
    }


    public void run() {
        running = true;
        while (running) {
            try {
                /* accepting connections */
                System.out.println("Waiting for clients...");

                Socket client = serverSocket.accept();
                ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());

                System.out.println("Client connected! Inet address : " + client.getInetAddress());
                Thread taskManagerThread;

            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }
}
