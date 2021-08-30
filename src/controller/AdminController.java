package controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import javafx.fxml.FXML;
import main.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.*;



public class AdminController {
    @FXML
    Button korisniciBtn;
    @FXML
    Button filmoviBtn;


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



}