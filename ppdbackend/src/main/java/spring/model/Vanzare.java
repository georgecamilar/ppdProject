package spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "vanzare")
public class Vanzare implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @Column(name = "data_vanzare")
    private Date data_vanzare;

    @ManyToOne
    @JoinColumn(name = "idSpectacol")
    private Spectacol spectacol;

    public Vanzare() {
    }

    public Vanzare(Integer id, Date data_vanzare, Spectacol spectacol) {
        this.id = id;
        this.data_vanzare = data_vanzare;
        this.spectacol = spectacol;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData_vanzare() {
        return data_vanzare;
    }

    public void setData_vanzare(Date data_vanzare) {
        this.data_vanzare = data_vanzare;
    }

    public Spectacol getSpectacol() {
        return spectacol;
    }

    public void setSpectacol(Spectacol spectacol) {
        this.spectacol = spectacol;
    }

}
