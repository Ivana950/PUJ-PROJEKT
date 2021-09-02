package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import main.Main;
import model.Favoriti;
import model.Filmovi;

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
    Label uspjehLbl;
    @FXML
    TableView<Filmovi> tableView;
    @FXML
    TableColumn<Filmovi, String> nazivTblCol;
    @FXML
    TableColumn<Filmovi, String> trajanjeFilmaTblCol;
    @FXML
    TableColumn<Filmovi, String>žanrTblCol;

    Collection<Filmovi> filmovi;
    Collection<Favoriti> favoriti;

    {
        try {
            filmovi = (Collection<Filmovi>) Filmovi.list(Filmovi.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    {
        try {
            favoriti = (Collection<Favoriti>) Favoriti.list(Favoriti.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loggedUserLbl.setText(
                LoginController.loggedInOsoba.getIme() +
                        " " +
                        LoginController.loggedInOsoba.getPrezime());

        uspjehLbl.setVisible(false);

        this.nazivTblCol.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        this.trajanjeFilmaTblCol.setCellValueFactory(new PropertyValueFactory<>("trajanjeFilma"));
        this.žanrTblCol.setCellValueFactory(new PropertyValueFactory<>("idŽanr"));

        try {
            this.tableView.getItems().setAll((Collection<? extends Filmovi>) Filmovi.list(Filmovi.class));
        } catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke");
        }

        this.populateTableView();
    }

    private void populateTableView(){
        try {
            this.tableView.getItems().setAll((Collection<? extends Filmovi>) Filmovi.list(Filmovi.class));
            this.tableView.setEditable(true);
        } catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke");
        }
    }


    public void addToFavorites(ActionEvent e) throws Exception {
        Filmovi f = this.tableView.getSelectionModel().getSelectedItem();
        if(f != null)
        {
            favoriti = (Collection<Favoriti>) Favoriti.list(Favoriti.class);
            favoriti.removeIf(favoriti -> favoriti.getIdFilm() == f.getId());

            Favoriti t = new Favoriti();
            t.setIdFilm(f.getId());
            t.setIdOsoba(LoginController.loggedInOsoba.getId());

            t.save();
            favoriti.add(t);

            uspjehLbl.setVisible(true);
        }
    }

    @FXML
    public void openFavorites(ActionEvent evt) throws IOException {
        Main.showWindow(
                getClass(),
                "../view/Favoriti.fxml",
                "Favoriti", 548, 400
        );
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

    public void sortirajPoŽanru(ActionEvent ev) throws IOException{

    }


}
