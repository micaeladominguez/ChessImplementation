package Rule.moves;

import Board.Board;
import Piece.PieceOperator;
import Position.Position;
import Rule.Rule;
import Rule.types.MoveType;
import Rule.types.RuleResponse;
import org.jetbrains.annotations.NotNull;

public class VerticalMoveRule implements Rule {
    ForwardRule forwardRule = new ForwardRule();
    int limit;

    public VerticalMoveRule(int limit) {
        this.limit = limit;
    }

    @Override
    public RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom) {
        if (limit != -1) {
            return checkCondition(board, positionTo, positionFrom);
        } else {
            return new RuleResponse(positionTo.getColumn() == positionFrom.getColumn(), MoveType.VERTICAL);
        }
    }

    @NotNull
    private RuleResponse checkCondition(Board board, Position positionTo, Position positionFrom) {
        int difference = Math.abs(positionTo.getRow() - positionFrom.getRow());
        int useLimit = defineLimit(positionFrom);
        if (!PieceOperator.isPawn(positionFrom.getPiece())) {
            return new RuleResponse((positionTo.getColumn() == positionFrom.getColumn() && difference <= useLimit), MoveType.VERTICAL);
        } else {
            return new RuleResponse(defineResponse(board, positionTo, positionFrom, difference, useLimit), MoveType.VERTICAL);
        }
    }

    private int defineLimit(Position positionFrom) {
        int useLimit = PieceOperator.isTypeOfPawn(positionFrom.getPiece())
                && positionFrom.getPiece().get().moves() == 0 ? 2 : 1 ;
        limit = 1;
        return useLimit;
    }

    private boolean defineResponse(Board board, Position positionTo, Position positionFrom, int difference, int useLimit) {
        return (positionTo.getColumn() == positionFrom.getColumn() && difference <= useLimit)
                && forwardRule.isMovePossible(board, positionTo, positionFrom).isCorrect();
    }
}