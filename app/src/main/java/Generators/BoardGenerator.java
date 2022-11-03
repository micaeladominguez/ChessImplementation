package Generators;

import Position.Position;

public class BoardGenerator implements Generator{
    public Position[][] board;

    public Position[][] getBoard() {
        return board;
    }

    @Override
    public void generate() {
        Position[][] board = new Position[8][8];
        for (int i = 0; i < 8; i++) {
            for(int j= 0; j < 8; j ++){
                board[i][j] = new Position(i,j);
            }
        }
        this.board = board;
    }
}
