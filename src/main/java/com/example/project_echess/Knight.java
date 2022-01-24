package com.example.project_echess;

public class Knight extends AbstractChessman {

    public Knight(boolean isColorBlack) {
        super(isColorBlack, 'N');
    }

    private final static int[][] DIRECTIONS = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};

    public boolean[][] getDestinations (Chessboard board, int row, int col) {
        boolean[][] res = new boolean[board.getNumOfRows()][board.getNumOfColumns()];
        for (int i = 0; i < DIRECTIONS.length; i++) {
            int x = row + DIRECTIONS[i][0];
            int y = col + DIRECTIONS[i][1];
            if (board.isValid(x, y)) {
                res[x][y] = true;
            }
        }
        cleanBlocked(res, board, this);
        return res;
    }
}
