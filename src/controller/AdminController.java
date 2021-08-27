package controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import main.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Database;
import model.Osoba;
import java.net.URL;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.ResourceBundle;


public class AdminController /*implements Initializable*/ {
    @FXML
    TextField ime;
    @FXML
    TextField prezime;
    @FXML
    TextField korIme;
    @FXML
    PasswordField lozinka;
    @FXML
    TextField uloga;
    @FXML
    Button addUserBtn;
    @FXML
    Button deleteUserBtn;
    @FXML
    Label loggedUserLbl;
    @FXML
    Button odjava;
    @FXML
    Button korisniciBtn;
    @FXML
    Button filmoviBtn;
/*
    @FXML
    TableView<Osoba> tableView;

    @FXML
    TableColumn<Osoba, String> imeTblCol;

    @FXML
    TableColumn<Osoba, String> prezimeTblCol;
    @FXML
    TableColumn<Osoba, String>ulogaTblCol;
    @FXML
    TableColumn<Osoba, String>korImeTblCol;
*/
    @FXML
    public void otvoriKorisnike(ActionEvent evt) throws IOException {
        Main.showWindow(
                getClass(),
                "../view/Admin_Korisnici.fxml",
                "Administracija", 618, 404
        );
    }

    @FXML
    public void otvoriFilmove(ActionEvent evt) throws IOException {
        Main.showWindow(
                getClass(),
                "../view/Admin_Filmovi.fxml",
                "Filmovi", 618, 404
        );
    }

    Collection<Osoba> osobe;

    {
        try {
            osobe = (Collection<Osoba>) Osoba.list(Osoba.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void addUserToDatabase (ActionEvent e) throws Exception{
        if(!this.ime.getText().equals("")&&
                !this.prezime.getText().equals("")&&
                !this.korIme.getText().equals("")&&
                !this.uloga.getText().equals("")&&
                !this.lozinka.getText().equals(""))
        {
            Osoba t = new Osoba();
            t.setIme(this.ime.getText());
            t.setPrezime(this.prezime.getText());
            t.setLozinka(this.lozinka.getText());
            t.setKorisnickoIme(this.korIme.getText());
            t.setUloga(this.uloga.getText());
            t.save();
            osobe.add(t);
            /*this.populateTableView();*/

            this.ime.setText("");
            this.prezime.setText("");
            this.lozinka.setText("");
            this.korIme.setText("");
            this.uloga.setText("");

        }


    }
/*
        this.imeTblCol.setCellValueFactory(new PropertyValueFactory<>("ime"));
        this.prezimeTblCol.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        this.ulogaTblCol.setCellValueFactory(new PropertyValueFactory<>("uloga"));
        this.korImeTblCol.setCellValueFactory(new PropertyValueFactory<>("korisnickoIme"));

        this.imeTblCol.setEditable(true);


        this.imeTblCol.setCellFactory(TextFieldTableCell.forTableColumn());

        this.prezimeTblCol.setEditable(true);


        this.prezimeTblCol.setCellFactory(TextFieldTableCell.forTableColumn());

        this.populateTableView();
 */

/*
    public void deleteUserFromDatabase(ActionEvent ev) throws Exception {

        Osoba o = tableView.getSelectionModel().getSelectedItem();

        if (o.getId() == LoginController.loggedInOsoba.getId()) {
            System.out.println("Ne mozete obrisat sami sebe");
        } else {

            String sql = "DELETE FROM favoriti WHERE idOsoba = ?";
            PreparedStatement query = Database.CONNECTION.prepareStatement(sql);
            query.setInt(1, o.getId());
            query.executeUpdate();


            o.delete();
            osobe.remove(o);

            this.populateTableView();
        }
    }


    @FXML
    public void editImeToDatabase(TableColumn.CellEditEvent<Osoba, String> evt) throws Exception {
        Osoba o = evt.getRowValue();
        o.setIme(evt.getNewValue());
        o.update();
    }

    @FXML
    public void editPrezimeToDatabase(TableColumn.CellEditEvent<Osoba, String> evt) throws Exception {
        Osoba o = evt.getRowValue();
        o.setPrezime(evt.getNewValue());
        o.update();
    }

    private void populateTableView(){
        try {
            this.tableView.getItems().setAll((Collection<? extends Osoba>) Osoba.list(Osoba.class));
            this.tableView.setEditable(true);
        } catch (Exception e) {
            System.out.println("Nismo uspjeli dohvatiti podatke");
        }
    }
*/
    @FXML
    public void logout(ActionEvent ev) throws IOException {
        LoginController.loggedInOsoba = null;
        Main.showWindow(
                getClass(),
                "../view/Login.fxml",
                "Login to system", 600, 215
        );
    }
/*
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.loggedUserLbl.setText(
                LoginController.loggedInOsoba.getIme() +
                        " " +
                        LoginController.loggedInOsoba.getPrezime());
    }
 */
}