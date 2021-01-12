package spring.socketserver;

import spring.controller.Service;
import spring.model.Spectacol;
import spring.model.Vanzare;
import spring.model.VanzareLoc;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Optional;

public class Task implements Runnable {

    private Socket clientConnection;
    private Object taskDescription;
    private Service serviceInstance;
    private Object result;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public Task(Object taskDescription, Socket clientConnection, ObjectOutputStream outputStream, ObjectInputStream inputStream, Service serviceInstance) {
        this.clientConnection = clientConnection;
        this.taskDescription = taskDescription;
        this.serviceInstance = serviceInstance;
    }

    @Override
    public void run() {
        if (taskDescription instanceof Spectacol) {
            Optional<Spectacol> optionalVar = serviceInstance.getSpectacolRepository().findById(((Spectacol) taskDescription).getID_spectacol());
            optionalVar.ifPresent(current -> serviceInstance.getSpectacolRepository().delete(current));
            serviceInstance.getSpectacolRepository().save((Spectacol) taskDescription);
            result = "changes saved";
        } else if (taskDescription instanceof Vanzare) {
            Optional<Vanzare> optionalVar = serviceInstance.getVanzareRepository().findById(((Vanzare) taskDescription).getId());
            optionalVar.ifPresent(current -> serviceInstance.getVanzareRepository().delete(current));
            serviceInstance.getVanzareRepository().save((Vanzare) taskDescription);
            result = "changes saved";
        } else if (taskDescription instanceof VanzareLoc) {
            Optional<VanzareLoc> optionalVar = serviceInstance.getVanzareLocRepository().findById(((VanzareLoc) taskDescription).getId());
            optionalVar.ifPresent(current -> serviceInstance.getVanzareLocRepository().delete(current));
            serviceInstance.getVanzareLocRepository().save((VanzareLoc) taskDescription);
            result = "changes saved";
        } else if (taskDescription instanceof String) {
            switch ((String) taskDescription) {
                case "Spectacol":
                    result = serviceInstance.getSpectacolRepository().findAll();
                    break;
                case "Vanzare":
                    result = serviceInstance.getVanzareRepository().findAll();
                    break;
                case "VanzareLoc":
                    result = serviceInstance.getVanzareLocRepository().findAll();
                case "Sala":
                    result = serviceInstance.getSalaRepository().findAll();
                default:
                    result = "Invalid Command";
            }
        } else if (taskDescription instanceof Integer) {
            //get
        }
    }

}
