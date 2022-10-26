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
        if (limit != -1) {
            int difference = Math.abs(positionTo.getRow() - positionFrom.getRow());
            int useLimit = 0;
            if ((positionFrom.getPiece().get().getName() == Pieces.PAWN ||
                    positionFrom.getPiece().get().getName() == Pieces.SUPER_PAWN)
            && positionFrom.getPiece().get().moves() == 0) {
                useLimit = 2;
                this.limit = 1;
            } else {
                useLimit = 1;
            }
            if (positionFrom.getPiece().isPresent() && positionFrom.getPiece().get().getName() != Pieces.PAWN) {
                return new RuleResponse((positionTo.getColumn() == positionFrom.getColumn() && difference <= useLimit), MoveType.VERTICAL);
            } else {
                return new RuleResponse((positionTo.getColumn() == positionFrom.getColumn() && difference <= useLimit)
                        && forwardRule.isMovePossible(board, positionTo, positionFrom).isResponse(), MoveType.VERTICAL);
            }
        } else {
            return new RuleResponse(positionTo.getColumn() == positionFrom.getColumn(), MoveType.VERTICAL);
        }
    }
}