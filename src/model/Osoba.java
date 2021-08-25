package model;

public class Osoba extends Table {
    @Entity(type = "INTEGER", size = 50, primary = true)
    int id;

    @Entity(type="VARCHAR", size=50, isnull = false)
    String ime;

    @Entity(type = "VARCHAR", size=50, isnull = false)
    String prezime;

    @Entity(type="VARCHAR", size = 50, isnull = false)
    String korisnickoIme;

    @Entity(type = "VARCHAR", size = 100, isnull = false)
    String lozinka;

    @Entity(type = "VARCHAR", size = 100, isnull = false)
    String uloga;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
}
