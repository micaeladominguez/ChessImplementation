package Generators;

import Board.Tuple;
import Position.Position;

import java.util.Map;

public class LinkBoardPieceGenerators{
     Position[][] board;
    public Position[][] getBoard() {
        return board;
    }

    public void generate(int maxRow, int maxColumn) {
        BoardGenerator boardGenerator = new BoardGenerator();
        boardGenerator.generate(maxRow, maxColumn);
        Position[][] board = boardGenerator.getBoard() ;
        PieceGenerator pieceGenerator = new PieceGenerator();
        pieceGenerator.generateNormalBoard();
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
