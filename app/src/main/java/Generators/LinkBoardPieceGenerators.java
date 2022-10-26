package Generators;

import Board.Tuple;
import Position.Position;

import java.util.Map;

public class LinkBoardPieceGenerators {
    public static Position[][] linkBoardPieceGenerator(){
        Position[][] board = BoardGenerator.createBoard(new Tuple(8,8));
        PieceGenerator pieceGenerator = new PieceGenerator();
        Map<Tuple, PieceColor> pieceRelation = pieceGenerator.getInitialPositionForPieces();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                PieceColor possible = pieceRelation.get(new Tuple(i, j));
                if(possible != null){
                    board[i][j].insertPiece(pieceGenerator.generatePiece(possible.getColor(), possible.getPiece()));
                }
            }
        }
        return board;
    }
}
