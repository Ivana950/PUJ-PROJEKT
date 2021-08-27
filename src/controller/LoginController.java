package controller;

import main.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Osoba;

import java.io.IOException;

public class LoginController {

    public static Osoba loggedInOsoba;

    @FXML
    Button prijaviSeBtn;

    @FXML
    TextField korisnickoImeTxt;

    @FXML
    TextField lozinkaTxt;

    @FXML
    Label greskaLbl;

    @FXML
    Label uspjehLbl;

    @FXML
    TextField uloga;

    @FXML
    public void prijaviSe(ActionEvent ev) {
        String korisnickoIme = this.korisnickoImeTxt.getText();
        String lozinka = this.lozinkaTxt.getText();

        if (korisnickoIme.equals("") || lozinka.equals("")) {
            greskaLbl.setVisible(true);
            uspjehLbl.setVisible(false);
        } else {
            greskaLbl.setVisible(false);
            uspjehLbl.setVisible(true);

            try {
                LoginController.loggedInOsoba = Osoba.login(korisnickoIme, lozinka);
                if (LoginController.loggedInOsoba.getUloga().equals("admin")) {
                    Main.showWindow(
                            getClass(),
                            "../view/Admin.fxml",
                            "Welcome to administration", 618, 404
                    );

                } else if (LoginController.loggedInOsoba.getUloga().equals("korisnik")) {
                    Main.showWindow(getClass(),
                            "../view/Korisnik.fxml",
                            "Dobrodošli!", 785, 513);
                } else {
                    greskaLbl.setText("Unesite ispravne korisničke podatke");
                    greskaLbl.setVisible(true);
                    uspjehLbl.setVisible(false);
                }

            } catch (Exception e) {
                System.out.println("Nastala je greška " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}