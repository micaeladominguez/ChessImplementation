package Rule;

import Board.Board;
import Generators.PieceColor;
import Generators.RulesPerPiece;
import Move.Move;
import Piece.ColorType;
import Piece.PieceOperator;
import Piece.PieceType;
import Position.Position;
import Rule.types.MoveType;
import Rule.types.RuleResponse;

import java.util.ArrayList;
import java.util.List;

public class MovementValidator {
    private final MaxBoardRule maxBoardRule = new MaxBoardRule();
    private final NoPieceCrash noPieceCrash = new NoPieceCrash();
    private final IsOnCheckRule isOnCheckRule = new IsOnCheckRule();
    public boolean isMoveInBoard(Board board, int column, int row){
        return maxBoardRule.isMovePossible(board,column, row);
    }

    public boolean isMovePossible(Board board, Move move, List<Rule> rules){
        for(Rule rule: rules){
            RuleResponse response = rule.isMovePossible(board, move.getPositionTo(), move.getPositionFrom());
            if(checkCrashPiece(response)){
                 return crashPiece(board, move, response) &&
                         sameColorRule(move.getPositionTo(), move.getPositionFrom());
            }else{
                return sameColorRule(move.getPositionTo(), move.getPositionFrom());
            }
        }
        return false;
    }

    private boolean crashPiece(Board board, Move move, RuleResponse response) {
        return noPieceCrash.isMovePossible(board, move.getPositionTo(), move.getPositionFrom(), response.getMoveType());
    }

    private  boolean checkCrashPiece(RuleResponse response) {
        return response.isCorrect() && response.getMoveType() != MoveType.SKIP && response.getMoveType() != MoveType.HORSE;
    }

    public boolean isOnCheckRule(Board board, ColorType reference, RulesPerPiece rules){
        ColorType color = reference == ColorType.BLACK ?  ColorType.WHITE : ColorType.BLACK;
        //AGARRO LA POSICION DE EL KING DE LA OTRA PERSONA
        Position kingPosition = board.getKingPosition(color);
        ArrayList<Position> possibleMovementsForKing = rules.getPossibleMovementsForKing(board,kingPosition);
        //AGARRO MIS PIEZAS
        ArrayList<Position> positionsFromPieces = board.searchForPiece(reference);
        //ME FIJO SI ESTA EN HACK LA OTRA PERSONA
        return isOnCheckRule.checkAllMovements(positionsFromPieces, possibleMovementsForKing, rules, board);
    }
    public boolean imOnCheckRule(Board board, ColorType reference, RulesPerPiece rules){
        ColorType color = ColorType.BLACK;
        if(reference == ColorType.BLACK){
            color = ColorType.WHITE;
        }
        Position kingPosition = board.getKingPosition(reference);
        ArrayList<Position> possibleMovementsForKing = rules.getPossibleMovementsForKing(board,kingPosition);
        ArrayList<Position> positionsFromPieces = board.searchForPiece(color);
        return isOnCheckRule.checkAllMovements(positionsFromPieces, possibleMovementsForKing, rules, board);
    }
    public boolean sameColorRule(Position positionTo, Position positionFrom){
        if(PieceOperator.isRook(positionTo.getPiece()) && PieceOperator.isKing(positionFrom.getPiece())
        && PieceOperator.sameColor(positionTo.getPiece(), positionFrom.getPiece())){
            return true;
        }
        return positionTo.getPiece().isEmpty() ||
                positionTo.getPiece().isPresent() && positionFrom.getPiece().isPresent() &&
                !PieceOperator.sameColor(positionTo.getPiece(),positionFrom.getPiece());
    }

    public ResponseCheck imOnCheckRuleAtLeastOne(Board board, ColorType reference, RulesPerPiece rules) {
        ColorType color = ColorType.BLACK;
        if(reference == ColorType.BLACK){
            color = ColorType.WHITE;
        }
        Position kingPosition = board.getKingPosition(reference);
        ArrayList<Position> possibleMovementsForKing = new ArrayList<>();
        possibleMovementsForKing.add(kingPosition);
        ArrayList<Position> positionsFromPieces = board.searchForPiece(color);
        return isOnCheckRule.checkAnyMovement(positionsFromPieces, possibleMovementsForKing, rules, board);
    }
}
