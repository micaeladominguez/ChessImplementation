package Rule.moves;

import Board.Board;
import Piece.PieceOperator;
import Position.Position;
import Rule.Rule;
import Rule.types.MoveType;
import Rule.types.RuleResponse;
import org.jetbrains.annotations.NotNull;

public class DiagonalMoveRule implements Rule {
    ForwardRule forwardRule = new ForwardRule();
    int limit;

    public DiagonalMoveRule(int limit) {
        this.limit = limit;
    }

    @Override
    public RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom) {
        if(eatDiagonalCondition(positionFrom)) return new RuleResponse(false, MoveType.DIAGONAL );
        if(!forwardCondition(board, positionTo, positionFrom)) return new RuleResponse(false, MoveType.DIAGONAL);
        return checkDiagonalCondition(positionTo.getRow(), positionTo.getColumn(), positionFrom.getRow(), positionFrom.getColumn());
    }

    @NotNull
    private RuleResponse checkDiagonalCondition(int toRow, int toCol, int fromRow, int fromCol) {
        int rowIterator = fromRow > toRow ? - 1 : 1;
        int colIterator = fromCol > toCol ? -1 : 1;
        int index = limit;
        while (conditionCorrect(toRow, toCol, fromRow, fromCol, index)) {
            fromRow += rowIterator ;
            fromCol += colIterator ;
            if( limit != -1){
                index -= index;
            }
        }
        return new RuleResponse((fromRow == toRow && fromCol == toCol), MoveType.DIAGONAL);
    }

    private boolean conditionCorrect(int toRow, int toCol, int fromRow, int fromCol, int index) {
        return fromRow != toRow && fromCol != toCol && index != 0;
    }


    private boolean forwardCondition(Board board, Position positionTo, Position positionFrom) {
        if(PieceOperator.isTypeOfPawn(positionFrom.getPiece()) &&
                !forwardRule.isMovePossible(board, positionTo, positionFrom).isCorrect()){
                return false;
        }
        return true;
    }

    private static boolean eatDiagonalCondition(Position positionFrom) {
        return !PieceOperator.exists(positionFrom.getPiece()) && (PieceOperator.isTypeOfPawn(positionFrom.getPiece())
                || PieceOperator.isSuperRook(positionFrom.getPiece()));
    }

}
