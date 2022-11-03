package Generators;

import Piece.Colors;
import Player.Player;

public class PlayerGenerator implements Generator {
    Player[] players;

    public Player[] getPlayers() {
        return players;
    }

    @Override
    public void generate() {
        Player[] players = new Player[2];
        players[0] = new Player(Colors.WHITE);
        players[1] = new Player(Colors.BLACK);
        this.players = players;
    }


}
