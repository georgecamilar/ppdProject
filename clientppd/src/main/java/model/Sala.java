package model;
import java.io.Serializable;

public class Sala implements Serializable {
    private Integer nr_locuri;

    public Sala(Integer nr_locuri) {
        this.nr_locuri = nr_locuri;
    }

    public void setNr_locuri(Integer nr_locuri) {
        this.nr_locuri = nr_locuri;
    }

    public Integer getNr_locuri() {
        return this.nr_locuri;
    }
}
