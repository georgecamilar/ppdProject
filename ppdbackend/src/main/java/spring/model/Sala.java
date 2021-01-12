package spring.model;
import java.io.Serializable;

public class Sala implements Serializable {
    private Integer nr_locuri;

    public Sala(Integer nr_locuri) {
        this.nr_locuri = nr_locuri;
    }
    public Sala() { }

    public void setNrLocuri(Integer nr_locuri) {
        this.nr_locuri = nr_locuri;
    }
    public Integer getNrLocuri() {
        return this.nr_locuri;
    }
}
