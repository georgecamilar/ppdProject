import spring.connection.ClientConnection;
import spring.model.*;
import spring.service.ClientService;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static java.lang.Thread.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // establish connection
        ClientConnection client = new ClientConnection("localhost", 8081);

        //declare lists
        List<Vanzare> vanzariTrimise = new LinkedList<Vanzare>();
        BlockingQueue<Vanzare> vanzariPrimite = new ArrayBlockingQueue<Vanzare>(1024);

        // init clientService
        ClientService clientService = new ClientService(client);

        //sala
        List<Sala> sali = clientService.getSali();
        Integer nr_locuri = sali.get(0).getNrLocuri();

        // spectacole
        List<Spectacol> spectacole = clientService.getSpectacole();
        SenderThread st = new SenderThread(nr_locuri, vanzariTrimise, clientService, spectacole);
        st.start();

        ReaderThread rt = new ReaderThread(nr_locuri, vanzariTrimise, vanzariPrimite, clientService, spectacole);
        rt.start();

        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SenderThread extends Thread {
    int nr_locuri;
    List<Vanzare> vanzariTrimise;
    BlockingQueue<Vanzare> vanzariPrimite;
    ClientService clientService;
    List<Spectacol> spectacole;

    public SenderThread(int nr_locuri, List<Vanzare> vanzariTrimise, ClientService clientService, List<Spectacol> spectacole) {
        this.clientService = clientService;
        this.nr_locuri = nr_locuri;
        this.vanzariTrimise = vanzariTrimise;
        this.spectacole = spectacole;
    }

    @Override
    public void run() {
        while (true) {
            try {
                clientService.sendRandomVanzare(nr_locuri, spectacole);
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class ReaderThread extends Thread {
    int nr_locuri;
    List<Vanzare> vanzariTrimise;
    BlockingQueue<Vanzare> vanzariPrimite;
    ClientService clientService;
    List<Spectacol> spectacole;

    public ReaderThread(int nr_locuri, List<Vanzare> vanzariTrimise, BlockingQueue<Vanzare> vanzariPrimite, ClientService clientService, List<Spectacol> spectacole) {
        this.nr_locuri = nr_locuri;
        this.vanzariTrimise = vanzariTrimise;
        this.vanzariPrimite = vanzariPrimite;
        this.clientService = clientService;
        this.spectacole = spectacole;
    }

    @Override
    public void run() {
        while (true) {
            ObjectInputStream inputStream = this.clientService.clientConnection.getInputStream();
            try {
                Object received = inputStream.readObject();
                sleep(100);

                if (received instanceof VanzareResponse) {
                    System.out.println(((VanzareResponse) received).success);
                }

                if (received instanceof LocuriOcupateUpdate) {
                    System.out.println(((LocuriOcupateUpdate) received).locuriOcupate);
                }

                if (received instanceof String && received.equals("TERMINATED")) {
                    break;
                }
            } catch (EOFException exception) {
                System.err.println(exception.getMessage());
                break;
            } catch (Exception e) {
                System.err.println("Server closed");;
                System.exit(0);
            }
        }
    }
}
