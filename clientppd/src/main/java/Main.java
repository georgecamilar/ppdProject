import connection.ClientConnection;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        ClientConnection client = new ClientConnection("localhost", 8081);

    }
}
