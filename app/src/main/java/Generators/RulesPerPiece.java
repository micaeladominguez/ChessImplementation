package Generators;

import Board.Board;
import Piece.Piece;
import Piece.Pieces;
import Position.Position;
import Rule.Rule;
import Rule.moves.*;

import java.util.ArrayList;
import java.util.EnumMap;

import Rule.CastlingRule;
public class RulesPerPiece implements Generator {
    protected EnumMap<Pieces, ArrayList<Rule>> rulesPerPiece;
    @Override
    public void generate() {
        rulesPerPiece = new EnumMap<>(Pieces.class);
        //RULES PER PAWN
        ArrayList<Rule> rulesForPawn = new ArrayList<>();
        rulesForPawn.add( new VerticalMoveRule(1));
        rulesForPawn.add(new DiagonalMoveRule(1));
        rulesPerPiece.put(Pieces.PAWN, rulesForPawn);
        //RULES PER HORSE
        ArrayList<Rule> rulesForHorse = new ArrayList<>();
        rulesForHorse.add(new HorseMove());
        rulesPerPiece.put(Pieces.HORSE, rulesForHorse);
        //RULES PER ROOK
        ArrayList<Rule> rulesForRook = new ArrayList<>();
        rulesForRook.add(new VerticalMoveRule(-1));
        rulesForRook.add(new HorizontalRuleMove(-1));
        rulesPerPiece.put(Pieces.ROOK, rulesForRook);
        //RULES PER BISHOP
        ArrayList<Rule> rulesForBishop = new ArrayList<>();
        rulesForBishop.add(new DiagonalMoveRule(-1));
        rulesPerPiece.put(Pieces.BISHOP, rulesForBishop);
        //RULES PER QUEEN
        ArrayList<Rule> rulesForQueen = new ArrayList<>();
        rulesForQueen.add(new DiagonalMoveRule(-1));
        rulesForQueen.add(new HorizontalRuleMove(-1));
        rulesForQueen.add(new VerticalMoveRule(-1));
        rulesPerPiece.put(Pieces.QUEEN, rulesForQueen);
        //RULES PER KING
        ArrayList<Rule> rulesForKing = new ArrayList<>();
        rulesForKing.add(new CastlingRule());
        rulesForKing.add(new DiagonalMoveRule(1));
        rulesForKing.add(new HorizontalRuleMove(1));
        rulesForKing.add(new VerticalMoveRule(1));
        rulesPerPiece.put(Pieces.KING, rulesForKing);
        //RULER PER SUPER PAWN
        ArrayList<Rule> rulesForSuperPawn = new ArrayList<>();
        rulesForSuperPawn.add( new VerticalMoveRule(1));
        rulesForSuperPawn.add(new DiagonalMoveRule(1));
        rulesForSuperPawn.add(new RabbitRule(2));
        rulesPerPiece.put(Pieces.SUPER_PAWN, rulesForSuperPawn);
        //RULES PER SUPER ROOK
        ArrayList<Rule> rulesForSuperRook = new ArrayList<>();
        rulesForSuperRook.add(new VerticalMoveRule(-1));
        rulesForSuperRook.add(new HorizontalRuleMove(-1));
        rulesForSuperRook.add(new DiagonalMoveRule(1));
        rulesPerPiece.put(Pieces.SUPER_ROOK, rulesForSuperRook);

    }
    public ArrayList<Rule> getRulesPerPiece(Piece piece){
         return rulesPerPiece.get(piece.getName());
    }
    public ArrayList<Position> getPossibleMovementsForKing(Board board, Position kingPosition) {
        ArrayList<Position> positions = new ArrayList<>();
        positions.add(kingPosition);
        int row = kingPosition.getRow();
        int column = kingPosition.getColumn();
        if(column - 1 >= 0){
            if(board.getBoard()[row][column - 1].isEmpty() || (board.getBoard()[row][column-1].getPiece().isPresent() && board.getBoard()[row][column-1].getPiece().get().getColor() != kingPosition.getPiece().get().getColor())){
                positions.add(board.getBoard()[row][column-1]);
            }
        }
        if(column + 1 < board.getMaxEdges().getColumn()){
            if(board.getBoard()[row][column + 1].isEmpty() || (board.getBoard()[row][column-1].getPiece().isPresent() && board.getBoard()[row][column+1].getPiece().get().getColor() != kingPosition.getPiece().get().getColor())){
                positions.add(board.getBoard()[row][column+1]);
            }
        }
        if(row - 1 >= 0){
            if(board.getBoard()[row - 1][column ].isEmpty() || (board.getBoard()[row - 1][column].getPiece().isPresent() && board.getBoard()[row - 1][column].getPiece().get().getColor() != kingPosition.getPiece().get().getColor())){
                positions.add(board.getBoard()[row - 1][column]);
            }
        }
        if(row + 1 < board.getMaxEdges().getRow()){
            if(board.getBoard()[row + 1][column].isEmpty() || (board.getBoard()[row + 1][column].getPiece().isPresent() && board.getBoard()[row + 1][column].getPiece().get().getColor() != kingPosition.getPiece().get().getColor())){
                positions.add(board.getBoard()[row + 1][column]);
            }
        }
        if(row - 1 >= 0 && column - 1 >= 0 ){
            if(board.getBoard()[row - 1][column - 1].isEmpty() || (board.getBoard()[row - 1][column - 1].getPiece().isPresent() && board.getBoard()[row - 1][column - 1].getPiece().get().getColor() != kingPosition.getPiece().get().getColor())){
                positions.add(board.getBoard()[row - 1][column - 1]);
            }
        }
        if(row - 1 >= 0 && column + 1 < board.getMaxEdges().getColumn() ){
            if(board.getBoard()[row - 1][column + 1].isEmpty() || (board.getBoard()[row - 1][column + 1].getPiece().isPresent() && board.getBoard()[row - 1][column + 1].getPiece().get().getColor() != kingPosition.getPiece().get().getColor())){
                positions.add(board.getBoard()[row - 1][column + 1]);
            }
        }
        if(row + 1 < board.getMaxEdges().getRow() && column - 1 >= 0){
            if(board.getBoard()[row + 1][column - 1].isEmpty() || (board.getBoard()[row + 1][column - 1].getPiece().isPresent() && board.getBoard()[row + 1][column - 1].getPiece().get().getColor() != kingPosition.getPiece().get().getColor())){
                positions.add(board.getBoard()[row + 1][column - 1]);
            }
        }
        if(row + 1 < board.getMaxEdges().getRow() && column + 1 < board.getMaxEdges().getColumn()){
            if(board.getBoard()[row + 1][column + 1].isEmpty() || (board.getBoard()[row + 1][column + 1].getPiece().isPresent() && board.getBoard()[row + 1][column + 1].getPiece().get().getColor() != kingPosition.getPiece().get().getColor())){
                positions.add(board.getBoard()[row + 1][column + 1]);
            }
        }
        return positions;
    }
}
