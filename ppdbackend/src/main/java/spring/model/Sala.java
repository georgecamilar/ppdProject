package spring.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name = "sala")
public class Sala implements Serializable {
    @Column(name = "nr_locuri")
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
