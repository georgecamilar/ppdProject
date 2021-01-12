package model;

import java.io.Serializable;

public class VanzareLoc implements Serializable {

    private Integer id;

    private Vanzare idVanzare;
    private Integer nrLoc;

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
