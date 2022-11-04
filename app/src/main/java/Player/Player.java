package Player;

import Piece.ColorType;

public class Player {
    protected ColorType colors;

    public Player(ColorType colors) {
        this.colors = colors;
    }

    public ColorType getColors() {
        return colors;
    }

    public void setColors(ColorType colors) {
        this.colors = colors;
    }
}
