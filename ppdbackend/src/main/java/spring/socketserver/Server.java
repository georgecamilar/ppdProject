package spring.socketserver;

import org.springframework.beans.factory.annotation.Autowired;
import spring.controller.Service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    private ServerSocket serverSocket;
    private boolean running;
    private Queue<ClientOrder> clientOrders = new LinkedList<>();
    private List<WorkerThread> clientList = new ArrayList<>();
    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

    @Autowired
    Service service;

    public Server() throws IOException {
        serverSocket = new ServerSocket(8081);
    }


    public void run() {
        running = true;
        ReadThread readThread = new ReadThread();
        readThread.start();
        while (running) {
            try {
                /* accepting connections */
                System.out.println("Waiting for clients...");

                Socket client = serverSocket.accept();
                ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());

                WorkerThread worker = new WorkerThread(client, inputStream, outputStream);
                worker.start();
                clientList.add(worker);

                System.out.println("Client connected! Inet address : " + client.getInetAddress());
            } catch (IOException exception) {
                exception.printStackTrace();
                running = false;
            }
        }
    }


    class ReadThread extends Thread {
        @Override
        public void run() {
            executeCommands();
        }

        private void executeCommands() {
            while (true) {
                if (!clientOrders.isEmpty()) {
                    for (ClientOrder clientOrder : clientOrders)
                        executor.execute(new Task(clientOrder.command, clientOrder.client, clientOrder.outputStream, clientOrder.inputStream, service));
                }
            }
        }
    }

    class ClientOrder {
        Socket client;
        ObjectOutputStream outputStream;
        ObjectInputStream inputStream;
        Object command;

        public ClientOrder(Socket client, ObjectOutputStream outputStream, ObjectInputStream inputStream, Object command) {
            this.client = client;
            this.command = command;
            this.inputStream = inputStream;
            this.outputStream = outputStream;
        }
    }

    class WorkerThread extends Thread {
        Socket clientConnection;
        ObjectInputStream inputStream;
        ObjectOutputStream outputStream;

        public WorkerThread(Socket clientConnection, ObjectInputStream inputStream, ObjectOutputStream outputStream) {
            this.clientConnection = clientConnection;
            this.inputStream = inputStream;
            this.outputStream = outputStream;
        }

        @Override
        public void run() {
            while (running) {
                try {
                    Object received =  inputStream.readObject();
                    ClientOrder clientOrder = new ClientOrder(clientConnection, outputStream, inputStream, received);
                    clientOrders.add(clientOrder);
                } catch (IOException | ClassNotFoundException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

}
