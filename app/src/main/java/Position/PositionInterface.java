package Position;

import Piece.Piece;

import java.util.Optional;

public interface PositionInterface {
    Optional<Piece> getPiece();
    void insertPiece(Piece piece);
    int getRow();
    int getColumn();
    Optional<Piece> deletePiece();
    boolean isEmpty();
}
