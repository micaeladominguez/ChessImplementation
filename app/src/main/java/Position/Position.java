package Position;

import Piece.Piece;

import java.util.Optional;

public class Position implements PositionInterface{
    protected Optional<Piece> possiblePiece;
    protected final int row;
    protected final int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.possiblePiece = Optional.empty();
    }


    @Override
    public Optional<Piece> getPiece() {
        return this.possiblePiece;
    }

    public String toString(){
        if(this.possiblePiece.isPresent()) return pieceToString();
        else return "{  } ";
    }

    public String pieceToString(){
        return possiblePiece.get().toString();
    }

    @Override
    public void insertPiece(Piece piece) {
        this.possiblePiece = Optional.ofNullable(piece);
    }

    public int getRow(){
        return this.row;
    }
    public int getColumn(){
        return this.column;
    }

    @Override
    public Optional<Piece> deletePiece() {
        Optional<Piece> returned = this.getPiece();
        this.possiblePiece = Optional.empty();
        return returned;
    }

    @Override
    public boolean isEmpty(){
        return this.possiblePiece.isEmpty();
    }


}
