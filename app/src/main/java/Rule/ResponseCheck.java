package Rule;

import Position.Position;

import java.util.ArrayList;

public class ResponseCheck {
    ArrayList<Position> whichOnes = new ArrayList<>() ;

    public ResponseCheck() {}

    public void addPosition(Position position){
        whichOnes.add(position);
    }

    public boolean isCheck() {
        return whichOnes.size() > 0;
    }

    public ArrayList<Position> getWhichOnes() {
        return whichOnes;
    }
}
