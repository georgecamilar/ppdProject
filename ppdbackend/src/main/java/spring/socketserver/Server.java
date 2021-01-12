package spring.socketserver;
import spring.controller.Service;
import spring.model.ConnectedClient;
import spring.model.Vanzare;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {
    private ServerSocket serverSocket;
    private boolean running;
    private Queue<ClientOrder> clientOrders = new ArrayBlockingQueue<ClientOrder>(1024);

    private List<Vanzare> vanzari = Collections.synchronizedList(new ArrayList<Vanzare>());

    private List<WorkerThread> clientList = new ArrayList<>();
    private List<ConnectedClient> connectedClients = new ArrayList<ConnectedClient>();

    private ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

    Service service;

    public Server() throws IOException {
        serverSocket = new ServerSocket(8081);
    }


    public void run() {
        running = true;
        ReadThread readThread = new ReadThread(vanzari);
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
                connectedClients.add(new ConnectedClient(client, outputStream));

                System.out.println("Client connected! Inet address : " + client.getInetAddress());
            } catch (IOException exception) {
                exception.printStackTrace();
                running = false;
            }
        }
    }


    class ReadThread extends Thread {
        List<Vanzare> vanzari;
        public ReadThread(List<Vanzare> vanzari) {
            this.vanzari = vanzari;
        }

        @Override
        public void run() {
            executeCommands();
        }

        private void executeCommands() {
            while (true) {
                if (!clientOrders.isEmpty()) {
                    for (ClientOrder clientOrder : clientOrders){
                        executor.execute(new Task(clientOrder.command, clientOrder.client, clientOrder.outputStream, clientOrder.inputStream, service, this.vanzari, connectedClients));
                        clientOrders.remove(clientOrder);
                    }
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
                } catch (Exception exception) {
                    System.err.println(exception.getMessage());
                    break;
                }
            }
        }
    }
}
