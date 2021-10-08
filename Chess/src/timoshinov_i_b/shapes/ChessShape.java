package timoshinov_i_b.shapes;

public abstract class ChessShape {
    protected boolean isWhite;

    public ChessShape(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    public abstract void paint();

    public abstract boolean canWalk(int var1, int var2, int var3, int var4);


}