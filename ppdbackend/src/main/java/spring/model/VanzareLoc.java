package spring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "vanzare_loc")
public class VanzareLoc implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idVanzare")
    private Vanzare idVanzare;

    @Column(name = "NrLoc")
    private Integer nrLoc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public VanzareLoc() {
    }

    public VanzareLoc(Vanzare idVanzare, Integer nrLoc) {
        this.idVanzare = idVanzare;
        this.nrLoc = nrLoc;
    }

    public Vanzare getIdVanzare() {
        return idVanzare;
    }

    public void setIdVanzare(Vanzare idVanzare) {
        this.idVanzare = idVanzare;
    }

    public Integer getNrLoc() {
        return nrLoc;
    }

    public void setNrLoc(Integer nrLoc) {
        this.nrLoc = nrLoc;
    }
}
