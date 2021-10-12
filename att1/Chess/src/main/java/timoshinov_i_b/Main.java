package timoshinov_i_b;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class Main {
private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {
        ChessBoard Chessboard = new ChessBoard();
        logger.info("Игра началась");
        while (Chessboard.getGameContinues()) {
            Chessboard.paintBoard();
            Chessboard.move();
        }
    }
}
