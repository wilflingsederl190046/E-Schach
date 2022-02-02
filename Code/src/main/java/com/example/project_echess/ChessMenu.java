package com.example.project_echess;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ChessMenu extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root= fxmlLoader.load();
        MenuController controller_1 = (MenuController) fxmlLoader.getController();

        controller_1.setPrimaryStage(stage);
        Scene scene = new Scene(root);
        stage.setTitle("E-Schach");
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
