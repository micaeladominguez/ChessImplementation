package Rule;

import Rule.types.MoveType;
import Rule.types.RuleResponse;
import org.jetbrains.annotations.NotNull;

public class RuleOperator {

    @NotNull
    public static RuleResponse validResponse(MoveType moveType) {
        return response(true, moveType);
    }

    @NotNull
    public static RuleResponse invalidResponse(MoveType moveType) {
        return response(false, moveType);
    }

    @NotNull
    private static RuleResponse response(Boolean isValid, MoveType moveType) {
        return new RuleResponse(isValid, moveType);
    }
}
