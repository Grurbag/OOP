package timoshinov_i_b.shapes;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("Pawn")
public class Pawn extends ChessShape {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    public Pawn() {
        super();
    }

    @Override
    public void paint() {
        if (this.white) {
            System.out.print("Pw");
        } else {
            System.out.print("Pb");
        }
    }

    @Override
    public boolean canWalk(int shapeRow, int shapeCol, int moveRow, int moveCol) {
        if (this.white) {
            return (((shapeCol == moveCol) && shapeRow == (moveRow + 1)) || ((shapeRow == 6) && (shapeCol == moveCol)
                    && (shapeRow == (moveRow + 2))) || ((shapeRow == (moveRow + 1)) && (Math.abs(shapeCol - moveCol) == 1)));
        } else {
            return (((shapeCol == moveCol) && shapeRow == (moveRow - 1)) || ((shapeRow == 1) && (shapeCol == moveCol)
                    && (shapeRow == (moveRow - 2))) || ((shapeRow == (moveRow - 1)) && (Math.abs(shapeCol - moveCol) == 1)));
        }
    }
}
