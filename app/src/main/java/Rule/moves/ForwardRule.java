package Rule.moves;

import Board.Board;
import Piece.ColorType;
import Position.Position;
import Rule.Rule;
import Rule.types.MoveType;
import Rule.types.RuleResponse;

public class ForwardRule implements Rule {
    //BUSCO CHEQUEAR QUE VAYA PARA ADELANTE
    @Override
    public RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom) {
        if(positionFrom.getPiece().isPresent() && positionFrom.getPiece().get().getColor() == ColorType.WHITE){
            if( positionTo.getRow() > positionFrom.getRow()) return new RuleResponse(true, MoveType.SKIP);
        }else{
            if( positionTo.getRow() < positionFrom.getRow())
                return new RuleResponse(true, MoveType.SKIP);

        }
        return new RuleResponse(false, MoveType.SKIP);
    }
}
