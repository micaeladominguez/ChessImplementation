package Rule;

import Board.Board;
import Piece.Piece;
import Piece.PieceType;
import Position.Position;
import Rule.types.MoveType;
import Piece.PieceOperator;
import Rule.types.RuleResponse;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class CastlingRule implements Rule {


    @Override
    public RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom) {
        if(PieceOperator.isPieceIn(positionFrom) && PieceOperator.isPieceIn(positionTo)){
            return isMovePossible(positionFrom.getPiece(), positionTo.getPiece());
        }
        else return RuleOperator.invalidResponse(MoveType.CASTLING);
    }

    private RuleResponse isMovePossible(Optional<Piece> origin, Optional<Piece> target) {
        if (PieceOperator.isKing(origin) && PieceOperator.isRook(target) &&
                !PieceOperator.hasMoved(origin) && !PieceOperator.hasMoved(target)) {
            return RuleOperator.validResponse(MoveType.CASTLING);
        }
        else return RuleOperator.invalidResponse(MoveType.CASTLING);
    }

}
