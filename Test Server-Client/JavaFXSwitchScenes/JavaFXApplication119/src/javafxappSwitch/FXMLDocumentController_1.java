/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxappSwitch;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Thomas
 */
public class FXMLDocumentController_1 implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label label;
    
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Scene scene = stage.getScene();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument_2.fxml"));
            Parent root = (Parent) loader.load();
            scene.setRoot(root);
            System.out.println("d2set");
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController_1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void setPrimaryStage(Stage stage) {
        this.stage = stage;
    }
    
}
