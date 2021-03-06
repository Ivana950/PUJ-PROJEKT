package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;

public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception{

        /*
        Table.create(Osoba.class);
        Table.create(Žanr.class);
        Table.create(Filmovi.class);
        Table.create(Favoriti.class);
         */

        Main.primaryStage = primaryStage;
        Main.showWindow(
                getClass(),
                "../view/Login.fxml",
                "Prijavite se na sustav", 610, 270);
    }

    public static void showWindow(Class<?> windowClass, String viewName, String title, int w, int h) throws IOException {
        Parent root = FXMLLoader.load(windowClass.getResource(viewName));
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root, w, h));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}