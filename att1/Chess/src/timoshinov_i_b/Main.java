package timoshinov_i_b;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessBoard Chessboard = new ChessBoard();
        while (Chessboard.getGameContinues()) {
            Chessboard.paintBoard();
            Chessboard.move();
        }
    }
}
