package spring.service;

import spring.connection.ClientConnection;
import spring.model.Sala;
import spring.model.Spectacol;
import spring.model.Vanzare;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class ClientService {
    public ClientConnection clientConnection;
    public ClientService(ClientConnection clientConnection) {
        this.clientConnection = clientConnection;
    }

    public List<Sala> getSali() {
        ObjectOutputStream outputStream = this.clientConnection.getOutputStream();
        ObjectInputStream inputStream = this.clientConnection.getInputStream();
        Object sala = null;

        try {
            outputStream.writeObject("Sala");
            sala = inputStream.readObject();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (List<Sala>)sala;
    }

    public List<Spectacol> getSpectacole() {
        ObjectOutputStream outputStream = this.clientConnection.getOutputStream();
        ObjectInputStream inputStream = this.clientConnection.getInputStream();
        Object listaSpectacole = null;

        try {
            outputStream.writeObject("Spectacol");
            listaSpectacole = inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (List<Spectacol>)listaSpectacole;
    }

    public void sendRandomVanzare(Integer nr_locuri, List<Spectacol> spectacole) {
        ObjectOutputStream outputStream = this.clientConnection.getOutputStream();
        ObjectInputStream inputStream = this.clientConnection.getInputStream();

        Random rand = new Random();
        Spectacol randomSpectacol = spectacole.get(rand.nextInt(spectacole.size()));

        int spectacol_id = randomSpectacol.getId();
        int nr_bilete = 5; //rand.nextInt(nr_locuri - 1) + 1;
        Date data_vanzare = new Date();
        List<Integer> lista_locuri = generateRandomArray(nr_bilete, nr_locuri);

        Vanzare v = new Vanzare();
        v.setId(UUID.randomUUID());
        v.setDataVanzare(data_vanzare);
        v.setListaLocuri(lista_locuri);
        v.setSpectacolId(spectacol_id);

        try {
            outputStream.writeObject(v);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> generateRandomArray(int n, int max){
        List<Integer> list = new LinkedList<Integer>();
        Random random = new Random();

        for (int i = 0; i < n; i++)  {
            list.add(random.nextInt(max - 1) + 1);
        }

        return list;
    }
}
