package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Table;
import model.Osoba;
import model.Filmovi;
import model.Favoriti;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*
        Table.create(Osoba.class);
        Table.create(Filmovi.class);
        Table.create(Favoriti.class);

        Filmovi film = new Filmovi();
        film.setNaziv("Spiderman");
        film.setŽanr("Fantazija");
        film.setTrajanjeFilma("2h 10min");
        film.save();

        Filmovi f = (Filmovi) Filmovi.get(Filmovi.class, 1);
        f.setŽanr("Sci-Fi");
        f.update();

        Filmovi f = (Filmovi) Filmovi.get(Filmovi.class, 1);
        f.delete();
         */

        Main.primaryStage = primaryStage;
        Main.showWindow(
                getClass(),
                "../view/login.fxml",
                "Prijavite se na sustav", 600, 215);
    }

    public static void showWindow(Class windowClass, String viewName, String title, int w, int h) throws IOException {
        Parent root = FXMLLoader.load(windowClass.getResource(viewName));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, w, h));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}