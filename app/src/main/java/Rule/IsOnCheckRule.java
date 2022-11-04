package Rule;

import Board.Board;
import Generators.RulesPerPiece;
import Position.Position;
import Rule.types.MoveType;
import Rule.types.RuleResponse;

import java.util.ArrayList;
import java.util.List;

public class IsOnCheckRule {
    NoPieceCrash noPieceCrash = new NoPieceCrash();
    public boolean checkAllMovements(ArrayList<Position> positionsFromPieces, ArrayList<Position> possibleMovementsForKing, RulesPerPiece rules, Board board) {
        boolean[] forAllMoves = getAllHacks(positionsFromPieces, possibleMovementsForKing, rules, board);
        for (boolean forAllMove : forAllMoves) {
            if (!forAllMove) return false;
        }
        return true;
    }

    public boolean[] getAllHacks(ArrayList<Position> positionsFromPieces, ArrayList<Position> possibleMovementsForKing, RulesPerPiece rules, Board board){
        boolean[] forAllMoves = new boolean[possibleMovementsForKing.size()] ;
        for (int i = 0; i < possibleMovementsForKing.size(); i++) {
            for (Position positionsFromPiece : positionsFromPieces) {
                if (positionsFromPiece.getPiece().isPresent()) {
                    if (isMovePossibleWithoutCrash(board, possibleMovementsForKing.get(i), positionsFromPiece, rules.getRulesPerPiece(positionsFromPiece.getPiece().get()))
                            && !forAllMoves[i]) {
                        forAllMoves[i] = true;
                    }
                }
            }
        }
        return forAllMoves;
    }
    public ResponseCheck checkAnyMovement(ArrayList<Position> positionsFromPieces, ArrayList<Position> possibleMovementsForKing, RulesPerPiece rules, Board board){
        ResponseCheck responseCheck = new ResponseCheck();
        getAllHacks(responseCheck, positionsFromPieces, possibleMovementsForKing, rules, board);
        return responseCheck;
    }

    public void getAllHacks(ResponseCheck responseCheck, ArrayList<Position> positionsFromPieces, ArrayList<Position> possibleMovementsForKing, RulesPerPiece rules, Board board){
        for (int i = 0; i < possibleMovementsForKing.size(); i++) {
            for (Position positionsFromPiece : positionsFromPieces) {
                if (positionsFromPiece.getPiece().isPresent()) {
                    if (isMovePossibleWithoutCrash(board, possibleMovementsForKing.get(i), positionsFromPiece, rules.getRulesPerPiece(positionsFromPiece.getPiece().get()))) {
                        responseCheck.addPosition(positionsFromPiece);
                    }
                }
            }
        }
    }
    public boolean isMovePossibleWithoutCrash(Board board, Position positionTo, Position positionFrom, List<Rule> rules){
        for(Rule rule: rules){
            RuleResponse response = rule.isMovePossible(board, positionTo, positionFrom);
            if(response.isCorrect() && response.getMoveType() != MoveType.SKIP && response.getMoveType() != MoveType.HORSE){
                return noPieceCrash.isMovePossible(board, positionTo, positionFrom, response.getMoveType());
            }
        }
        return false;
    }
}
