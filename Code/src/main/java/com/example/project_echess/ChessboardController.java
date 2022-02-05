package com.example.project_echess;

import com.example.project_echess.Chessman.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

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
    private ImageView bishopBlackL;

    @FXML
    private ImageView bishopBlackR;

    @FXML
    private ImageView bishopWhiteL;

    @FXML
    private ImageView bishopWhiteR;

    @FXML
    private ImageView kingBlack;

    @FXML
    private ImageView kingWhite;

    @FXML
    private ImageView knightBlackL;

    @FXML
    private ImageView knightBlackR;

    @FXML
    private ImageView knightWhiteL;

    @FXML
    private ImageView knightWhiteR;

    @FXML
    private ImageView pawnBlack1;

    @FXML
    private ImageView pawnBlack2;

    @FXML
    private ImageView pawnBlack3;

    @FXML
    private ImageView pawnBlack4;

    @FXML
    private ImageView pawnBlack5;

    @FXML
    private ImageView pawnBlack6;

    @FXML
    private ImageView pawnBlack7;

    @FXML
    private ImageView pawnBlack8;

    @FXML
    private ImageView pawnWhite1;

    @FXML
    private ImageView pawnWhite2;

    @FXML
    private ImageView pawnWhite3;

    @FXML
    private ImageView pawnWhite4;

    @FXML
    private ImageView pawnWhite5;

    @FXML
    private ImageView pawnWhite6;

    @FXML
    private ImageView pawnWhite7;

    @FXML
    private ImageView pawnWhite8;

    @FXML
    private ImageView queenBlack;

    @FXML
    private ImageView queenWhite;

    @FXML
    private ImageView rookBlackL;

    @FXML
    private ImageView rookBlackR;

    @FXML
    private ImageView rookWhiteL;

    @FXML
    private ImageView rookWhiteR;

    @FXML
    private TextField hintField;

    @FXML
    private TextField selectChessman;

    @FXML
    private TextField selectField;

    @FXML
    private TextField turn;

    @FXML
    private TextField userName;

    @FXML
    private GridPane gridPane;

    private Stage stage;

    private Chessboard chessboard;

    boolean currentPlayerBlack = false;
    boolean chessmanIsClicked = false;

    public void setPrimaryStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Click Button");
        alert.setHeaderText("Please click the Submit Button to start the chess game!");
        alert.showAndWait();

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
        if(turn.getText().equals("")) {
            String[] nameAndColor = stage.getTitle().split(" ");
            chessboard = createInitial(nameAndColor[1]);
            userName.setText(nameAndColor[0]);
            turn.setText("1");
            hintField.setText("Player WHITE is next!");
        }
    }

    public char getContent(int row, int col) {
        Chessman chessman = chessboard.getContent(row,col);
        if (chessman == null) {
            return ' ';
        }
        return chessman.toChar();
    }

    public final static Chessman[] CHESSMEN =
    {
            new Pawn(true),     // 0: schwarzer Bauer
            new Pawn(false),    // 1: weisser Bauer
            new Rook(true),         // 2: schwarzer Turm
            new Rook(false),        // 3: weisser Turm
            new Knight(true),   // 4: schwarzer Springer
            new Knight(false),  // 5: weisser Springer
            new Bishop(true),       // 6: schwarzer Laeufer
            new Bishop(false),      // 7: weisser Laeufer
            new Queen(true),    // 8: schwarze Dame
            new Queen(false),   // 9: weisse Dame
            new King(true),     // 10: schwarzer Koenig
            new King(false)     // 11: weisser Koenig
    };

    public final static int[][] BOARD_AT_START_WHITE =
    {
        { 2, 4, 6, 8,10, 6, 4, 2},
        { 0, 0, 0, 0, 0, 0, 0, 0},
        {-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1},
        {-1,-1,-1,-1,-1,-1,-1,-1},
        { 1, 1, 1, 1, 1, 1, 1, 1},
        { 3, 5, 7, 9,11, 7, 5, 3}
    };

    protected Chessboard createInitial(String color) {
        Chessboard board = new Chessboard(8, 8);
        if(color.equals("BLACK")) {
            for (int i = 7; i >= 0; i--) {
                for (int j = 7; j >= 0; j--) {
                    if (BOARD_AT_START_WHITE[i][j] > -1) {
                        board.setContent(i, j, CHESSMEN[BOARD_AT_START_WHITE[i][j]]);
                    }
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (BOARD_AT_START_WHITE[i][j] > -1) {
                        board.setContent(i, j, CHESSMEN[BOARD_AT_START_WHITE[i][j]]);
                    }
                }
            }
        }

        return board;
    }

    @FXML
    void handleBlackBishopClickedAction(MouseEvent event) {
        if(currentPlayerBlack) {
            Chessman c = chessboard.getContent( (int) event.getX(), (int) event.getY());
            stage.setTitle(c.toString());
            chessmanIsClicked = true;
        }
    }

    @FXML
    void handleBlackKingClickedAction(MouseEvent event) {
        if(currentPlayerBlack) {

        }
    }

    @FXML
    void handleBlackKnightClickedAction(MouseEvent event) {
        if(currentPlayerBlack) {

        }
    }

    @FXML
    void handleBlackPawnClickedAction(MouseEvent event) {
        if(currentPlayerBlack) {

        }
    }

    @FXML
    void handleBlackQueenClickedAction(MouseEvent event) {
        if(currentPlayerBlack) {

        }
    }

    @FXML
    void handleBlackRookClickedAction(MouseEvent event) {
        if(currentPlayerBlack) {

        }
    }

    @FXML
    void handleFieldClickedAction(MouseEvent event) {
        if(chessmanIsClicked) {
            selectField.setText("HALLO");
        }
    }

    @FXML
    void handleWhiteBishopClickedAction(MouseEvent event) {
        if(!currentPlayerBlack) {
            //Chessman c = chessboard.getContent( (int) event.getX(), (int) event.getY());
            //stage.setTitle(c.toString());
            chessmanIsClicked = true;
        }
    }

    @FXML
    void handleWhiteKingClickedAction(MouseEvent event) {
        if(!currentPlayerBlack) {

        }
    }

    @FXML
    void handleWhiteKnightClickedAction(MouseEvent event) {
        if(!currentPlayerBlack) {

        }
    }

    @FXML
    void handleWhitePawnClickedAction(MouseEvent event) {
        if(!currentPlayerBlack) {

        }
    }

    @FXML
    void handleWhiteQueenClickedAction(MouseEvent event) {
        if(!currentPlayerBlack) {

        }
    }

    @FXML
    void handleWhiteRookClickedAction(MouseEvent event) {
        if(!currentPlayerBlack) {

        }
    }
}