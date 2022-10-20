package Generators;

import Board.Tuple;
import Position.Position;

public class BoardGenerator {
    public static Position[][] createBoard(Tuple maxEdges){
        Position[][] board = new Position[maxEdges.getRow()][maxEdges.getColumn()];
        for (int i = 0; i < maxEdges.getRow(); i++) {
            for(int j= 0; j < maxEdges.getColumn(); j ++){
                board[i][j] = new Position(i,j);
            }
        }
        return board;
    }
}
