package com.ppdClient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PpdClient {
    SocketService socketService = new SocketService();
    int numar_locuri = 100;

    public void start() {
        String inputChar = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while(!inputChar.equals("2") || !inputChar.equals("1")) {
            displayOptions();
            try {
                inputChar = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (inputChar.equals("1"))
                connectAndSend();

            if(inputChar.equals("2"))
                break;
        }
    }

    public void connectAndSend() {
        // connect
        // ...


        while(true){
            try {

                //generate inputs
                int rand = this.getRandomNumber(1, 100);
                String randomNrs = this.getRandomNumbersString(rand, 1, 100);
                System.out.println(rand);
                System.out.println(randomNrs);

                //send inputs...


                //wait for answer...


                //go next..
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Integer getRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public String getRandomNumbersString(int count, int min, int max) {
        List<String> result = new ArrayList<String>();

        for(int i = 1; i <= count; i++) {
            result.add(this.getRandomNumber(min, max).toString());
        }

        return String.join(",", result);
    }

    public void displayOptions() {
        System.out.println("1. Start");
        System.out.println("2. Exit");
        System.out.print("Option: ");
    }
}
