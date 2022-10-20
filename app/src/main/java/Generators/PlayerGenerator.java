package Generators;

import Piece.Colors;
import Player.Player;

public class PlayerGenerator {
    public static Player[] generatePlayers(){
        Player[] players = new Player[2];
        players[0] = new Player(Colors.WHITE);
        players[1] = new Player(Colors.BLACK);
        return players;
    }
}
