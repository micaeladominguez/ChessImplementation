package Rule;

import Board.Board;
import Position.Position;
import Rule.types.RuleResponse;

public interface Rule {
     RuleResponse isMovePossible(Board board, Position positionTo, Position positionFrom);
}
