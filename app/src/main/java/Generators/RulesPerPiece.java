package Generators;

import Board.Board;
import Piece.Piece;
import Piece.Pieces;
import Position.Position;
import Rule.Rule;
import Rule.moves.VerticalMoveRule;
import Rule.moves.DiagonalMoveRule;
import java.util.ArrayList;
import java.util.EnumMap;
import Rule.moves.HorseMove;
import Rule.moves.HorizontalRuleMove;
import Rule.CastlingRule;
public class RulesPerPiece {
    protected EnumMap<Pieces, ArrayList<Rule>> rulesPerPiece;

    public void generateRules(){
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

    }
    public ArrayList<Rule> getRulesPerPiece(Piece piece){
         return rulesPerPiece.get(piece.getName());
    };

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

    public Rule getSpecificRule(String ruleType){
        if(ruleType == "HORIZONTAL") return new HorizontalRuleMove(-1);
        else return new VerticalMoveRule(-1);
    }
}
