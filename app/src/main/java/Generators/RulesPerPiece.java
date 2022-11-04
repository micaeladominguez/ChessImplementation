package Generators;

import Board.Board;
import Piece.Piece;
import Piece.PieceType;
import Position.Position;
import Rule.Rule;
import Rule.moves.*;
import Board.Tuple;
import java.util.ArrayList;
import java.util.EnumMap;

import Rule.CastlingRule;
import javafx.geometry.Pos;

public class RulesPerPiece {
    Tuple maxEdges;
    protected EnumMap<PieceType, ArrayList<Rule>> rulesPerPiece;
    public void generate() {
        rulesPerPiece = new EnumMap<>(PieceType.class);
        rulesPawn();
        rulesHorse();
        rulesRook();
        rulesBishop();
        rulesQueen();
        rulesKing();
        rulesSuperPawn();
        rulesSuperRook();
    }

    private void rulesSuperRook() {
        ArrayList<Rule> rulesForSuperRook = new ArrayList<>();
        rulesForSuperRook.add(new VerticalMoveRule(-1));
        rulesForSuperRook.add(new HorizontalRuleMove(-1));
        rulesForSuperRook.add(new DiagonalMoveRule(1));
        rulesPerPiece.put(PieceType.SUPER_ROOK, rulesForSuperRook);
    }

    private void rulesSuperPawn() {
        ArrayList<Rule> rulesForSuperPawn = new ArrayList<>();
        rulesForSuperPawn.add( new VerticalMoveRule(1));
        rulesForSuperPawn.add(new DiagonalMoveRule(1));
        rulesForSuperPawn.add(new RabbitRule(2));
        rulesPerPiece.put(PieceType.SUPER_PAWN, rulesForSuperPawn);
    }

    private void rulesKing() {
        ArrayList<Rule> rulesForKing = new ArrayList<>();
        rulesForKing.add(new CastlingRule());
        rulesForKing.add(new DiagonalMoveRule(1));
        rulesForKing.add(new HorizontalRuleMove(1));
        rulesForKing.add(new VerticalMoveRule(1));
        rulesPerPiece.put(PieceType.KING, rulesForKing);
    }

    private void rulesQueen() {
        ArrayList<Rule> rulesForQueen = new ArrayList<>();
        rulesForQueen.add(new DiagonalMoveRule(-1));
        rulesForQueen.add(new HorizontalRuleMove(-1));
        rulesForQueen.add(new VerticalMoveRule(-1));
        rulesPerPiece.put(PieceType.QUEEN, rulesForQueen);
    }

    private void rulesBishop() {
        ArrayList<Rule> rulesForBishop = new ArrayList<>();
        rulesForBishop.add(new DiagonalMoveRule(-1));
        rulesPerPiece.put(PieceType.BISHOP, rulesForBishop);
    }

    private void rulesRook() {
        ArrayList<Rule> rulesForRook = new ArrayList<>();
        rulesForRook.add(new VerticalMoveRule(-1));
        rulesForRook.add(new HorizontalRuleMove(-1));
        rulesPerPiece.put(PieceType.ROOK, rulesForRook);
    }

    private void rulesHorse() {
        ArrayList<Rule> rulesForHorse = new ArrayList<>();
        rulesForHorse.add(new HorseMove());
        rulesPerPiece.put(PieceType.HORSE, rulesForHorse);
    }

    private void rulesPawn() {
        ArrayList<Rule> rulesForPawn = new ArrayList<>();
        rulesForPawn.add( new VerticalMoveRule(1));
        rulesForPawn.add(new DiagonalMoveRule(1));
        rulesPerPiece.put(PieceType.PAWN, rulesForPawn);
    }

    public ArrayList<Rule> getRulesPerPiece(Piece piece){
         return rulesPerPiece.get(piece.getName());
    }
    public ArrayList<Position> getPossibleMovementsForKing(Board board, Position kingPosition) {
        ArrayList<Position> positions = new ArrayList<>();
        defineTuple(board);
        positions.add(kingPosition);
        addPosition(0, -1, positions, board, kingPosition);
        addPosition(0, 1, positions, board, kingPosition);
        addPosition(-1, 0,positions, board, kingPosition );
        addPosition(1, 0,positions, board, kingPosition );
        addPosition(-1, -1,positions, board, kingPosition );
        addPosition(-1, 1,positions, board, kingPosition );
        addPosition(1, -1,positions, board, kingPosition );
        addPosition(1, 1,positions, board, kingPosition );
        return positions;
    }

    private void defineTuple(Board board) {
        if(this.maxEdges == null) this.maxEdges = board.getMaxEdges();
    }

    private void addPosition(int addRow, int addColumn, ArrayList<Position> positions, Board board, Position kingPosition) {
        if(checkBoardRule(kingPosition.getRow() + addRow, kingPosition.getColumn() + addColumn)){
            if(board.getBoard()[kingPosition.getRow() + addRow][kingPosition.getColumn() + addColumn].isEmpty() ||
                            (board.getBoard()[kingPosition.getRow() + addRow][kingPosition.getColumn() + addColumn].getPiece().isPresent() &&
                                    board.getBoard()[kingPosition.getRow() + addRow][kingPosition.getColumn() + addColumn].getPiece().get().getColor() != kingPosition.getPiece().get().getColor())){
                positions.add(board.getBoard()[kingPosition.getRow() + addRow][kingPosition.getColumn() + addColumn]);
            }
        }
    }

    private boolean checkBoardRule(int row, int column) {
        return row < maxEdges.getRow() && column < maxEdges.getColumn()
                && row >= 0 && column >= 0;
    }
}
