package com.example.project_echess;

public interface Chessman {
    char toChar();
    boolean canTake(Chessman chessman);
    boolean[][] getDestinations(Chessboard board, int row, int col);
    boolean equals (Object o);
}
