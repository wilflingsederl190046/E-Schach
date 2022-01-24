package com.example.project_echess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Menue.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        MenueController controller_1 = (MenueController) fxmlLoader.getController();

        controller_1.setPrimaryStage(stage);
        Scene scene = new Scene(root);
        stage.setTitle("Chess with two devices");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}