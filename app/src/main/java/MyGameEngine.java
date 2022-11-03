import Game.Game;
import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;
import Game.GameResponse;
import Game.TypeOfResponse;

import java.util.List;

public class MyGameEngine implements GameEngine {
    Game game = new Game();
    Adapter adapter = new Adapter();

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
       GameResponse gameResponse =  game.possibleMovement(move.getTo().getRow() - 1, move.getTo().getColumn() - 1, move.getFrom().getRow() - 1, move.getFrom().getColumn() - 1);
       if(gameResponse.getTypeOfResponse() == TypeOfResponse.CORRECT_MOVE){
           return new NewGameState(adapter.getPieces(game.getBoard()),adapter.parseColor(game.getReference()));
       }
       else if(gameResponse.getTypeOfResponse() == TypeOfResponse.INCORRECT_MOVE)
           return new InvalidMove(gameResponse.getMessage());
       else{
           return new GameOver(adapter.parseColor(game.getReference()));
       }
    }

    @NotNull
    @Override
    public InitialState init() {
        game.startGame();
        List<ChessPiece> chessPieces =  adapter.getPieces(game.getBoard());
        return new InitialState(new BoardSize(game.getBoard().getMaxEdges().getColumn(),
                game.getBoard().getMaxEdges().getRow()), chessPieces,
                adapter.parseColor(game.getReference()));
    }


}
