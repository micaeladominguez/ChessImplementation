package Rule.moves;

import Board.Board;
import Piece.Pieces;
import Position.Position;
import Rule.Rule;
import Rule.types.MoveType;
import Rule.types.RuleResponse;

public class HorizontalRuleMove implements Rule {
    int limit;

    public HorizontalRuleMove(int limit) {
        this.limit = limit;
    }

    @Override
    public RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom) {
        if(limit != -1){
            int difference = positionTo.getColumn() - positionFrom.getColumn();
            return new RuleResponse(positionTo.getRow() == positionFrom.getRow() && Math.abs(difference) <= limit, MoveType.HORIZONTAL);
        }else{
            return new RuleResponse(positionTo.getRow() == positionFrom.getRow(), MoveType.HORIZONTAL)  ;
        }
    }
}
