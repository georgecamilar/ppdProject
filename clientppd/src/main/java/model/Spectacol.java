package model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Spectacol implements Serializable {
    private int id;
    private Integer ID_spectacol;
    private Date data_spectacol;
    private String titlu;
    private BigDecimal pret_bilet;

    public Spectacol(Integer ID_spectacol, Date data_spectacol, String titlu, BigDecimal pret_bilet) {
        this.ID_spectacol = ID_spectacol;
        this.data_spectacol = data_spectacol;
        this.titlu = titlu;
        this.pret_bilet = pret_bilet;
    }
    public Spectacol(){

    }

    public Integer getID_spectacol() {
        return ID_spectacol;
    }

    public void setID_spectacol(Integer ID_spectacol) {
        this.ID_spectacol = ID_spectacol;
    }

    public Date getData_spectacol() {
        return data_spectacol;
    }

    public void setData_spectacol(Date data_spectacol) {
        this.data_spectacol = data_spectacol;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public BigDecimal getPret_bilet() {
        return pret_bilet;
    }

    public void setPret_bilet(BigDecimal pret_bilet) {
        this.pret_bilet = pret_bilet;
    }
}
