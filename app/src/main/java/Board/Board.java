package Board;


import Game.LastMovement;
import Generators.LinkBoardPieceGenerators;
import Piece.Colors;
import Piece.Piece;
import Piece.Pieces;
import Position.Position;

import java.util.ArrayList;
import java.util.Optional;

public class Board {
    protected Position[][] board;
    protected Tuple whiteKingPointer ;
    protected Tuple blackKingPointer;

    protected Tuple maxEdges;

    public Board(int maxColumn, int maxRow ) {
        this.maxEdges = new Tuple(maxColumn, maxRow);
        generateBoard();
    }

    private void generateBoard() {
        LinkBoardPieceGenerators linkBoardPieceGenerators = new LinkBoardPieceGenerators();
        linkBoardPieceGenerators.generate();
        this.board = linkBoardPieceGenerators.getBoard();
        this.whiteKingPointer = new Tuple(0,4);
        this.blackKingPointer = new Tuple(7, 4);
    }
    public Position[][] getBoard(){
        return this.board;
    }
    public Position getKingPosition(Colors color) {
        if(color == Colors.WHITE){
            return this.board[whiteKingPointer.row][whiteKingPointer.column];
        }else{
            return this.board[blackKingPointer.row][blackKingPointer.column];
        }
    }
    public ArrayList<Position> searchForPiece( Colors color){
        ArrayList<Position> positions = new ArrayList<>();
        for (int i = 0; i < maxEdges.getRow(); i++) {
            for (int j = 0; j < maxEdges.getColumn(); j++) {
                if(!this.board[i][j].isEmpty() && this.board[i][j].getPiece().isPresent()
                        && this.board[i][j].getPiece().get().getColor() == color) {
                        positions.add(board[i][j]);
                }
            }
        }
        return positions;
    }
    public void movePieceToPosition(Position position, Piece piece, Position positionFrom){
        if(piece.getName() == Pieces.KING && positionFrom.getRow() == position.getRow()
                && Math.abs(positionFrom.getColumn() - position.getColumn()) > 0){
                    doCastlingRotation(positionFrom, position, piece);
        }else if(piece.getName() == Pieces.PAWN && (position.getRow() == 0 ||
                position.getRow() + 1 == maxEdges.row)){
            piece.changeType(Pieces.QUEEN);
            movePiece(position, piece);
        } else{
            movePiece(position, piece);
            if(piece.getName() == Pieces.KING){
                movePiece(position, piece);
                if(piece.getColor() == Colors.WHITE) {
                    this.whiteKingPointer.setColumn(position.getColumn());
                    this.whiteKingPointer.setRow(position.getRow());
                }else{
                    this.blackKingPointer.setColumn(position.getColumn());
                    this.blackKingPointer.setRow(position.getRow());
                }
            }
        }
    }

    private void movePiece(Position position, Piece piece){
        if(position != null && position.isEmpty()){
            piece.addMove();
            position.insertPiece(piece);
        }else{
            piece.addMove();
            this.movePieceFromPosition(position);
            position.insertPiece(piece);
        }
    }



    public Optional<Piece> movePieceFromPosition(Position position){
        if(!position.isEmpty()){
            return position.deletePiece();
        }else{
            return Optional.empty();
        }
    }

    public Tuple getMaxEdges() {
        return maxEdges;
    }


    public void doCastlingRotation(Position positionFromKing, Position positionFromRook, Piece piece) {
        int row = positionFromKing.getRow();
        int difference = positionFromKing.getColumn() - positionFromRook.getColumn();
        Optional<Piece> rook = movePieceFromPosition(positionFromRook);
        if(difference == 4){
            if( rook.isPresent()){
                movePiece(board[row][1],piece);
                movePiece(board[row][2],rook.get());
                if(piece.getColor() == Colors.WHITE) {
                    this.whiteKingPointer.setColumn(1);
                    this.whiteKingPointer.setRow(row);
                }else{
                    this.blackKingPointer.setColumn(1);
                    this.blackKingPointer.setRow(row);
                }
            }
        }else{
            if(rook.isPresent()){
                movePiece(board[row][6],piece);
                movePiece(board[row][5],rook.get());
                if(piece.getColor() == Colors.WHITE) {
                    this.whiteKingPointer.setColumn(6);
                    this.whiteKingPointer.setRow(row);
                }else{
                    this.blackKingPointer.setColumn(6);
                    this.blackKingPointer.setRow(row);
                }
            }
        }
    }

    public void rollBackLastMovement(Position positionTo, LastMovement lastMovement) {
        movePieceFromPosition(positionTo);
        movePieceToPosition(lastMovement.getPosition(), lastMovement.getPiece(), positionTo);
    }
}
