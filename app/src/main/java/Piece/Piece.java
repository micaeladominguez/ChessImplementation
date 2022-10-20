package Piece;

import java.util.concurrent.atomic.AtomicLong;

public class Piece implements PieceInterface {
    private static int count = 0;
    public long getId() {
        return id;
    }
    protected final long id;
    protected final Colors color;
    protected boolean dead;
    protected int countOfMovements;
    protected  Pieces type ;

    public Piece(Colors color, Pieces type) {
        this.id = ++count;
        this.color = color;
        this.dead = false;
        this.countOfMovements = 0;
        this.type = type;
    }

    @Override
    public Colors getColor() {
        return this.color;
    }

    @Override
    public Pieces getName() {
        return type;
    }
    @Override
    public boolean isDead() {
        return this.dead;
    }

    @Override
    public int moves() {
        return this.countOfMovements;
    }

    @Override
    public void addMove() {
        countOfMovements = countOfMovements + 1;
    }

    public String toString(){
        if(type == Pieces.ROOK || type == Pieces.KING || type == Pieces.PAWN){
            return " { " + color.toString() + "," + type.toString() + " } ";
        }else if(type == Pieces.QUEEN || type == Pieces.HORSE ){
            return " {" + color.toString() + "," + type.toString() + " } ";
        }else{
            return " {" + color.toString() + "," + type.toString() + "} ";
        }

    }

    public void changeType(Pieces pieces){
        this.type = pieces;
    }
}
