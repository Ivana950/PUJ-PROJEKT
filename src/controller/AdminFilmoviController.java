package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.Main;
import model.Database;
import model.Filmovi;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.ResourceBundle;

public class AdminFilmoviController implements Initializable {
    @FXML
    TextField naziv;
    @FXML
    TextField trajanjeFilma;
    @FXML
    TextField žanr;
    @FXML
    Button addMovieBtn;
    @FXML
    Button deleteMovieBtn;
    @FXML
    Label loggedUserLbl;
    @FXML
    Button odjava;

    @FXML
    TableView<Filmovi> tableView;
    @FXML
    TableColumn<Filmovi, String> nazivTblCol;
    @FXML
    TableColumn<Filmovi, String> trajanjeFilmaTblCol;
    @FXML
    TableColumn<Filmovi, String>žanrTblCol;

    Collection<Filmovi> filmovi;

    {
        try {
            filmovi = (Collection<Filmovi>) Filmovi.list(Filmovi.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addMovieToDatabase (ActionEvent e) throws Exception{
        if(!this.naziv.getText().equals("")&&
                !this.trajanjeFilma.getText().equals("")&&
                !this.žanr.getText().equals(""))
        {
            Filmovi t = new Filmovi();
            t.setNaziv(this.naziv.getText());
            t.setTrajanjeFilma(this.trajanjeFilma.getText());


            t.save();
            filmovi.add(t);
            this.populateTableView();

            this.naziv.setText("");
            this.trajanjeFilma.setText("");


        }


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.loggedUserLbl.setText(
                LoginController.loggedInOsoba.getIme() +
                        " " +
                        LoginController.loggedInOsoba.getPrezime());


        this.nazivTblCol.setCellValueFactory(new PropertyValueFactory<>("naziv"));
        this.trajanjeFilmaTblCol.setCellValueFactory(new PropertyValueFactory<>("trajanjeFilma"));
        this.žanrTblCol.setCellValueFactory(new PropertyValueFactory<>("žanr"));


        this.nazivTblCol.setEditable(true);
        this.trajanjeFilmaTblCol.setCellFactory(TextFieldTableCell.forTableColumn());
        this.žanrTblCol.setEditable(true);

        this.populateTableView();

    }


    public void deleteMovieFromDatabase(ActionEvent ev) throws Exception {

        Filmovi o = tableView.getSelectionModel().getSelectedItem();

        try{
            String sql = "DELETE FROM favoriti WHERE idFilm = ?";
            PreparedStatement query = Database.CONNECTION.prepareStatement(sql);
            query.setInt(1, o.getId());
            query.executeUpdate();

            o.delete();
            filmovi.remove(o);

            this.populateTableView();
        }
        catch (Exception e) {
            System.out.println("Nismo uspjeli izbrisati film");

        }
    }


    @FXML
    public void editNazivToDatabase(TableColumn.CellEditEvent<Filmovi, String> evt) throws Exception {
        Filmovi o = evt.getRowValue();
        o.setNaziv(evt.getNewValue());
        o.update();
    }

    @FXML
    public void editTrajanjeFilmaToDatabase(TableColumn.CellEditEvent<Filmovi, String> evt) throws Exception {
        Filmovi o = evt.getRowValue();
        o.setTrajanjeFilma(evt.getNewValue());
        o.update();
    }



    private void populateTableView(){
        try {
            this.tableView.getItems().setAll((Collection<? extends Filmovi>) Filmovi.list(Filmovi.class));
            this.tableView.setEditable(true);
        } catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke");
        }
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
