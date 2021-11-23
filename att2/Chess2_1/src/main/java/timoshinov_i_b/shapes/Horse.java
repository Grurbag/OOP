package timoshinov_i_b.shapes;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Horse")
public class Horse extends ChessShape {
    public Horse(boolean isWhite) {
        super(isWhite);
    }

    public Horse() {
        super();
    }

    @Override
    public void paint() {
        if (this.white) {
            System.out.print("Hw");
        } else {
            System.out.print("Hb");
        }
    }

    @Override
    public boolean canWalk(int srcRow, int srcCol, int destRow, int destCol) {
        return Math.abs(srcRow - destRow) == 2 && Math.abs(srcCol - destCol) == 1 || Math.abs(srcRow - destRow) == 1 && Math.abs(srcCol - destCol) == 2;
    }

}
