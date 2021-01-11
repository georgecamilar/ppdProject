package spring.socketserver;

import spring.controller.Service;
import spring.model.Spectacol;
import spring.model.Vanzare;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.Date;
import java.util.Optional;

public class Task implements Runnable {

    private Socket clientConnection;
    private String taskDescription;
    private Service serviceInstance;
    private String result;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public Task(String taskDescription, Socket clientConnection, ObjectOutputStream outputStream, ObjectInputStream inputStream, Service serviceInstance) {
        this.clientConnection = clientConnection;
        this.taskDescription = taskDescription;
        this.serviceInstance = serviceInstance;
    }

    @Override
    public void run() {
        try {
            String[] taskFields = taskDescription.split(";");
            switch (taskFields[0]) {
                case "add":
                    String[] fields = taskFields[1].split("-");
                    String[] data = fields[1].split(" ");
                    if (fields[0].equals("Spectacol")) {
                        Spectacol spectacol = new Spectacol();
                        //set date
                        Date time = new Date(Long.parseLong(data[0]));
                        spectacol.setData_spectacol(time);
                        //set title
                        spectacol.setTitlu(data[1]);
                        spectacol.setPret_bilet(new BigDecimal(data[2]));
                        this.serviceInstance.getSpectacolRepository().save(spectacol);
                        this.result = "added";
                    }
                    if (fields[0].equals("Vanzare")) {
                        Vanzare vanzare = new Vanzare();
                        //set date
                        Date time = new Date(Long.parseLong(data[0]));
                        vanzare.setData_vanzare(time);
                        //set title
                        Optional<Spectacol> spectacol = serviceInstance.getSpectacolRepository().findById(Integer.parseInt(data[1]));
                        //todo spectacol exists check
                        vanzare.setSpectacol(spectacol.get());
                        this.serviceInstance.getVanzareRepository().save(vanzare);
                        this.result = "added";

                    }
                    break;
                case "getAll":
                    if (taskFields[1].equals("Spectacol")) {
                        StringBuilder builder = new StringBuilder();
                        serviceInstance.getSpectacolRepository().findAll().forEach(el -> builder.append(el.toString()).append(" "));
                        result = builder.toString();
                    }
                    break;
            }

            outputStream.writeObject(result);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
