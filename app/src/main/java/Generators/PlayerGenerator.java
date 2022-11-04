package Generators;

import Piece.ColorType;
import Player.Player;

public class PlayerGenerator {
    Player[] players;

    public Player[] getPlayers() {
        return players;
    }

    public void generate() {
        Player[] players = new Player[2];
        players[0] = new Player(ColorType.WHITE);
        players[1] = new Player(ColorType.BLACK);
        this.players = players;
    }


}
