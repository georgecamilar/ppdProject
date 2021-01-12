package spring.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Spectacol implements Serializable {
    private Integer id;
    private Date data_spectacol;
    private String titlu;
    private BigDecimal pret_bilet;

    public Spectacol(Integer id, Date data_spectacol, String titlu, BigDecimal pret_bilet) {
        this.id = id;
        this.data_spectacol = data_spectacol;
        this.titlu = titlu;
        this.pret_bilet = pret_bilet;
    }

    public Spectacol() { }

    public Integer getId() { return this.id; }
    public void setId(Integer id) {
        this.id = id;
    }
    public Date getDataSpectacol() {
        return data_spectacol;
    }
    public void setDataSpectacol(Date data_spectacol) {
        this.data_spectacol = data_spectacol;
    }
    public String getTitlu() {
        return titlu;
    }
    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }
    public BigDecimal getPretBilet() {
        return pret_bilet;
    }
    public void setPretBilet(BigDecimal pret_bilet) {
        this.pret_bilet = pret_bilet;
    }
}
