package Generators;

import Piece.Colors;
import Piece.Pieces;

public class PieceColor {
    Pieces piece;
    Colors color;

    public PieceColor(Pieces piece, Colors color) {
        this.piece = piece;
        this.color = color;
    }

    public Pieces getPiece() {
        return piece;
    }

    public void setPiece(Pieces piece) {
        this.piece = piece;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }
}
