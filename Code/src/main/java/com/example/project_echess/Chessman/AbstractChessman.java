package com.example.project_echess.Chessman;

public class AbstractChessman implements Chessman {
    private char description;
    private boolean isColorBlack;

    public AbstractChessman(boolean isColorBlack,char description) {
        this.description = description;
        this.isColorBlack = isColorBlack;
    }

    public boolean isColorBlack() {
        return isColorBlack;
    }

    @Override
    public char toChar() {
        return (char) (description + ((isColorBlack) ? ('a' - 'A') : 0));
    }

    @Override
    public boolean canTake(Chessman chessman) {
        if (!(chessman instanceof AbstractChessman)) {
            return false;
        }
        return isColorBlack != ((AbstractChessman) chessman).isColorBlack;
    }

    @Override
    public boolean[][] getDestinations(Chessboard board, int row, int col) {
        return new boolean[0][];
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Chessman) {
            return ((Chessman) o).toChar() == toChar();
        }
        return false;
    }

    public int hashCode() {
        return toChar();
    }

    public static void cleanBlocked(boolean[][] destinations, Chessboard chessboard,Chessman chessman) {
        for (int i = 0; i < destinations.length; i++) {
            for (int j = 0; j < destinations[i].length; j++) {
                if (destinations[i][j]) {
                    Chessman opponent = chessboard.getContent(i, j);
                    if (opponent != null && !chessman.canTake(opponent)) {
                        destinations[i][j] = false;
                    }
                }
            }
        }
    }
}
