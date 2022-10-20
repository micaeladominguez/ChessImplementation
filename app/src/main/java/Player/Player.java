package Player;

import Piece.Colors;

public class Player {
    protected Colors colors;

    public Player(Colors colors) {
        this.colors = colors;
    }

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }
}
