package timoshinov_i_b.shapes;

import com.fasterxml.jackson.annotation.JsonClassDescription;

@JsonClassDescription("Elephant")
public class Elephant extends  ChessShape {

    public Elephant(boolean isWhite) {
        super(isWhite);
    }

    public Elephant() {
        super();
    }

    @Override
    public void paint() {
        if (this.white) {
            System.out.print("Ew");
        } else {
            System.out.print("Eb");
        }
    }

    @Override
    public boolean canWalk(int srcRow, int srcCol, int destRow, int destCol) {
        return Math.abs(srcRow - destRow) == Math.abs(srcCol - destCol);
    }

}
