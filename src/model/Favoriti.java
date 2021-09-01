package model;

public class Favoriti extends Table{

    @Entity(type = "INTEGER", size = 11, primary = true)
    int id;

    @ForeignKey(table = "Filmovi", attribute = "id")
    @Entity(type = "INTEGER", size = 11)
    int idFilm;

    @ForeignKey(table = "Osoba", attribute = "id")
    @Entity(type = "INTEGER", size = 11)
    int idOsoba;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getIdOsoba() {
        return idOsoba;
    }

    public void setIdOsoba(int idOsoba) {
        this.idOsoba = idOsoba;
    }
}
