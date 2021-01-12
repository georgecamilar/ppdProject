package spring.socketserver;

import spring.controller.Service;
import spring.model.ConnectedClient;
import spring.model.LocuriOcupateUpdate;
import spring.model.Vanzare;
import spring.model.VanzareResponse;
import spring.repos.SalaRepository;
import spring.repos.SpectacolRepository;
import spring.repos.VanzareRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Task implements Runnable {

    private SalaRepository salaRepository = new SalaRepository();
    private SpectacolRepository spectacolRepository = new SpectacolRepository();
    private VanzareRepository vanzareRepository = new VanzareRepository();

    private Socket clientConnection;
    private Object taskDescription;
    private Service serviceInstance;
    private Object result;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private List<Vanzare> vanzari;
    private List<ConnectedClient> connectedClients;

    public Task(Object taskDescription, Socket clientConnection, ObjectOutputStream outputStream, ObjectInputStream inputStream, Service serviceInstance, List<Vanzare> vanzari, List<ConnectedClient> connectedClients) {
        this.clientConnection = clientConnection;
        this.taskDescription = taskDescription;
        this.serviceInstance = serviceInstance;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.vanzari = vanzari;
        this.connectedClients = connectedClients;
    }

    @Override
    public void run() {
        try {
        if (taskDescription instanceof Vanzare) {
            synchronized (vanzari) {
                Vanzare vanzare = (Vanzare) taskDescription;

                Boolean foundOccupiedSeat = false;

                // check if we are trying to buy an occupied seat
                for (Vanzare element: vanzari) {
                    List<Integer> locuriOcupate = element.getListaLocuri();
                    List<Integer> locuriDorite = vanzare.getListaLocuri();

                    if (vanzare.getSpectacolId().equals(element.getSpectacolId())) {
                        for(int i = 0; i < locuriDorite.size(); i++) {
                            for(int j = 0; j < locuriOcupate.size(); j++) {
                                if (locuriDorite.get(i).equals(locuriOcupate.get(j))) {
                                    foundOccupiedSeat = true;
                                    break;
                                }
                            }
                            if (foundOccupiedSeat)
                                break;
                        }
                    }
                }

                if (!foundOccupiedSeat) {
                    vanzari.add(vanzare);
                    result = new VanzareResponse(vanzare.getId(), true);
                    vanzareRepository.saveToDisk(vanzari);

                    List<Integer> locuriOcupate = new LinkedList<>();

                    // gasim locurile ocupate
                    for (Vanzare element: vanzari) {
                        locuriOcupate.addAll(element.getListaLocuri());
                    }

                    System.out.println(locuriOcupate);
                    LocuriOcupateUpdate locuriOcupateMsg = new LocuriOcupateUpdate(locuriOcupate);
                    // notify clients
                    for (ConnectedClient cc: connectedClients) {
                        if (cc.client.isConnected()) {
                            try {
                                cc.stream.writeObject(locuriOcupateMsg);
                            } catch (Exception e) {
                                System.err.println("Client disconnected");
                                connectedClients.remove(cc);
                            }
                        }
                    }
                } else {
                    result = new VanzareResponse(vanzare.getId(), false);
                }
            }
        }  else if (taskDescription instanceof String) {
            switch ((String) taskDescription) {
                case "Spectacol":
                    result = spectacolRepository.findAll();
                    break;
                case "Sala":
                    result = salaRepository.findAll();
                    break;
                default:
                    result = "Invalid Command";
            }
        }


            System.out.println(result);
            outputStream.writeObject(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
