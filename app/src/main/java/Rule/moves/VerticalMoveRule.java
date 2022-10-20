package Rule.moves;

import Board.Board;
import Piece.Pieces;
import Position.Position;
import Rule.Rule;
import Rule.types.MoveType;
import Rule.types.RuleResponse;

public class VerticalMoveRule implements Rule {
    ForwardRule forwardRule = new ForwardRule();
    int limit;

    public VerticalMoveRule(int limit) {
        this.limit = limit;
    }

    @Override
    public RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom) {
        if(limit != -1){
            int difference = Math.abs(positionTo.getRow() - positionFrom.getRow());
            int differenceWithLimit = 0;
            int useLimit = 0;
            if(positionFrom.getPiece().get().getName() == Pieces.PAWN && positionFrom.getPiece().get().moves() == 0){
                useLimit = 2;
                this.limit = 2;
                differenceWithLimit = difference - limit;
                this.limit = 1;
            }else{
                useLimit = 1;
                differenceWithLimit = difference - limit;
            }
            if(positionFrom.getPiece().isPresent() && positionFrom.getPiece().get().getName() != Pieces.PAWN){
                return new RuleResponse((positionTo.getColumn() == positionFrom.getColumn() && Math.abs(differenceWithLimit) < useLimit), MoveType.VERTICAL);
            }else{
                return new RuleResponse((positionTo.getColumn() == positionFrom.getColumn() && Math.abs(differenceWithLimit) < useLimit)
                        && forwardRule.isMovePossible(board, positionTo, positionFrom).isResponse(), MoveType.VERTICAL);
            }
        }else{
            return new RuleResponse(positionTo.getColumn() == positionFrom.getColumn(), MoveType.VERTICAL );
        }

    }

}
