package Rule;

import Board.Board;
import Piece.Pieces;
import Position.Position;
import Rule.types.MoveType;
import Rule.types.RuleResponse;

public class CastlingRule implements Rule {

    @Override
    public RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom) {
       if(positionFrom.getPiece().isPresent() && positionTo.getPiece().isPresent()){
           if(positionFrom.getPiece().get().getName() == Pieces.KING &&
                  positionTo.getPiece().get().getName() == Pieces.ROOK &&
                  positionFrom.getPiece().get().moves() == 0
                  && positionTo.getPiece().get().moves() == 0){
               return new RuleResponse(true, MoveType.CASTLING);
           }
       }
       return new RuleResponse(false, MoveType.CASTLING);
    }
}
