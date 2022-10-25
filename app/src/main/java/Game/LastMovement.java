package Game;

import Piece.Piece;
import Position.Position;

public class LastMovement {
    protected Position position;
    protected Piece piece;


    public LastMovement() {
    }

    public LastMovement(Position position, Piece piece) {
        this.position = position;
        this.piece = piece;
    }

    public Position getPosition() {
        return position;
    }

    public Piece getPiece() {
        return piece;
    }
    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
    public boolean isEmpty(){
        return piece == null && position == null;
    }
    public String toString(){
        return position.toString() + " piece: " + piece.toString();
    }

}
