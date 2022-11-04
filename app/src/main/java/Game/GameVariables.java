package Game;

import Board.Tuple;

public class GameVariables {
    public static int getRegularGameRowSize(){
        return 8;
    }
    public static int getRegularGameColumnSize(){
        return 8;
    }

    public static int getOtherGameRowSize(){
        return 9;
    }
    public static int getOtherGameColumnSize(){
        return 10;
    }

    public static Tuple getWhKing(){
        return new Tuple(0,4);
    }

    public static Tuple getBlKing(){
        return new Tuple(7,4);
    }
}
