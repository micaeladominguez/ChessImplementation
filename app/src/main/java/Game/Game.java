package Game;

import Board.Board;
import Generators.PlayerGenerator;
import Generators.RulesPerPiece;
import Piece.Colors;
import Piece.Piece;
import Player.Player;
import Position.Position;
import Rule.MovementValidator;

import java.sql.Timestamp;
import java.util.Optional;

public class Game {
    protected Board board = new Board(8,8);
    protected Timestamp startTime;
    protected Player[] activePlayers;
    protected RulesPerPiece rulesPerPiece = new RulesPerPiece();
    protected Colors reference;
    protected MovementValidator movementValidator = new MovementValidator();

    public Board getBoard(){
        return this.board;
    }
    public Colors getReference(){
        return reference;
    }

    public void startGame(){
        this.startTime =  new Timestamp(System.currentTimeMillis());
        this.activePlayers = PlayerGenerator.generatePlayers();
        if(activePlayers[0].getColors() == Colors.WHITE) this.reference = Colors.WHITE;
        else this.reference = Colors.BLACK;
        this.rulesPerPiece.generateRules();
    }

    public GameResponse possibleMovement(int rowTo, int columnTo, int rowFrom, int columnFrom) {
        if(!movementValidator.isMoveOutOfBoard(board, columnTo, rowTo )) return new GameResponse( "The move is not valid", TypeOfResponse.INCORRECT_MOVE );
        Position positionFrom = board.getBoard()[rowFrom][columnFrom];
        Position positionTo = board.getBoard()[rowTo][columnTo];
        if(!checkPiece(positionFrom)) return new GameResponse("No piece selected", TypeOfResponse.INCORRECT_MOVE);
        if(!checkTurn(positionFrom))  return new GameResponse("It's not your turn", TypeOfResponse.INCORRECT_MOVE);
        if( movementValidator.isMovePossible(board,positionTo, positionFrom,rulesPerPiece.getRulesPerPiece(positionFrom.getPiece().get()))){
            Optional<Piece> pieceToPut =  board.movePieceFromPosition(positionFrom);
            pieceToPut.ifPresent(piece -> board.movePieceToPosition(positionTo, piece, positionFrom));
            if(movementValidator.imOnCheckRule(board, reference, rulesPerPiece )) return new GameResponse("The game is finish, the winner is ", TypeOfResponse.GAME_OVER);
            changeTurn();
            return new GameResponse("Ok move", TypeOfResponse.CORRECT_MOVE);
        }else{
            return new GameResponse("This movement is not possible", TypeOfResponse.INCORRECT_MOVE);
        }
    }

    private boolean checkPiece(Position positionFrom) {
        return positionFrom.getPiece().isPresent();
    }

    private boolean checkTurn(Position positionFrom) {
        if(positionFrom.isEmpty()) return false;
        return positionFrom.getPiece().isPresent() && positionFrom.getPiece().get().getColor() == reference;
    }

    private void changeTurn(){
        if(reference == Colors.WHITE){
            this.reference = Colors.BLACK;
        }else{
            this.reference = Colors.WHITE;
        }
    }
}
