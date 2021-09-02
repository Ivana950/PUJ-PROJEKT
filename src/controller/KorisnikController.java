package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.Main;
import model.Favoriti;
import model.Filmovi;
import model.Žanr;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class KorisnikController implements Initializable {
    @FXML
    Label loggedUserLbl;
    @FXML
    Button favoritiBtn;
    @FXML
    Button odjavaBtn;
    @FXML
    Button dodajfavoritaBtn;

    @FXML
    TableView<Filmovi> tableView;
    @FXML
    TableColumn<Filmovi, String> nazivTblCol;
    @FXML
    TableColumn<Filmovi, String> trajanjeFilmaTblCol;
    @FXML
    TableColumn<Filmovi, String>žanrTblCol;

    Collection<Filmovi> filmovi;

    static ObservableList<Filmovi> list = FXCollections.observableArrayList();

    Collection<Favoriti> favoriti;

    static ObservableList<Favoriti> lista = FXCollections.observableArrayList();


    @FXML
    public void openFavorites(ActionEvent evt) throws IOException {
        Main.showWindow(
                getClass(),
                "../view/Favoriti.fxml",
                "Favoriti", 548, 400
        );
    }

    public void addToFavorites(ActionEvent e) throws Exception {
        Filmovi f = this.tableView.getSelectionModel().getSelectedItem();
        if(f != null)
        {
            favoriti = (Collection<Favoriti>) Favoriti.list(Favoriti.class);
            favoriti.removeIf(favoriti -> favoriti.getIdFilm() == f.getId());

            Favoriti t = new Favoriti();
            t.setIdFilm(t.getId());
            t.setIdOsoba(t.getIdOsoba());

            t.save();
            favoriti.add(t);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loggedUserLbl.setText(
                LoginController.loggedInOsoba.getIme() +
                        " " +
                        LoginController.loggedInOsoba.getPrezime());

    dohvatiFilmove();
    }

    private void dohvatiFilmove () {
        this.properties();
        try {

            filmovi = (Collection<Filmovi>) Filmovi.list(Filmovi.class);
            this.tableView.getItems().setAll(filmovi);
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.tableView.setEditable(true);
    }

    private void properties(){
        this.nazivTblCol.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        this.trajanjeFilmaTblCol.setCellValueFactory(new PropertyValueFactory<>("trajanjeFilma"));
        this.žanrTblCol.setCellValueFactory(new PropertyValueFactory<>("idŽanr"));

    }



    @FXML
    public void logout(ActionEvent ev) throws IOException {
        LoginController.loggedInOsoba = null;
        Main.showWindow(
                getClass(),
                "../view/Login.fxml",
                "Login to system", 610, 270
        );
    }
}
