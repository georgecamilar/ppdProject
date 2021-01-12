package spring.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Vanzare implements Serializable {
    private UUID id;
    private Date data_vanzare;
    private Integer spectacol_id;
    private List<Integer> lista_locuri = new LinkedList<Integer>();

    public Vanzare(UUID id, Date data_vanzare, Integer spectacol_id, List<Integer> lista_locuri) {
        this.id = id;
        this.data_vanzare = data_vanzare;
        this.spectacol_id = spectacol_id;
        this.lista_locuri = lista_locuri;
    }

    public Vanzare() { }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public Date getDataVanzare() {
        return data_vanzare;
    }
    public void setDataVanzare(Date data_vanzare) {
        this.data_vanzare = data_vanzare;
    }
    public Integer getSpectacolId() {
        return this.spectacol_id;
    }
    public void setSpectacolId(int spectacol_id) {
        this.spectacol_id = spectacol_id;
    }
    public void setListaLocuri(List<Integer> lista_locuri) { this.lista_locuri = lista_locuri; }
    public List<Integer> getListaLocuri(){ return this.lista_locuri; }
}
