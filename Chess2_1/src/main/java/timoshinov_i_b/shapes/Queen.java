package timoshinov_i_b.shapes;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Queen")
public class Queen extends ChessShape {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    public Queen() {
        super();
    }

    @Override
    public void paint() {
        if (this.white) {
            System.out.print("Qw");
        } else {
            System.out.print("Qb");
        }
    }

    @Override
    public boolean canWalk(int srcRow, int srcCol, int destRow, int destCol) {
        return Math.abs(srcRow - destRow) == Math.abs(srcCol - destCol) || srcRow == destRow || srcCol == destCol;
    }

}
