package timoshinov_i_b.shapes;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("King")
public class King extends ChessShape {

    public King(boolean isWhite) {
        super(isWhite);
    }

    public King() {
        super();
    }

    @Override
    public void paint() {
        if (this.white) {
            System.out.print("Kw");
        } else {
            System.out.print("Kb");
        }
    }

    @Override
    public boolean canWalk(int srcRow, int srcCol, int destRow, int destCol) {
        return Math.abs(destRow - srcRow) <= 1 || Math.abs(destCol - srcCol) <= 1;
    }

}
