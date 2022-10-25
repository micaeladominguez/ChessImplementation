package Rule;

import Board.Board;
import Generators.RulesPerPiece;
import Piece.Colors;
import Piece.Pieces;
import Position.Position;
import Rule.types.MoveType;
import Rule.types.RuleResponse;

import java.util.ArrayList;
import java.util.List;

public class MovementValidator {
    private final MaxBoardRule maxBoardRule = new MaxBoardRule();
    private final NoPieceCrash noPieceCrash = new NoPieceCrash();
    private final IsOnCheckRule isOnCheckRule = new IsOnCheckRule();
    public boolean isMoveOutOfBoard(Board board,int column, int row){
        return maxBoardRule.isMovePossible(board,column, row);
    }

    public boolean isMovePossible(Board board, Position positionTo, Position positionFrom, List<Rule> rules){
        for(Rule rule: rules){
            RuleResponse response = rule.isMovePossible(board, positionTo, positionFrom);
            if(response.isResponse() && response.getMoveType() != MoveType.SKIP && response.getMoveType() != MoveType.HORSE){
                 boolean crashPiece = noPieceCrash.isMovePossible(board, positionTo, positionFrom, response.getMoveType());
                 return crashPiece && sameColorRule(positionTo, positionFrom);
            }else if(response.isResponse() && (response.getMoveType() != MoveType.SKIP || response.getMoveType() != MoveType.HORSE)){
                return sameColorRule(positionTo, positionFrom);
            }
        }
        return false;
    }

    public boolean isOnCheckRule(Board board, Colors reference, RulesPerPiece rules){
        Colors color = Colors.BLACK;
        if(reference == Colors.BLACK){
            color = Colors.WHITE;
        }
        //TERMINE DE MOVER YO y me fijo si deje en hacke a la otra persona
        //AGARRO LA POSICION DE EL KING DE LA OTRA PERSONA
        Position kingPosition = board.getKingPosition(color);
        ArrayList<Position> possibleMovementsForKing = rules.getPossibleMovementsForKing(board,kingPosition);
        //AGARRO MIS PIEZAS
        ArrayList<Position> positionsFromPieces = board.searchForPiece(reference);
        //ME FIJO SI ESTA EN HACKE LA OTRA PERSONA
        return isOnCheckRule.checkAllMovements(positionsFromPieces, possibleMovementsForKing, rules, board);
    }
    public boolean imOnCheckRule(Board board, Colors reference, RulesPerPiece rules){
        Colors color = Colors.BLACK;
        if(reference == Colors.BLACK){
            color = Colors.WHITE;
        }
        Position kingPosition = board.getKingPosition(reference);
        ArrayList<Position> possibleMovementsForKing = rules.getPossibleMovementsForKing(board,kingPosition);
        ArrayList<Position> positionsFromPieces = board.searchForPiece(color);
        return isOnCheckRule.checkAllMovements(positionsFromPieces, possibleMovementsForKing, rules, board);
    }
    public boolean sameColorRule(Position positionTo, Position positionFrom){
        if(positionFrom.getPiece().isPresent() && positionFrom.getPiece().get().getName() == Pieces.KING
        && positionTo.getPiece().isPresent() && positionTo.getPiece().get().getName() == Pieces.ROOK
        && positionFrom.getPiece().get().getColor() == positionTo.getPiece().get().getColor()){
            return true;
        }
        return positionTo.getPiece().isEmpty() || positionTo.getPiece().get().getColor() != positionFrom.getPiece().get().getColor();
    }

    public boolean imOnCheckRuleAtLeastOne(Board board, Colors reference, RulesPerPiece rules) {
        Colors color = Colors.BLACK;
        if(reference == Colors.BLACK){
            color = Colors.WHITE;
        }
        Position kingPosition = board.getKingPosition(reference);
        ArrayList<Position> possibleMovementsForKing = new ArrayList<>();
        possibleMovementsForKing.add(kingPosition);
        ArrayList<Position> positionsFromPieces = board.searchForPiece(color);
        return isOnCheckRule.checkAnyMovement(positionsFromPieces, possibleMovementsForKing, rules, board);
    }
}
