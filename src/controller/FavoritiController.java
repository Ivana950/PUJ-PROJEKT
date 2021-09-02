package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.Main;
import model.Database;
import model.Favoriti;
import model.Filmovi;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class FavoritiController implements Initializable {

    ArrayList favoriti = new ArrayList();

    @FXML
    Label loggedUserLbl;
    @FXML
    Button goBackBtn;
    @FXML
    Button deleteBtn;
    @FXML
    TableView<Filmovi> tableView;
    @FXML
    TableColumn<Filmovi, String> nazivTblCol;
    @FXML
    TableColumn<Filmovi, String>žanrTblCol;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.loggedUserLbl.setText(
                LoginController.loggedInOsoba.getIme() +
                        " " +
                        LoginController.loggedInOsoba.getPrezime());

        dohvatiOmiljeneFilmove();
    }

    private void dohvatiOmiljeneFilmove(){
        this.nazivTblCol.setCellValueFactory(new PropertyValueFactory<>("naziv"));;
        this.žanrTblCol.setCellValueFactory(new PropertyValueFactory<>("idŽanr"));

        try {

            String sql = "SELECT * FROM favoriti WHERE idOsoba=?";
            PreparedStatement query = Database.CONNECTION.prepareStatement(sql);
            query.setInt(1,LoginController.loggedInOsoba.getId());
            ResultSet rs = query.executeQuery();
            while(rs.next()) {

                Filmovi film = (Filmovi) Filmovi.get(Filmovi.class, rs.getInt(2));
                favoriti.add(film);

            }

            this.tableView.getItems().setAll(favoriti);
        }catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke");
        }
    }


    public void nazad(ActionEvent ev) throws IOException {
        Main.showWindow(
                getClass(),
                "../view/Korisnik.fxml",
                "Dobrodošli!", 600, 400);

    }

    public void deleteFavorites(ActionEvent e) throws Exception {
        Filmovi f = this.tableView.getSelectionModel().getSelectedItem();
        if(f != null){
            String sql = "DELETE FROM Favoriti WHERE idFilm=? AND idOsoba=?";
            PreparedStatement stmt = Database.CONNECTION.prepareStatement(sql);
            stmt.setInt(1, f.getId());
            stmt.setInt(2, f.getId());
            stmt.executeUpdate();

            favoriti.remove(f);

            this.tableView.getSelectionModel().clearSelection();
            this.tableView.getItems().clear();
            this.tableView.getItems().addAll(favoriti);

        }
    }

}




