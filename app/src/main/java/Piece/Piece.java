package Piece;


public class Piece implements PieceInterface {
    private static int count = 0;
    public long getId() {
        return id;
    }
    protected final long id;
    protected final ColorType color;
    protected int countOfMovements;
    protected PieceType type ;
    public Piece(ColorType color, PieceType type) {
        this.id = ++count;
        this.color = color;
        this.countOfMovements = 0;
        this.type = type;
    }

    @Override
    public ColorType getColor() {
        return this.color;
    }

    @Override
    public PieceType getName() {
        return type;
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
        if(type == PieceType.ROOK || type == PieceType.KING || type == PieceType.PAWN){
            return " { " + color.toString() + "," + type.toString() + " } ";
        }else if(type == PieceType.QUEEN || type == PieceType.HORSE ){
            return " {" + color.toString() + "," + type.toString() + " } ";
        }else{
            return " {" + color.toString() + "," + type.toString() + "} ";
        }

    }

    public void changeType(PieceType pieces){
        this.type = pieces;
    }
}
