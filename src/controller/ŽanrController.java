package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import main.Main;
import model.Database;
import model.Žanr;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.ResourceBundle;

public class ŽanrController implements Initializable {
    @FXML
    Label loggedUserLbl;
    @FXML
    Button odjava;
    @FXML
    Button addŽanrBtn;
    @FXML
    Button deleteŽanrBtn;
    @FXML
    Button logoutBtn;
    @FXML
    Button goBackBtn;
    @FXML
    TextField naziv;
    @FXML
    TableView<Žanr> tableView;
    @FXML
    TableColumn<Žanr, String> idTblCol;
    @FXML
    TableColumn<Žanr, String> nazivTblCol;

    Collection<Žanr> žanrovi;

    {
        try {
            žanrovi = (Collection<Žanr>) Žanr.list(Žanr.class);
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


        this.idTblCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.nazivTblCol.setCellValueFactory(new PropertyValueFactory<>("naziv"));


        try {
            this.tableView.getItems().setAll((Collection<? extends Žanr>) Žanr.list(Žanr.class));
        } catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke");
        }

        this.nazivTblCol.setEditable(true);
        this.nazivTblCol.setCellFactory(TextFieldTableCell.forTableColumn());

        this.populateTableView();

    }

    @FXML
    public void addŽanr (ActionEvent e) throws Exception{
        if(!this.naziv.getText().equals(""))

        {

            Žanr ž = new Žanr();
            ž.setNaziv(this.naziv.getText());



            ž.save();
            žanrovi.add(ž);

            this.populateTableView();

            this.naziv.setText("");
        }

    }



    public void deleteŽanr(ActionEvent ev) throws Exception {

        Žanr o = tableView.getSelectionModel().getSelectedItem();

        try{
            String sql = "DELETE FROM Žanr WHERE id = ?";
            PreparedStatement query = Database.CONNECTION.prepareStatement(sql);
            query.setInt(1, o.getId());
            query.executeUpdate();

            o.delete();
            žanrovi.remove(o);

            this.populateTableView();
        }
        catch (Exception e) {
            System.out.println("Nismo uspjeli izbrisati film");

        }
    }


    @FXML
    public void editNazivToDatabase(TableColumn.CellEditEvent<Žanr, String> evt) throws Exception {
        Žanr o = evt.getRowValue();
        o.setNaziv(evt.getNewValue());
        o.update();
    }


    private void populateTableView(){
        try {
            this.tableView.getItems().setAll((Collection<? extends Žanr>) Žanr.list(Žanr.class));
            this.tableView.setEditable(true);
        } catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke");
        }
    }

    public void nazad(ActionEvent ev) throws IOException {
        Main.showWindow(
                getClass(),
                "../view/Admin.fxml",
                "Dobrodošli u administraciju!", 618, 404);

    }


    @FXML
    public void logout(ActionEvent ev) throws IOException {
        LoginController.loggedInOsoba = null;
        Main.showWindow(
                getClass(),
                "../view/Admin.fxml",
                "Prijava", 610, 270
        );
    }
}
