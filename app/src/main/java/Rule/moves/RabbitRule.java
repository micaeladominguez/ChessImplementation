package Rule.moves;

import Board.Board;
import Position.Position;
import Rule.Rule;
import Rule.types.MoveType;
import Rule.types.RuleResponse;

public class RabbitRule implements Rule {
    int limit;

    public RabbitRule(int limit) {
        this.limit = limit;
    }

    @Override
    public RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom) {
        if(conditionForRule(board, positionTo, positionFrom)) return new RuleResponse(true, MoveType.SKIP);
        else return new RuleResponse(false, MoveType.SKIP);
    }

    private boolean conditionForRule(Board board, Position positionTo, Position positionFrom) {
        int difference = Math.abs(positionTo.getRow() - positionFrom.getRow());
        return positionTo.getPiece().isEmpty() ||
                positionFrom.getColumn() == positionTo.getColumn()
                || difference == limit
                || pieceInAllPath(board, positionTo.getRow(), positionFrom.getRow(), positionFrom.getColumn());
    }

    private boolean pieceInAllPath(Board board, int rowTo, int rowFrom, int column) {
        int min = Math.min(rowFrom, rowTo);
        int max = Math.max(rowFrom, rowTo);
        for (int i = min; i < max ; i++) {
            if(board.getBoard()[i][column].isEmpty()) return false;
        }
        return true;
    }
}
