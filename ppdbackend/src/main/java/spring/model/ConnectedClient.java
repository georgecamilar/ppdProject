package spring.model;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ConnectedClient {
    public Socket client;
    public ObjectOutputStream stream;

    public ConnectedClient(Socket client, ObjectOutputStream stream) {
        this.client = client;
        this.stream = stream;
    }
}
