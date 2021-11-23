package timoshinov_i_b.shapes;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName ("Tura")
public class Tura extends  ChessShape {
    public Tura(boolean isWhite) {
        super(isWhite);
    }

    public Tura() {
        super();
    }

    @Override
    public void paint() {
        if (this.white) {
            System.out.print("Tw");
        } else {
            System.out.print("Tb");
        }
    }

    @Override
    public boolean canWalk(int srcRow, int srcCol, int destRow, int destCol) {
        return srcRow == destRow || srcCol == destCol;
    }

}
