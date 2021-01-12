package com.ppdClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketService {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;


        // stub
        public void startConnection(String ip, int port) throws IOException {
            try {
                clientSocket = new Socket(ip, port);
            } catch (IOException e) {
                e.printStackTrace();
            }

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }

        public String sendMessage(String msg) throws IOException {
            out.println(msg);
            String resp = in.readLine();
            return resp;
        }

        public void stopConnection() throws IOException {
            in.close();
            out.close();
            clientSocket.close();
        }
}
