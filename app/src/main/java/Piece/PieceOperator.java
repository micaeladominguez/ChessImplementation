package Piece;

import java.util.Optional;
import Position.Position;

public class PieceOperator {

    public static boolean exists(Optional<Piece> piece){
        return piece.isPresent();
    }
    public static boolean isPieceIn(Position position){
        return position.getPiece().isPresent();
    }
    public static boolean isKing(Optional<Piece> origin) {
        return origin.isPresent() && origin.get().getName() == PieceType.KING;
    }

    public static boolean isRook(Optional<Piece> origin) {
        return origin.isPresent() && origin.get().getName() == PieceType.ROOK;
    }

    public static boolean isPawn(Optional<Piece> origin) {
        return origin.isPresent() && origin.get().getName() == PieceType.PAWN;
    }
    public static boolean isSuperPawn(Optional<Piece> origin) {
        return  origin.isPresent() && origin.get().getName() == PieceType.SUPER_PAWN;
    }

    public static boolean isTypeOfPawn(Optional<Piece> origin){return
            origin.isPresent() && origin.get().getName() == PieceType.PAWN ||
       origin.get().getName() == PieceType.SUPER_PAWN; }
    public static boolean isSuperRook(Optional<Piece> origin) {
        return origin.isPresent() && origin.get().getName() == PieceType.SUPER_ROOK;
    }

    public static boolean hasMoved(Optional<Piece> origin) {
        return
                origin.isPresent() && origin.get().moves() == 0;
    }


    public static boolean sameColor(Optional<Piece> o, Optional<Piece> a){
        return o.isPresent() && a.isPresent() && o.get().getColor() == a.get().getColor();
    }
}
