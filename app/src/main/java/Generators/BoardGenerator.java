package Generators;

import Position.Position;

public class BoardGenerator{
    public Position[][] board;

    public Position[][] getBoard() {
        return board;
    }

    public void generate(int maxRow, int maxColumn) {
        Position[][] board = new Position[maxRow][maxColumn];
        for (int i = 0; i < 8; i++) {
            for(int j= 0; j < 8; j ++){
                board[i][j] = new Position(i,j);
            }
        }
        this.board = board;
    }
}
