package com.example.project_echess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenueController implements Initializable {
    public enum Color {
        B {
            @Override
            public String toString() {
                return "black";
            }
        },
        W {
            @Override
            public String toString() {
                return "white";
                }
            }
    }

    @FXML
    private Button button;

    @FXML
    private ComboBox<Color> comboBoxColor;

    @FXML
    private TextField username;

    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxColor = new ComboBox<>();
        ObservableList<Color> olColor = FXCollections.observableArrayList();
        olColor.addAll(Color.values());
        comboBoxColor.setItems(olColor);
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        if(!username.getText().equals("") && !comboBoxColor.equals("black") && !comboBoxColor.equals("weiß")) {
            try {
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

    public void setPrimaryStage(Stage stage) {
        this.stage = stage;
    }
}
