package com.example.project_echess.Chessman;

public class Chessboard {
    private Chessman[][] content;

    public Chessboard (int rows, int cols) {
        content = new Chessman[rows][cols];
    }

    public boolean isValid(int row, int col) {
        if(row >= 0 && col >= 0 && row < content.length && col < content[0].length) {
            return true;
        }
        return false;
    }

    public int getNumOfColumns() {
        return content[0].length;
    }

    public int getNumOfRows() {
        return content.length;
    }

    public Chessman getContent(int row, int col) {
        return content[row][col];
    }

    public Chessman setContent(int row, int col, Chessman chessman) {
        Chessman oldContent = content[row][col];
        content[row][col] = chessman;
        return oldContent;
    }

    public Chessman move(int oldRow, int oldCol, int newRow, int newCol) {
        Chessman c = content[oldRow][oldCol];

        if(c != null && content[newRow][newCol] == null) {
            content[oldRow][oldCol] = null;
            content[newRow][newCol] = c;
        } else if(c != null && c.canTake(content[newRow][newCol])) {
            content[oldRow][oldCol] = null;
            Chessman thrownChessman = content[newRow][newCol];
            content[newRow][newCol] = c;
            return thrownChessman;
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Chessboard) {
            Chessboard board = (Chessboard) o;
            if (board.content.length != content.length || board.content[0].length != content[0].length) {
                return false;
            }
            for (int i = 0; i < content.length; i++) {
                for (int j = 0; j < board.content[i].length; j++) {
                    Chessman man1 = content[i][j];
                    Chessman man2 = board.content[i][j];
                    if (man1 == null && man2 == null) {
                        continue;
                    }
                    if (man1 != null && man2 != null && man1.equals(man2)) {
                        continue;
                    }
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int res = 0;
        for (int i = 0; i < content.length; i++)
            for (int j = 0; j < content[i].length; j++)
                if (content[i][j] != null) {
                    res += content[i][j].hashCode();
                }
        return res;
    }
}
