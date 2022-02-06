package com.example.project_echess;

import com.example.project_echess.Chessman.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
    int[] chessmanPosition;
    String currentChessmanID;

    Image marker;

    final private String TURN_PLAYER_BLACK = "Player BLACK is next!";
    final private String TURN_PLAYER_WHITE = "Player WHITE is next!";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hintField.setText("Click the Start Button to begin with the chess game!");

        marker = new Image(String.valueOf(this.getClass().getResource("/com/example/project_echess/Pictures/mark.png")));

        EventHandler<MouseEvent> eventHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Node mark = (Node) mouseEvent.getSource();
                selectField.setText(mark.getId());
                int[] newPositionChessman = getRowAndColIndex(mark);

                for(int i = 0; i < 8; i++) {
                    for(int y = 0; y < 8; y++) {
                        ((ImageView) stage.getScene().lookup("#M" + y + "" + i)).setImage(null);
                    }
                }

                for(Node n : gridPane.getChildren()) {
                    if(n.getId().equals(currentChessmanID)) {
                        gridPane.getChildren().remove(n);
                        gridPane.add(n, newPositionChessman[1], newPositionChessman[0]);
                        break;
                    }
                }
                chessboard.move(chessmanPosition[0], chessmanPosition[1], newPositionChessman[0], newPositionChessman[1]);

                if(currentPlayerBlack) {
                    currentPlayerBlack = false;
                    hintField.setText(TURN_PLAYER_WHITE);
                    turn.setText(Integer.toString(Integer.parseInt(turn.getText()) + 1));
                } else {
                    currentPlayerBlack = true;
                    hintField.setText(TURN_PLAYER_BLACK);
                }
            }
        };

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ImageView img = new ImageView();
                img.setId("M" + i + j);
                img.setFitHeight(35);
                img.setFitWidth(35);
                GridPane.setHalignment(img, HPos.CENTER);
                GridPane.setValignment(img, VPos.CENTER);
                gridPane.add(img, i, j);
                img.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHandler);
            }
        }
    }

    @FXML
    void handleStartAction(ActionEvent event) {
        if(turn.getText().equals("")) {
            String[] nameAndColor = stage.getTitle().split(" ");
            chessboard = createInitial(nameAndColor[1]);
            userName.setText(nameAndColor[0]);
            turn.setText("1");
            hintField.setText(TURN_PLAYER_WHITE);
        }
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
            chessmanPosition = null;
            chessmanIsClicked = true;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }


    @FXML
    void handleBlackKingClickedAction(MouseEvent event) {
        if(currentPlayerBlack) {
            chessmanPosition = null;
            chessmanIsClicked = true;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }

    @FXML
    void handleBlackKnightClickedAction(MouseEvent event) {
        if(currentPlayerBlack) {
            chessmanPosition = null;
            chessmanIsClicked = true;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }

    @FXML
    void handleBlackPawnClickedAction(MouseEvent event) {
        if(currentPlayerBlack) {
            chessmanPosition = null;
            chessmanIsClicked = true;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }

    @FXML
    void handleBlackQueenClickedAction(MouseEvent event) {
        if(currentPlayerBlack) {
            chessmanPosition = null;
            chessmanIsClicked = true;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }

    @FXML
    void handleBlackRookClickedAction(MouseEvent event) {
        if(currentPlayerBlack) {
            chessmanPosition = null;
            chessmanIsClicked = true;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }

    @FXML
    void handleFieldClickedAction(MouseEvent event) {}

    @FXML
    void handleWhiteBishopClickedAction(MouseEvent event) {
        if(!currentPlayerBlack && !turn.getText().equals("")) {
            chessmanPosition = null;
            chessmanIsClicked = true;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }

    @FXML
    void handleWhiteKingClickedAction(MouseEvent event) {
        if(!currentPlayerBlack && !turn.getText().equals("")) {
            chessmanPosition = null;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);
            chessmanIsClicked = true;

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }

    @FXML
    void handleWhiteKnightClickedAction(MouseEvent event) {
        if(!currentPlayerBlack && !turn.getText().equals("")) {
            chessmanPosition = null;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);
            chessmanIsClicked = true;

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }

    @FXML
    void handleWhitePawnClickedAction(MouseEvent event) {
        if(!currentPlayerBlack && !turn.getText().equals("")) {
            chessmanPosition = null;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);
            chessmanIsClicked = true;

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }

    @FXML
    void handleWhiteQueenClickedAction(MouseEvent event) {
        if(!currentPlayerBlack && !turn.getText().equals("")) {
            chessmanPosition = null;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);
            chessmanIsClicked = true;

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }

    @FXML
    void handleWhiteRookClickedAction(MouseEvent event) {
        if(!currentPlayerBlack && !turn.getText().equals("")) {
            chessmanPosition = null;

            Node clicked = (Node) event.getSource();
            chessmanPosition = getRowAndColIndex(clicked);

            Chessman chessman = chessboard.getContent(chessmanPosition[0], chessmanPosition[1]);
            chessmanIsClicked = true;

            currentChessmanID = clicked.getId();
            selectChessman.setText(currentChessmanID);
            setMarker(chessman, chessmanPosition[0], chessmanPosition[1]);
        }
    }

    private int[] getRowAndColIndex(Node clicked) {
        Integer colIndex = GridPane.getColumnIndex(clicked);
        Integer rowIndex = GridPane.getRowIndex(clicked);

        if(colIndex == null) {
            colIndex = 0;
        }
        if(rowIndex == null) {
            rowIndex = 0;
        }

        return new int[]{rowIndex, colIndex};
    }

    private void setMarker(Chessman chessman, int row, int column) {
        for(int i = 0; i < 8; i++) {
            for(int y = 0; y < 8; y++) {
                ((ImageView) stage.getScene().lookup("#M" + y + "" + i)).setImage(null);
            }
        }

        boolean[][] whereCanMove = chessman.getDestinations(chessboard, row, column);
        for(int i = 0; i < whereCanMove.length; i++) {
            for(int y = 0; y < whereCanMove.length; y++) {
                if(whereCanMove[i][y]) {
                    ((ImageView) stage.getScene().lookup("#M" + y + "" + i)).setImage(marker);
                    ((ImageView) stage.getScene().lookup("#M" + y + "" + i)).imageProperty();
                }
            }
        }
    }

    public void setPrimaryStage(Stage stage) {
        this.stage = stage;
    }
}