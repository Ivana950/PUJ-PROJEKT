package model;

public class Filmovi extends Table {
    @Entity(type = "INTEGER", size = 32, primary = true)
    int id;

    @Entity(type="VARCHAR", size=50, isnull = false)
    String naziv;


    @Entity(type="VARCHAR", size=50, isnull = false)
    String trajanjeFilma;

    @ForeignKey(table = "Žanr", attribute = "id")
    @Entity(type = "INTEGER", size = 11)
    int idŽanr;


    public int getIdŽanr() {
        return idŽanr;
    }

    public void setIdŽanr(int idŽanr) {
        this.idŽanr = idŽanr;
    }

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


    public String getTrajanjeFilma() {
        return trajanjeFilma;
    }

    public void setTrajanjeFilma(String trajanjeFilma) {
        this.trajanjeFilma = trajanjeFilma;
    }
}
