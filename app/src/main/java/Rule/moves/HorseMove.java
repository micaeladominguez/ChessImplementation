package Rule.moves;

import Board.Board;
import Position.Position;
import Rule.Rule;
import Rule.types.MoveType;
import Rule.types.RuleResponse;
import org.jetbrains.annotations.Nullable;

public class HorseMove implements Rule {

    @Override
    public RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom) {
        if(checkHorseCondition(positionTo.getRow(), positionTo.getColumn(), positionFrom.getRow(), positionFrom.getColumn()))
            return new RuleResponse(true, MoveType.HORSE);
        return new RuleResponse(false, MoveType.HORSE);
    }

    private boolean checkHorseCondition(int toRow, int toCol, int fromRow, int fromCol) {
        int[] xMoves = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] yMoves = {1, 2, 2, 1, -1, -2, -2, -1};
        for (int i = 0; i < xMoves.length; i++) {
            if((fromRow + xMoves[i]) == toRow && (fromCol + yMoves[i]) == toCol){
                return true;
            }
        }
        return false;
    }
}
