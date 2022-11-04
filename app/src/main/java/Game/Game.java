package Game;

import Board.Board;
import Generators.PlayerGenerator;
import Generators.RulesPerPiece;
import Piece.ColorType;
import Piece.Piece;
import Piece.PieceType;
import Player.Player;
import Position.Position;
import Rule.MovementValidator;
import Rule.ResponseCheck;
import Piece.PieceOperator;
import Move.Move;
import Rule.Rule;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Optional;

public class Game {
    protected Board board;
    protected LastMovement lastMovement;
    protected Player[] activePlayers;
    protected RulesPerPiece rulesPerPiece = new RulesPerPiece();
    protected ColorType reference;
    protected MovementValidator movementValidator = new MovementValidator();

    public Board getBoard(){
        return this.board;
    }
    public ColorType getReference(){
        return reference;
    }

    public void startGame(){
        this.board = defineBoard();
        this.activePlayers = getActivePlayers();
        defineStarterPlayer();
        this.lastMovement = new LastMovement();
        this.rulesPerPiece.generate();
    }

    private Player[]  getActivePlayers() {
        PlayerGenerator playerGenerator = getPlayerGenerator();
        return playerGenerator.getPlayers();
    }

    private void defineStarterPlayer() {
        if(activePlayers[0].getColors() == ColorType.WHITE) this.reference = ColorType.WHITE;
        else this.reference = ColorType.BLACK;
    }

    @NotNull
    private PlayerGenerator getPlayerGenerator() {
        PlayerGenerator playerGenerator = new PlayerGenerator();
        playerGenerator.generate();
        return playerGenerator;
    }

    private Board defineBoard() {
        return new Board(GameVariables.getRegularGameRowSize(),GameVariables.getRegularGameColumnSize(),
                        GameVariables.getWhKing(),GameVariables.getBlKing());
    }

    public GameResponse possibleMovement(int rowTo, int columnTo, int rowFrom, int columnFrom) {
        GameResponse gameResponse = validateInput(rowTo, columnTo, rowFrom, columnFrom);
        if(gameResponse != null) return gameResponse;
        Move move = new Move(board.getPosition(rowFrom, columnFrom), board.getPosition(rowTo, columnTo));
        return checkIfMoveIsPossible(move);
    }

    @NotNull
    private GameResponse checkIfMoveIsPossible(Move move) {
        if(movementValidator.isMovePossible(board, move, rulesFromMove(move))){
            movePiece(move);
            if(checkIfImInCheck()){
                rollBackMove(move);
                return new GameResponse("You're in hack, please move another piece", TypeOfResponse.INCORRECT_MOVE);
            }
            if(eatKing(move.getPositionTo()))
                return new GameResponse("The game is finish, the winner is ", TypeOfResponse.GAME_OVER);
            if(isOnCheckRule())
                return new GameResponse("The game is finish, the winner is ", TypeOfResponse.GAME_OVER);
            changeTurn();
            return new GameResponse("Ok move", TypeOfResponse.CORRECT_MOVE);
        }else{

            return new GameResponse("This movement is not possible", TypeOfResponse.INCORRECT_MOVE);
        }
    }

    private ArrayList<Rule> rulesFromMove(Move move) {
        return rulesPerPiece.getRulesPerPiece(move.getPositionFrom().getPiece().get());
    }

    private boolean isOnCheckRule() {
        return movementValidator.isOnCheckRule(board, reference, rulesPerPiece);
    }

    private void rollBackMove(Move move) {
        board.rollBackLastMovement(move.getPositionTo(), this.lastMovement);
    }

    private void movePiece(Move move) {
        setLastMovement(move.getPositionFrom());
        Optional<Piece> pieceToPut =  board.movePieceFromPosition(move.getPositionFrom());
        pieceToPut.ifPresent(piece -> board.movePieceToPosition(move.getPositionTo(), piece, move.getPositionFrom()));
    }


    private boolean checkIfImInCheck() {
        ResponseCheck responseCheck = movementValidator.imOnCheckRuleAtLeastOne(board, reference, rulesPerPiece);
        log(responseCheck);
        return responseCheck.isOnCheck();
    }


    private void log(ResponseCheck responseCheck) {
        for(Position position : responseCheck.getWhichOnes()){
            System.out.println(position.toString());
        }
        System.out.println("I'm on check" + responseCheck.isOnCheck());
    }

    private GameResponse validateInput(int rowTo, int columnTo, int rowFrom, int columnFrom) {
        if(!moveInBoard(rowTo, columnTo)) return new GameResponse( "The move is not valid", TypeOfResponse.INCORRECT_MOVE );
        if(!checkPiece(rowFrom, columnFrom)) return new GameResponse("No piece selected", TypeOfResponse.INCORRECT_MOVE);
        if(!checkTurn(rowFrom, columnFrom))  return new GameResponse("It's not your turn", TypeOfResponse.INCORRECT_MOVE);
        return null;
    }

    private boolean moveInBoard(int rowTo, int columnTo) {
        return movementValidator.isMoveInBoard(board, columnTo, rowTo);
    }

    private boolean eatKing(Position positionTo) {
        return positionTo.getPiece().isPresent() &&
                positionTo.getPiece().get().getName() == PieceType.KING;
    }

    private void setLastMovement(Position positionFrom) {
        if(positionFrom.getPiece().isPresent()){
            this.lastMovement = new LastMovement(positionFrom, positionFrom.getPiece().get());
        }
    }

    private boolean checkPiece(int rowFrom, int columnFrom) {
        if(board.getPieceIn(rowFrom, columnFrom).isEmpty()) return false;
        return board.getPieceIn(rowFrom, columnFrom).isPresent();
    }

    private boolean checkTurn(int rowFrom, int columnFrom) {
        return  board.getPieceIn(rowFrom, columnFrom).isPresent()
                    && board.getPieceIn(rowFrom, columnFrom).get().getColor() == reference;
    }

    private void changeTurn(){
        if(reference == ColorType.WHITE){
            this.reference = ColorType.BLACK;
        }else{
            this.reference = ColorType.WHITE;
        }
    }
}
