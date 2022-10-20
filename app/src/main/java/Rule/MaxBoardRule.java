package Rule;

import Board.Board;
import Position.Position;
import Board.Tuple;

public class MaxBoardRule {
    public boolean isMovePossible(Board board, int column, int row){
        Tuple maxEdges = board.getMaxEdges();
        return !(row >= maxEdges.getRow() || column >= maxEdges.getColumn() || column < 0 || row < 0 );
    }
}
