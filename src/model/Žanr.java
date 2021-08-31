package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Žanr extends Table{
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

    public static Žanr dohvatiŽanr (String naziv) throws Exception {
        String sql = "SELECT id FROM žanr WHERE naziv=?";
        PreparedStatement query = Database.CONNECTION.prepareStatement(sql);
        query.setString(1, naziv);

        ResultSet rs = query.executeQuery();

        if (rs.next()) {
            return (Žanr) Žanr.get(Žanr.class, rs.getInt(1));
        } else {
            return null;
        }

    }


}

