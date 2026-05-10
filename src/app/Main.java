package app;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Doaa Ayman Oukal 
 */
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        try {

            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(
                    getClass().getResource("/views/main.fxml")
            );

            Parent root = loader.load();

            Scene scene = new Scene(root);

            scene.getStylesheets().add(
                    getClass().getResource("/styles/style.css").toExternalForm()
            );

            stage.setTitle("Enrollment System");
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}