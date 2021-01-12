package spring.repos;

import spring.model.Vanzare;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

public class VanzareRepository {
    String datePattern = "dd/mm/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);

    public void saveToDisk(List<Vanzare> vanzari) {
        try {
            FileWriter myWriter = new FileWriter("Vanzare.txt");
            for (Vanzare vanzare: vanzari) {
                List<String> lista_locuri = new LinkedList<String>();
                for (Integer i: vanzare.getListaLocuri()) {
                    lista_locuri.add(i.toString());
                }

                myWriter.write(vanzare.getId() + " " + simpleDateFormat.format(vanzare.getDataVanzare()) + " " + vanzare.getSpectacolId() + " " + String.join(" ", lista_locuri));
                myWriter.write("\n");
            }
            myWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
