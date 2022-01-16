package com.example.project_echess;

public class AbstractChessman implements Chessman {
    private char description;
    private boolean isBlack;

    public AbstractChessman(boolean isBlack,char description) {
        this.description = description;
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    @Override
    public char toChar() {
        return ' ';
        //return (char) (description + ((isBlack) ? ('a' - 'A') : 0));
    }

    @Override
    public boolean canTake(Chessman chessman) {
        if (!(chessman instanceof AbstractChessman)) {
            return false;
        }
        return isBlack != ((AbstractChessman) chessman).isBlack;
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
}
