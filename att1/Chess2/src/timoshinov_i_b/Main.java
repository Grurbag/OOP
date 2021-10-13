package timoshinov_i_b;

public class Main {

    public static void main(String[] args) {
        ChessBoard Chessboard = new ChessBoard();
        while (Chessboard.getGameContinues()) {
            Chessboard.paintBoard();
            Chessboard.move();
        }
    }
}