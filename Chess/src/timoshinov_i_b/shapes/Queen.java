package timoshinov_i_b.shapes;

public class Queen extends ChessShape {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void paint() {
        if (this.isWhite) {
            System.out.print("Q");
        } else {
            System.out.print("q");
        }
    }

    @Override
    public boolean canWalk(int srcRow, int srcCol, int destRow, int destCol) {
        return Math.abs(srcRow - destRow) == Math.abs(srcCol - destCol) || srcRow == destRow || srcCol == destCol;
    }

}
