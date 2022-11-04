package Rule;

import Board.Board;
import Piece.PieceType;
import Position.Position;
import Rule.types.MoveType;

public class NoPieceCrash {

    public boolean isMovePossible(Board board, Position positionTo, Position positionFrom, MoveType moveType) {
        if(moveType == MoveType.HORIZONTAL) return checkHorizontalMove(board, positionTo, positionFrom);
        if(moveType == MoveType.VERTICAL) return checkVerticalMove(board, positionTo, positionFrom);
        if(moveType == MoveType.DIAGONAL) return checkDiagonalMove(board, positionTo, positionFrom);
        if(moveType == MoveType.CASTLING) return checkCastlingMove(board, positionTo, positionFrom);
        return true;
    }

    private boolean checkCastlingMove(Board board, Position positionTo, Position positionFrom) {
        for (int i = positionFrom.getColumn() + 1; i < positionTo.getColumn(); i++) {
            if(!board.getBoard()[positionFrom.getRow()][i].isEmpty()) return false;
        }
        return true;
    }

    private boolean checkDiagonalMove(Board board, Position positionTo, Position positionFrom) {
        int fromRow = positionFrom.getRow();
        int fromCol = positionFrom.getColumn();
        int toRow = positionTo.getRow();
        int toCol = positionTo.getColumn();
        if(toRow < fromRow && toCol < fromCol){
            for (int i = fromRow - 1, j = 1 ; i > toRow ; i-- , j ++) {
                if(!board.getBoard()[i][fromCol - j].isEmpty()) return false;
            }
        }
        if(toRow < fromRow && toCol > fromCol){
            for (int i = fromRow - 1, j = 1 ; i > toRow ; i--, j ++) {
                if(!board.getBoard()[i][fromCol + j].isEmpty()) return false;
            }
        }
        if(toRow > fromRow && toCol < fromCol ){
            for (int i = fromRow + 1, j = 1 ; i < toRow ; i ++ , j ++) {
                if(!board.getBoard()[i][fromCol - j].isEmpty()) return false;
            }
        }
        if(toRow > fromRow && toCol > fromCol ){
            for (int i = fromRow + 1, j = 1 ; i < toRow ; i ++ , j ++) {
                if(!board.getBoard()[i][fromCol + j].isEmpty()) return false;
            }
        }
        return !(toRow == fromRow && toCol == fromCol);
    }

    private boolean checkVerticalMove(Board board, Position positionTo, Position positionFrom) {
        int difference = positionTo.getRow() - positionFrom.getRow();
        if(difference == 0) return false;
        int column = positionTo.getColumn();
        if(difference < 0) {
            for (int i = positionFrom.getRow() - 1; i > positionTo.getRow(); i --) {
                if(!board.getBoard()[i][column].isEmpty()) return false;
            }
        }
        if(difference > 0) {
            for (int i = positionFrom.getRow() + 1; i < positionTo.getRow(); i ++) {
                if(!board.getBoard()[i][column].isEmpty()) return false;
            }
        }
        if(positionFrom.getPiece().isPresent() & positionFrom.getPiece().get().getName() == PieceType.PAWN ||
                positionFrom.getPiece().get().getName() == PieceType.SUPER_PAWN){
            if(!positionTo.isEmpty()){
                return false;
            }
        }
        return true;
    }

    private boolean checkHorizontalMove(Board board, Position positionTo, Position positionFrom) {
        int difference = positionTo.getColumn() - positionFrom.getColumn();
        if(difference == 0) return false;
        int row = positionTo.getRow();
        if(difference < 0){
            for (int i = positionFrom.getColumn() - 1; i > positionTo.getColumn(); i --) {
                if(!board.getBoard()[row][i].isEmpty()) return false;
            }
        }
        if(difference > 0){
            for (int i = positionFrom.getColumn() + 1; i < positionTo.getColumn(); i ++) {
                if(!board.getBoard()[row][i].isEmpty()) return false;
            }
        }
        return true;
    }
}
