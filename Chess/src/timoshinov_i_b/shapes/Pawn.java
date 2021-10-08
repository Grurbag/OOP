package timoshinov_i_b.shapes;

public class Pawn extends ChessShape {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    @Override
    public void paint() {
        if (this.isWhite) {
            System.out.print("P");
        } else {
            System.out.print("p");
        }
    }

    @Override
    public boolean canWalk(int shapeRow, int shapeCol, int moveRow, int moveCol) {
        if (this.isWhite) {
            return (((shapeCol == moveCol) && shapeRow == (moveRow + 1)) || ((shapeRow == 6) && (shapeCol == moveCol)
                    && (shapeRow == (moveRow + 2))) || ((shapeRow == (moveRow + 1)) && (Math.abs(shapeCol - moveCol) == 1)));
        } else {
            return (((shapeCol == moveCol) && shapeRow == (moveRow - 1)) || ((shapeRow == 6) && (shapeCol == moveCol)
                    && (shapeRow == (moveRow - 2))) || ((shapeRow == (moveRow - 1)) && (Math.abs(shapeCol - moveCol) == 1)));
        }
    }
}
