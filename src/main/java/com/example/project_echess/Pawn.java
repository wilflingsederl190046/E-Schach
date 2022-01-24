package com.example.project_echess;

public class Pawn extends AbstractChessman {

    public Pawn(boolean isColorBlack) {
        super(isColorBlack, 'P');
    }

    public boolean[][] getDestinations (Chessboard board,int row,int col) {
        boolean[][] res = new boolean[board.getNumOfRows()][board.getNumOfColumns()];
        row = isColorBlack() ? row + 1 : row - 1;

        if (!board.isValid(row,col)) {
            return res;
        }else if (board.getContent(row,col) == null) {
            res[row][col] = true;
        }

        for (int i = -1; i < 2; i += 2) {
            int column = col + i;
            if (!board.isValid(row,column)) {
                continue;
            }
            Chessman opponent = board.getContent(row,column);
            if (opponent != null && canTake(opponent)) {
                res[row][column] = true;
            }
        }

        if (res[row][col]) {
            row = isColorBlack() ? row + 1 : row - 1;
            if (board.isValid(row, col) && ((row == res.length - 4 && !isColorBlack()) || (row == 3 && isColorBlack())) && board.getContent(row, col) == null) {
                res[row][col] = true;
            }
        }
        return res;
    }
}
