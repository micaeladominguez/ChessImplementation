package Generators;

import Board.Tuple;
import Piece.Piece;
import Piece.Pieces;
import Piece.Colors;

import java.util.HashMap;
import java.util.Map;


public class PieceGenerator implements Generator{
    protected Map<Tuple, PieceColor> initialPositionForPieces = new HashMap<>();
    public static Piece generatePiece(Colors color, Pieces piece){
        return new Piece(color, piece);
    }

    public Map<Tuple, PieceColor> getInitialPositionForPieces() {
        return initialPositionForPieces;
    }

    @Override
    public void generate() {
        initialPositionForPieces.put(new Tuple(0,0), new PieceColor(Pieces.ROOK, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(0,1),  new PieceColor(Pieces.HORSE, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(0,2),  new PieceColor(Pieces.BISHOP, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(0,3),  new PieceColor(Pieces.QUEEN, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(0,4),  new PieceColor(Pieces.KING, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(0,5), new PieceColor(Pieces.BISHOP, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(0,6), new PieceColor(Pieces.HORSE, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(0,7), new PieceColor(Pieces.ROOK, Colors.WHITE));

        initialPositionForPieces.put(new Tuple(1,0), new PieceColor(Pieces.PAWN, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(1,1), new PieceColor(Pieces.PAWN, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(1,2), new PieceColor(Pieces.PAWN, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(1,3), new PieceColor(Pieces.PAWN, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(1,4), new PieceColor(Pieces.PAWN, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(1,5), new PieceColor(Pieces.PAWN, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(1,6), new PieceColor(Pieces.PAWN, Colors.WHITE));
        initialPositionForPieces.put(new Tuple(1,7), new PieceColor(Pieces.PAWN, Colors.WHITE));

        initialPositionForPieces.put(new Tuple(6,0), new PieceColor(Pieces.PAWN, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(6,1), new PieceColor(Pieces.PAWN, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(6,2), new PieceColor(Pieces.PAWN, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(6,3), new PieceColor(Pieces.PAWN, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(6,4), new PieceColor(Pieces.PAWN, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(6,5), new PieceColor(Pieces.PAWN, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(6,6), new PieceColor(Pieces.PAWN, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(6,7), new PieceColor(Pieces.PAWN, Colors.BLACK));

        initialPositionForPieces.put(new Tuple(7,0),  new PieceColor(Pieces.ROOK, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(7,1), new PieceColor(Pieces.HORSE, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(7,2), new PieceColor(Pieces.BISHOP, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(7,3), new PieceColor(Pieces.QUEEN, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(7,4), new PieceColor(Pieces.KING, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(7,5), new PieceColor(Pieces.BISHOP, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(7,6), new PieceColor(Pieces.HORSE, Colors.BLACK));
        initialPositionForPieces.put(new Tuple(7,7), new PieceColor(Pieces.ROOK, Colors.BLACK));

    }
}
