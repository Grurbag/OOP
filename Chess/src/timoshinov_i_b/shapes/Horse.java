package timoshinov_i_b.shapes;

public class Horse extends ChessShape {
    public Horse(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void paint() {
        if (this.isWhite) {
            System.out.print("H");
        } else {
            System.out.print("h");
        }
    }

    @Override
    public boolean canWalk(int srcRow, int srcCol, int destRow, int destCol) {
        return Math.abs(srcRow - destRow) == 2 && Math.abs(srcCol - destCol) == 1 || Math.abs(srcRow - destRow) == 1 && Math.abs(srcCol - destCol) == 2;
    }

}
