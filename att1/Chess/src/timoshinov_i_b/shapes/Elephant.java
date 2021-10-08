package timoshinov_i_b.shapes;

public class Elephant extends  ChessShape {

    public Elephant(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void paint() {
        if (this.isWhite) {
            System.out.print("E");
        } else {
            System.out.print("e");
        }
    }

    @Override
    public boolean canWalk(int srcRow, int srcCol, int destRow, int destCol) {
        return Math.abs(srcRow - destRow) == Math.abs(srcCol - destCol);
    }

}
