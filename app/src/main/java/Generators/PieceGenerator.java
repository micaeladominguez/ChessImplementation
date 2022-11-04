package Generators;

import Board.Tuple;
import Piece.Piece;
import Piece.PieceType;
import Piece.ColorType;

import java.util.HashMap;
import java.util.Map;


public class PieceGenerator{
    protected Map<Tuple, PieceColor> initialPositionForPieces = new HashMap<>();
    public static Piece generatePiece(ColorType color, PieceType piece){
        return new Piece(color, piece);
    }

    public Map<Tuple, PieceColor> getInitialPositionForPieces() {
        return initialPositionForPieces;
    }

    public void generateNormalBoard() {
        definePosition(0,0, PieceType.ROOK, ColorType.WHITE);
        definePosition(0,7, PieceType.ROOK, ColorType.WHITE);

        definePosition(0,1, PieceType.HORSE, ColorType.WHITE);
        definePosition(0,6, PieceType.HORSE, ColorType.WHITE);

        definePosition(0,2, PieceType.BISHOP, ColorType.WHITE);
        definePosition(0,5, PieceType.BISHOP, ColorType.WHITE);

        definePosition(0,3, PieceType.QUEEN, ColorType.WHITE);
        definePosition(0,4, PieceType.KING, ColorType.WHITE);

        definePosition(1, 0, PieceType.PAWN, ColorType.WHITE);
        definePosition(1, 1, PieceType.PAWN, ColorType.WHITE);
        definePosition(1, 2, PieceType.PAWN, ColorType.WHITE);
        definePosition(1, 3, PieceType.PAWN, ColorType.WHITE);
        definePosition(1, 4, PieceType.PAWN, ColorType.WHITE);
        definePosition(1, 5, PieceType.PAWN, ColorType.WHITE);
        definePosition(1, 6, PieceType.PAWN, ColorType.WHITE);
        definePosition(1, 7, PieceType.PAWN, ColorType.WHITE);



        definePosition(7,0, PieceType.ROOK, ColorType.BLACK);
        definePosition(7,7, PieceType.ROOK, ColorType.BLACK);

        definePosition(7,1, PieceType.HORSE, ColorType.BLACK);
        definePosition(7,6, PieceType.HORSE, ColorType.BLACK);

        definePosition(7,2, PieceType.BISHOP, ColorType.BLACK);
        definePosition(7,5, PieceType.BISHOP, ColorType.BLACK);

        definePosition(7,3, PieceType.QUEEN,ColorType.BLACK);
        definePosition(7,4, PieceType.KING, ColorType.BLACK);

        definePosition(6, 0, PieceType.PAWN, ColorType.BLACK);
        definePosition(6, 1, PieceType.PAWN, ColorType.BLACK);
        definePosition(6, 2, PieceType.PAWN, ColorType.BLACK);
        definePosition(6, 3, PieceType.PAWN, ColorType.BLACK);
        definePosition(6, 4, PieceType.PAWN, ColorType.BLACK);
        definePosition(6, 5, PieceType.PAWN, ColorType.BLACK);
        definePosition(6, 6, PieceType.PAWN, ColorType.BLACK);
        definePosition(6, 7, PieceType.PAWN, ColorType.BLACK);
    }

    private void definePosition(int row, int column, PieceType pieceType, ColorType color) {
        initialPositionForPieces.put(new Tuple(row,column), new PieceColor(pieceType, color));
    }
}
