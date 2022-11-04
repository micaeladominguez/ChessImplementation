package Move;

import Position.Position;

public class Move {
     private Position positionFrom;
    private Position positionTo;

    public Move(Position positionFrom, Position positionTo) {
        this.positionFrom = positionFrom;
        this.positionTo = positionTo;
    }

    public Position getPositionFrom() {
        return positionFrom;
    }

    public Position getPositionTo() {
        return positionTo;
    }
}
