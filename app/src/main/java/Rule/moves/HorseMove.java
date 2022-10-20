package Rule.moves;

import Board.Board;
import Position.Position;
import Rule.Rule;
import Rule.types.MoveType;
import Rule.types.RuleResponse;

public class HorseMove implements Rule {

    @Override
    public RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom) {
        int fromRow = positionFrom.getRow();
        int fromCol = positionFrom.getColumn();
        int toRow = positionTo.getRow();
        int toCol = positionTo.getColumn();
        int[] xMoves = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] yMoves = {1, 2, 2, 1, -1, -2, -2, -1};
        for (int i = 0; i < xMoves.length; i++) {
            if((fromRow + xMoves[i]) == toRow && (fromCol + yMoves[i]) == toCol){
                return new RuleResponse(true, MoveType.HORSE);
            }
        }
        return new RuleResponse(false, MoveType.HORSE);
    }
}
