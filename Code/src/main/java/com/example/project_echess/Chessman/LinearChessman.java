package com.example.project_echess.Chessman;

public class LinearChessman extends AbstractChessman {

    private boolean horizontalAndVertical;
    private boolean diagonal;
    private int range;

    public LinearChessman(boolean isBlack, char description, boolean horizontalAndVertical, boolean diagonal, int range) {
        super(isBlack, description);
        this.horizontalAndVertical = horizontalAndVertical;
        this.diagonal = diagonal;
        this.range = range;
    }

    private void check(int[] direction, Chessboard board, int row, int col, boolean[][] destinations) {
        try {
            for (int i = 1; i <= range; i++) {
                row = row + direction[0];
                col = col + direction[1];
                destinations[row][col] = true;

                if (board.getContent(row,col) != null) {
                    break;
                }
            }
        } catch(ArrayIndexOutOfBoundsException e) {

        }
    }

    private final static int[][] DIRECTIONS_HV = {{1,0},{-1,0},{0,1},{0,-1}};
    private final static int[][] DIRECTIONS_DIAG = {{1,1},{1,-1},{-1,1},{-1,-1}};

    public boolean[][] getDestinations (Chessboard board,int row,int col) {
        boolean[][] res = new boolean[board.getNumOfRows()][board.getNumOfColumns()];
        if (horizontalAndVertical) {
            for (int i = 0; i < DIRECTIONS_HV.length; i++) {
                check(DIRECTIONS_HV[i], board, row, col, res);
            }
        }
        if (diagonal) {
            for (int i = 0; i < DIRECTIONS_DIAG.length; i++) {
                check(DIRECTIONS_DIAG[i], board, row, col, res);
            }
        }
        cleanBlocked(res, board, this);

        return res;
    }
}
