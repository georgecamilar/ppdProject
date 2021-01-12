import spring.socketserver.Server;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("120000 miliseconds(long constructor value) = 2 min");
        System.err.println("This is only a test value of 1200 miliseconds");
        Server server = new Server("5000");


        server.run();
    }
}