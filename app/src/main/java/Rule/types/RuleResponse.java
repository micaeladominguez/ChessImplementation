package Rule.types;


public class RuleResponse {
    protected boolean response;
    protected MoveType moveType;

    public RuleResponse(boolean response, MoveType moveType) {
        this.response = response;
        this.moveType = moveType;
    }

    public boolean isCorrect() {
        return response;
    }

    public MoveType getMoveType() {
        return moveType;
    }
    public String toString(){
        return " MoveType : " + moveType.toString() + " Available : " + response;
    }
}
