package com.example.project_echess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController {

    @FXML
    private ComboBox<String> comboBoxColor = new ComboBox<>();

    @FXML
    private TextField username;

    private Stage stage;

    private final String hostName = "localhost";
    private final int portNumber = 4445;

    public void initialize() {
        ObservableList<String> olColor = FXCollections.observableArrayList();
        olColor.addAll("black", "white");
        comboBoxColor.setItems(olColor);
        comboBoxColor.setValue("color");
    }

    @FXML
    void handleStartButtonAction(ActionEvent event) {
        if(!username.getText().equals("") && !comboBoxColor.getValue().equals("color")) {

            new Client(username.getText(), comboBoxColor.toString(), hostName, portNumber).activate();

            //Socket socket;
            try {
                /*socket = new Socket("localhost", 4711);
                OutputStream out = socket.getOutputStream();
                PrintStream ps = new PrintStream(out, true);

                ps.print("menuInformation;" + username.getText() + ";" + comboBoxColor.toString());*/

                stage.setMinHeight(985);
                stage.setMinWidth(1275);
                stage.setResizable(false);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent windowEvent) {
                        System.exit(0);
                    }
                });
                Scene scene = stage.getScene();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Chessboard.fxml"));
                Parent root = (Parent) loader.load();
                scene.setRoot(root);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            //Meldung ausgeben
        }
    }

    @FXML
    void handleCloseButtonAction(ActionEvent event) {
        stage.close();
        System.exit(0);
    }



    public void setPrimaryStage(Stage stage) {
        this.stage = stage;
    }
}
