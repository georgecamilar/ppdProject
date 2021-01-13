package spring.repos;

import spring.model.Vanzare;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class VanzareRepository {
    String datePattern = "dd/mm/yyyy";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(datePattern);

    public void saveToDisk(List<Vanzare> vanzari) {
        try {
            FileWriter myWriter = new FileWriter("Vanzare.txt");
            for (Vanzare vanzare : vanzari) {
                List<String> lista_locuri = new LinkedList<String>();
                for (Integer i : vanzare.getListaLocuri()) {
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

    public List<Vanzare> findAll() {
        try {
            return Files.readAllLines(Paths.get("Vanzare.txt")).stream().map(line -> {
                String[] fields = line.split(" ");
                List<Integer> takenSeats = new ArrayList<>();
                for (int index = 3; index < fields.length; index++) {
                    takenSeats.add(Integer.parseInt(fields[index]));
                }

                try {
                    return new Vanzare(UUID.fromString(fields[0]), simpleDateFormat.parse(fields[1]), Integer.parseInt(fields[2]), takenSeats);
                } catch (ParseException e) {
                    e.printStackTrace();
                    return new Vanzare();
                }
            }).collect(Collectors.toList());
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return new ArrayList<>();
    }
}
