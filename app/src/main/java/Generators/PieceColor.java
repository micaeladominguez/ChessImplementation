package Generators;

import Piece.ColorType;
import Piece.PieceType;

public class PieceColor {
    PieceType piece;
    ColorType color;

    public PieceColor(PieceType piece, ColorType color) {
        this.piece = piece;
        this.color = color;
    }

    public PieceType getPiece() {
        return piece;
    }

    public void setPiece(PieceType piece) {
        this.piece = piece;
    }

    public ColorType getColor() {
        return color;
    }

    public void setColor(ColorType color) {
        this.color = color;
    }
}
