import connection.ClientConnection;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClientConnection client = new ClientConnection("localhost", 8081);
        client.getSala();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
