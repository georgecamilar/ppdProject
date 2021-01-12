package connection;

import model.Sala;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConnection {
    private Socket connection;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    public ClientConnection(String host, Integer port) throws IOException {
        connection = new Socket(host, port);
        outputStream = new ObjectOutputStream(connection.getOutputStream());
        inputStream = new ObjectInputStream(connection.getInputStream());
    }

    public void getSala() {
        try {
            outputStream.writeObject("Sala");

            Sala sala = (Sala) inputStream.readObject();
            System.out.println(sala.getNr_locuri());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }
}
