package Rule.types;

import Rule.types.MoveType;

public class RuleResponse {
    protected boolean response;
    protected MoveType moveType;

    public RuleResponse(boolean response, MoveType moveType) {
        this.response = response;
        this.moveType = moveType;
    }

    public boolean isResponse() {
        return response;
    }

    public void setResponse(boolean response) {
        this.response = response;
    }

    public MoveType getMoveType() {
        return moveType;
    }

    public void setMoveType(MoveType moveType) {
        this.moveType = moveType;
    }
}
