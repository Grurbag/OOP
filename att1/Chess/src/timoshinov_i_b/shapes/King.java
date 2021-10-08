package timoshinov_i_b.shapes;

public class King extends ChessShape {

    public King(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void paint() {
        if (this.isWhite) {
            System.out.print("K");
        } else {
            System.out.print("k");
        }
    }

    @Override
    public boolean canWalk(int srcRow, int srcCol, int destRow, int destCol) {
        return Math.abs(destRow - srcRow) <= 1 || Math.abs(destCol - srcCol) <= 1;
    }

}
