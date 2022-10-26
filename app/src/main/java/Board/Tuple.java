package Board;

import java.util.Objects;

public class Tuple {
    protected int column;
    protected int row;

    public Tuple(int row, int column) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple tuple = (Tuple) o;
        return column == tuple.column && row == tuple.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }
}
