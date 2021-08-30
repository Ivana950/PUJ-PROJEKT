package model;

public class Žanr {
    @Entity(type = "INTEGER", size = 11, primary = true)
    int id;

    @Entity(type="VARCHAR", size=50, isnull = false)
    String naziv;

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
