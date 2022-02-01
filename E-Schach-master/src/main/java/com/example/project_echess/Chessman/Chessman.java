package com.example.project_echess.Chessman;

import com.example.project_echess.Chessboard;

public interface Chessman {
    char toChar();
    boolean canTake(Chessman chessman);
    boolean[][] getDestinations(Chessboard board, int row, int col);
    boolean equals (Object o);
}
