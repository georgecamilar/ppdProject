package spring.repos;
import spring.model.Spectacol;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SpectacolRepository {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/mm/yyyy");

    public List<Spectacol> findAll() {
        List <Spectacol> spectacole = new LinkedList<>();
        try {
            File file = new File("Spectacol.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] fields = data.split(" ");

                Spectacol spectacol = new Spectacol();

                // parse fields
                spectacol.setId(Integer.parseInt(fields[0]));
                spectacol.setDataSpectacol(dateFormatter.parse(fields[1]));
                spectacol.setTitlu(fields[2]);
                spectacol.setPretBilet(new BigDecimal(Double.parseDouble((fields[3]))));

                spectacole.add(spectacol);
            }
            myReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return spectacole;
    }

    public Spectacol findById(Integer id) {
        List <Spectacol> spectacole = this.findAll();

        for (Spectacol spectacol: spectacole) {
            if (spectacol.getId().equals(id)) {
                return spectacol;
            }
        }

        return null;
    }
}
