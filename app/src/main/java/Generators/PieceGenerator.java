package Generators;

import Piece.Piece;
import Piece.Pieces;
import Piece.Colors;


public class PieceGenerator {
    public static Piece generatePiece(Colors color, Pieces piece){
        return new Piece(color, piece);
    }
}
