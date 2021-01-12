package spring.repos;

import spring.model.Sala;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SalaRepository {
    public List<Sala> findAll() {
        List <Sala> sali = new LinkedList<>();
        try {
            File file = new File("Sala.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                sali.add(new Sala(Integer.parseInt(data)));
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sali;
    }
}
