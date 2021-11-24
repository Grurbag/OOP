package timoshinov_i_b.shapes;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class ChessShape {

    protected boolean white;

    public ChessShape(boolean isWhite) {
        this.white = isWhite;
    }

    public ChessShape() {
    }

    public void setWhite(boolean white) {
        this.white = white;
    }

    public boolean isWhite() {
        return this.white;
    }

    public abstract void paint();

    public abstract boolean canWalk(int var1, int var2, int var3, int var4);
}