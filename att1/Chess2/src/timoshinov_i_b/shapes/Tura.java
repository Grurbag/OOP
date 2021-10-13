package timoshinov_i_b.shapes;

public class Tura extends  ChessShape {
    public Tura(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void paint() {
        if (this.isWhite) {
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
