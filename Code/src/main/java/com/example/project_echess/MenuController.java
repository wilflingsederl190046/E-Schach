package com.example.project_echess;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

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
        if(!username.getText().trim().equals("") && !comboBoxColor.getValue().equals("color")) {
            new ChessClient(username.getText(), comboBoxColor.toString(), hostName, portNumber).activate();

            try {
                stage.setMinHeight(985);
                stage.setMinWidth(1275);
                stage.setResizable(false);
                stage.setTitle(username.getText() + " " + comboBoxColor.getValue().toUpperCase());
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent windowEvent) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Leave Confirmation");
                        alert.setHeaderText("Are you sure you want to quit this session and get back to the menu?");
                        Optional<ButtonType> result = alert.showAndWait();

                        if(result.get() == ButtonType.OK){
                            try {
                                stage.setMinHeight(390);
                                stage.setMaxHeight(390);
                                stage.setMinWidth(610);
                                stage.setMaxWidth(610);
                                stage.setTitle("E-Schach");
                                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                                    @Override
                                    public void handle(WindowEvent windowEvent) {
                                        System.exit(0);
                                    }
                                });
                                Scene scene = stage.getScene();
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));

                                Parent root = (Parent) loader.load();
                                scene.setRoot(root);

                                MenuController controller = (MenuController) loader.getController();
                                controller.setPrimaryStage(stage);
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        windowEvent.consume();
                    }
                });
                Scene scene = stage.getScene();
                FXMLLoader loader = null;
                if(comboBoxColor.getValue().equals("white")) {
                    loader = new FXMLLoader(getClass().getResource("Chessboard_whiteSide.fxml"));
                } else {
                    loader = new FXMLLoader(getClass().getResource("Chessboard_blackSide.fxml"));
                }

                Parent root = (Parent) loader.load();
                scene.setRoot(root);

                ChessboardController controller = (ChessboardController) loader.getController();
                controller.setPrimaryStage(stage);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("no information error");
            alert.setHeaderText("Please set a username and choose a color and try again!");
            alert.showAndWait();
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
