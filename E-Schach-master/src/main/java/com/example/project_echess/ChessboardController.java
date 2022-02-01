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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.WindowEvent;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ChessboardController implements Initializable {

    @FXML
    private Rectangle A1;

    @FXML
    private Rectangle A2;

    @FXML
    private Rectangle A3;

    @FXML
    private Rectangle A4;

    @FXML
    private Rectangle A5;

    @FXML
    private Rectangle A6;

    @FXML
    private Rectangle A7;

    @FXML
    private Rectangle A8;

    @FXML
    private Rectangle B1;

    @FXML
    private Rectangle B2;

    @FXML
    private Rectangle B3;

    @FXML
    private Rectangle B4;

    @FXML
    private Rectangle B5;

    @FXML
    private Rectangle B6;

    @FXML
    private Rectangle B7;

    @FXML
    private Rectangle B8;

    @FXML
    private Rectangle C1;

    @FXML
    private Rectangle C2;

    @FXML
    private Rectangle C3;

    @FXML
    private Rectangle C4;

    @FXML
    private Rectangle C5;

    @FXML
    private Rectangle C6;

    @FXML
    private Rectangle C7;

    @FXML
    private Rectangle C8;

    @FXML
    private Rectangle D1;

    @FXML
    private Rectangle D2;

    @FXML
    private Rectangle D3;

    @FXML
    private Rectangle D4;

    @FXML
    private Rectangle D5;

    @FXML
    private Rectangle D6;

    @FXML
    private Rectangle D7;

    @FXML
    private Rectangle D8;

    @FXML
    private Rectangle E1;

    @FXML
    private Rectangle E2;

    @FXML
    private Rectangle E3;

    @FXML
    private Rectangle E4;

    @FXML
    private Rectangle E5;

    @FXML
    private Rectangle E6;

    @FXML
    private Rectangle E7;

    @FXML
    private Rectangle E8;

    @FXML
    private Rectangle F1;

    @FXML
    private Rectangle F2;

    @FXML
    private Rectangle F3;

    @FXML
    private Rectangle F4;

    @FXML
    private Rectangle F5;

    @FXML
    private Rectangle F6;

    @FXML
    private Rectangle F7;

    @FXML
    private Rectangle F8;

    @FXML
    private Rectangle G1;

    @FXML
    private Rectangle G2;

    @FXML
    private Rectangle G3;

    @FXML
    private Rectangle G4;

    @FXML
    private Rectangle G5;

    @FXML
    private Rectangle G6;

    @FXML
    private Rectangle G7;

    @FXML
    private Rectangle G8;

    @FXML
    private Rectangle H1;

    @FXML
    private Rectangle H2;

    @FXML
    private Rectangle H3;

    @FXML
    private Rectangle H4;

    @FXML
    private Rectangle H5;

    @FXML
    private Rectangle H6;

    @FXML
    private Rectangle H7;

    @FXML
    private Rectangle H8;

    @FXML
    private ImageView bauerBlack1;

    @FXML
    private ImageView bauerBlack2;

    @FXML
    private ImageView bauerBlack3;

    @FXML
    private ImageView bauerBlack4;

    @FXML
    private ImageView bauerBlack5;

    @FXML
    private ImageView bauerBlack6;

    @FXML
    private ImageView bauerBlack7;

    @FXML
    private ImageView bauerBlack8;

    @FXML
    private ImageView bauerWhite1;

    @FXML
    private ImageView bauerWhite2;

    @FXML
    private ImageView bauerWhite3;

    @FXML
    private ImageView bauerWhite4;

    @FXML
    private ImageView bauerWhite5;

    @FXML
    private ImageView bauerWhite6;

    @FXML
    private ImageView bauerWhite7;

    @FXML
    private ImageView bauerWhite8;

    @FXML
    private TextField hintField;

    @FXML
    private ImageView koenigBlack;

    @FXML
    private ImageView koenigWhite;

    @FXML
    private ImageView koeniginBlack;

    @FXML
    private ImageView koeniginWhite;

    @FXML
    private ImageView laeuferBlackLeft;

    @FXML
    private ImageView laeuferBlackRight;

    @FXML
    private ImageView laeuferWhiteLeft;

    @FXML
    private ImageView laeuferWhiteRight;

    @FXML
    private TextField selectChessman;

    @FXML
    private TextField selectField;

    @FXML
    private ImageView springerBlackLeft;

    @FXML
    private ImageView springerBlackRight;

    @FXML
    private ImageView springerWhiteLeft;

    @FXML
    private ImageView springerWhiteRight;

    @FXML
    private TableView<String> tableView;

    @FXML
    private ImageView turmBlackLeft;

    @FXML
    private ImageView turmBlackRight;

    @FXML
    private ImageView turmWhiteLeft;

    @FXML
    private ImageView turmWhiteRight;

    @FXML
    private TextField turn;

    @FXML
    private TextField userName;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> olTable = FXCollections.observableArrayList();
        olTable.addAll("P<number> ... Bauer", "T<number> ... Turm", "K<number> ... Springer", "B<number> ... Läufer", "K ... König", "Q ... Königin");
        tableView.setItems(olTable);

        /*Socket socket;
        try {
            socket = new Socket("localhost", 4711);
            OutputStream out = socket.getOutputStream();
            PrintStream ps = new PrintStream(out, true);

            InputStream in = socket.getInputStream();
            BufferedReader buff = new BufferedReader(new InputStreamReader(in));

            ps.print("menuInformation;");

            while(true) {
                String st = buff.readLine();

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }*/
    }

    @FXML
    void handleSubmitAction(ActionEvent event) {
        if(!selectChessman.getText().equals("") && !selectField.getText().equals("")) {

        }
    }

}