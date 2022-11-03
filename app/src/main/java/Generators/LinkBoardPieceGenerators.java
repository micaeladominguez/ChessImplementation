package Generators;

import Board.Tuple;
import Position.Position;

import java.util.Map;

public class LinkBoardPieceGenerators implements  Generator{
     Position[][]  board;
    public Position[][] getBoard() {
        return board;
    }

    @Override
    public void generate() {
        BoardGenerator boardGenerator = new BoardGenerator();
        boardGenerator.generate();
        Position[][] board = boardGenerator.getBoard() ;
        PieceGenerator pieceGenerator = new PieceGenerator();
        pieceGenerator.generate();
        Map<Tuple, PieceColor> pieceRelation = pieceGenerator.getInitialPositionForPieces();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                PieceColor possible = pieceRelation.get(new Tuple(i, j));
                if(possible != null){
                    board[i][j].insertPiece(pieceGenerator.generatePiece(possible.getColor(), possible.getPiece()));
                }
            }
        }
        this.board = board;
    }
}
