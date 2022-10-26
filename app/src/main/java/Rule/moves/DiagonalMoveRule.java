package Rule.moves;

import Board.Board;
import Piece.Pieces;
import Position.Position;
import Rule.Rule;
import Rule.types.MoveType;
import Rule.types.RuleResponse;

public class DiagonalMoveRule implements Rule {
    int limit;

    public DiagonalMoveRule(int limit) {
        this.limit = limit;
    }

    @Override
    public RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom) {
        if(positionFrom.getPiece().get().getName() == Pieces.PAWN ||
                positionFrom.getPiece().get().getName() == Pieces.SUPER_ROOK){
            if(positionTo.getPiece().isEmpty()) return new RuleResponse(false, MoveType.DIAGONAL );
        }

        int fromRow = positionFrom.getRow();
        int fromCol = positionFrom.getColumn();
        int toRow = positionTo.getRow();
        int toCol = positionTo.getColumn();
        int rowIterator = 0;
        if (fromRow > toRow)
            rowIterator = -1;
        else
            rowIterator =1;

        int colIterator = 0;
        if (fromCol > toCol)
            colIterator = -1;
        else
            colIterator =1;

        int index = limit;

        while (fromRow != toRow && fromCol != toCol  && index != 0) {
            fromRow += rowIterator ;
            fromCol += colIterator ;
            if( limit != -1){
                index -= index;
            }
        }

        return new RuleResponse((fromRow == toRow && fromCol == toCol), MoveType.DIAGONAL );
    }
}
