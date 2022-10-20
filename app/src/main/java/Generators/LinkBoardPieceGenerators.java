package Generators;

import Board.Tuple;
import Piece.Colors;
import Piece.Pieces;
import Position.Position;

public class LinkBoardPieceGenerators {
    public static Position[][] linkBoardPieceGenerator(){
        Position[][] board = BoardGenerator.createBoard(new Tuple(8,8));
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(i == 0){
                    if(j == 0 || j == board[0].length - 1){
                        board[i][j].insertPiece( PieceGenerator.generatePiece(Colors.WHITE, Pieces.ROOK) );
                    }else if(j == 1 || j == board[0].length - 2){
                        board[i][j].insertPiece( PieceGenerator.generatePiece(Colors.WHITE, Pieces.HORSE) );
                    }else if(j == 2 || j == board[0].length - 3){
                        board[i][j].insertPiece( PieceGenerator.generatePiece(Colors.WHITE, Pieces.BISHOP) );
                    }else if(j == 3){
                        board[i][j].insertPiece(PieceGenerator.generatePiece(Colors.WHITE, Pieces.QUEEN) );
                    }else{
                        board[i][j].insertPiece(PieceGenerator.generatePiece(Colors.WHITE, Pieces.KING) );
                    }
                }else if(i == 1){
                    board[i][j].insertPiece( PieceGenerator.generatePiece(Colors.WHITE, Pieces.PAWN) );
                }else if(i == 6){
                    board[i][j].insertPiece( PieceGenerator.generatePiece(Colors.BLACK, Pieces.PAWN) );
                }else if(i == 7){
                    if(j == 0 || j == board[0].length - 1){
                        board[i][j].insertPiece( PieceGenerator.generatePiece(Colors.BLACK, Pieces.ROOK) );
                    }else if(j == 1 || j == board[0].length - 2){
                        board[i][j].insertPiece( PieceGenerator.generatePiece(Colors.BLACK, Pieces.HORSE) );
                    }else if(j == 2 || j == board[0].length - 3){
                        board[i][j].insertPiece( PieceGenerator.generatePiece(Colors.BLACK, Pieces.BISHOP) );
                    }else if(j == 3){
                        board[i][j].insertPiece(PieceGenerator.generatePiece(Colors.BLACK, Pieces.KING) );
                    }else{
                        board[i][j].insertPiece(PieceGenerator.generatePiece(Colors.BLACK, Pieces.QUEEN) );
                    }
                }
            }
        }
        return board;
    }
}
