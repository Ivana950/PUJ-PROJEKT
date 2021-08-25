package model;

public class Filmovi extends Table {
    @Entity(type = "INTEGER", size = 32, primary = true)
    int id;

    @Entity(type="VARCHAR", size=50, isnull = false)
    String naziv;

    @Entity(type="VARCHAR", size=50, isnull = false)
    String žanr;

    @Entity(type="VARCHAR", size=50, isnull = false)
    String trajanjeFilma;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getŽanr() {
        return žanr;
    }

    public void setŽanr(String žanr) {
        this.žanr = žanr;
    }

    public String getTrajanjeFilma() {
        return trajanjeFilma;
    }

    public void setTrajanjeFilma(String trajanjeFilma) {
        this.trajanjeFilma = trajanjeFilma;
    }
}
