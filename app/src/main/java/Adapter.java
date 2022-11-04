import Board.Board;
import Piece.Piece;
import edu.austral.dissis.chess.gui.ChessPiece;
import edu.austral.dissis.chess.gui.PlayerColor;
import edu.austral.dissis.chess.gui.Position;

import java.util.ArrayList;
import java.util.List;
import Piece.ColorType;
import Piece.PieceType;
public class Adapter {
    public List<ChessPiece> getPieces(Board board){
        ArrayList<ChessPiece> chessPieces = new ArrayList<>();
        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                if(!board.getBoard()[i][j].isEmpty()){
                    if(board.getBoard()[i][j].getPiece().isPresent()){
                        Piece piece = board.getBoard()[i][j].getPiece().get();
                        chessPieces.add(new ChessPiece(String.valueOf(piece.getId()), parseColor(piece.getColor()), new Position(i + 1, j + 1), parseTypeOfPiece(piece.getName())));
                    }

                }
            }
        }
        return chessPieces;
    }

    public PlayerColor parseColor(ColorType reference) {
        if(reference == ColorType.BLACK){
            return  PlayerColor.BLACK;
        }else{
            return PlayerColor.WHITE;
        }
    }

    public String parseTypeOfPiece(PieceType pieces){
        switch (pieces){
            case KING -> {
                return "king";
            }
            case QUEEN -> {
                return "queen";
            }
            case ROOK -> {
                return "rook";
            }
            case BISHOP -> {
                return "bishop";
            }
            case PAWN -> {
                return "pawn";
            }
            case HORSE -> {
                return "knight";
            }
        }
        return "king";
    }
}
