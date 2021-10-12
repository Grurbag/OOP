package timoshinov_i_b;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import timoshinov_i_b.shapes.*;

import java.util.Scanner;

public class ChessBoard {
    private static final Logger logger = LoggerFactory.getLogger(ChessBoard.class);
    private static final int boardSize = 8;
    private ChessShape[][] chessBoard = new ChessShape[boardSize][boardSize];
    private static int shapeRow, shapeCol, moveRow, moveCol;
    private static Boolean whiteTurn = true;
    private static Boolean isPossibleWalk = false;
    String walk;
    Scanner player_move = new Scanner(System.in);
    private Boolean gameContinues;
    public ChessBoard() {
        createChessBoard(chessBoard);
        gameContinues = true;
    }

    public Boolean getGameContinues() {
        return this.gameContinues;
    }

    private static void createChessBoard(ChessShape[][] chessboard) {
        logger.info("Создание новой доски");
        for (int row = 0; row < chessboard.length; row++) {
            for (int col = 0; col < chessboard[row].length; col++) {
                if (row == 0) {
                    switch (col) {
                        case 0:
                            chessboard[row][col] = new Tura(false);
                            break;
                        case 1:
                            chessboard[row][col] = new Horse(false);
                            break;
                        case 2:
                            chessboard[row][col] = new Elephant(false);
                            break;
                        case 3:
                            chessboard[row][col] = new Queen(false);
                            break;
                        case 4:
                            chessboard[row][col] = new King(false);
                            break;
                        case 5:
                            chessboard[row][col] = new Elephant(false);
                            break;
                        case 6:
                            chessboard[row][col] = new Horse(false);
                            break;
                        case 7:
                            chessboard[row][col] = new Tura(false);
                            break;
                    }
                } else if (row == 1) {
                    chessboard[row][col] = new Pawn(false);
                } else if (row == 6) {
                    chessboard[row][col] = new Pawn(true);
                } else if (row == 7) {
                    switch (col) {
                        case 0:
                            chessboard[row][col] = new Tura(true);
                            break;
                        case 1:
                            chessboard[row][col] = new Horse(true);
                            break;
                        case 2:
                            chessboard[row][col] = new Elephant(true);
                            break;
                        case 3:
                            chessboard[row][col] = new Queen(true);
                            break;
                        case 4:
                            chessboard[row][col] = new King(true);
                            break;
                        case 5:
                            chessboard[row][col] = new Elephant(true);
                            break;
                        case 6:
                            chessboard[row][col] = new Horse(true);
                            break;
                        case 7:
                            chessboard[row][col] = new Tura(true);
                            break;
                    }
                } else {
                    chessboard[row][col] = null;
                }
            }
        }
    }

    public void paintBoard() {
        logger.info("Началась отрисовка доски");
        System.out.println("\ta\tb\tc\td\te\tf\tg\th");
        for (int row = 0; row < chessBoard.length; row++) {
                System.out.print(8 - row + ".\t");
            for (int col = 0; col < chessBoard[row].length; col++) {
                if (chessBoard[row][col] != null) {
                    chessBoard[row][col].paint();
                    System.out.print("\t");
                } else {
                    System.out.print("*\t");
                }
            }
            System.out.print(8 - row + ".\t");
            System.out.println();
        }
        System.out.println("\ta\tb\tc\td\te\tf\tg\th");
    }

    private boolean movePossible() {

        if (shapeRow < 0 || shapeRow > 7 || shapeCol < 0 || shapeCol > 7 || moveRow < 0
                || moveRow > 7 || moveCol < 0 || moveCol > 7) {
            System.out.println("Нельзя ходить за пределы доски");
            return false;
        }

        if (chessBoard[shapeRow][shapeCol] == null) {
            System.err.println("Нет фигуры");
            return false;
        }

        if ((chessBoard[shapeRow][shapeCol].isWhite() && !whiteTurn)
                || (!chessBoard[shapeRow][shapeCol].isWhite() && whiteTurn)) {
            System.err.println("Сейчас не ваш ход");
            return false;
        }

        if (chessBoard[shapeRow][shapeCol].getClass() == Pawn.class) {
            if ((moveCol == shapeCol + 1 || moveCol == shapeCol - 1) && chessBoard[moveRow][moveCol] == null) {
                    System.err.println("Эта фигура не может так ходить");
                    return false;
            }
        }

        if (!chessBoard[shapeRow][shapeCol].canWalk(shapeRow, shapeCol, moveRow,
                moveCol)) {
            System.err.println("Эта фигура не может так ходить");
            return false;
        }

        if (chessBoard[shapeRow][shapeCol].getClass() == Tura.class || chessBoard[shapeRow][shapeCol].getClass() == Queen.class) {
            if (shapeCol == moveCol) {
                for (int i = 1; i < Math.abs(moveRow - shapeRow); i++) {
                    if (chessBoard[i][shapeCol] != null) {
                        System.err.println("Прыгать через фигуры может только конь");
                        return false;
                    }
                }
            } else if (shapeRow == moveRow) {
                for (int i = 1; i < Math.abs(moveCol - shapeCol); i++) {
                    if (chessBoard[shapeRow][i] != null) {
                        System.err.println("Прыгать через фигуры может только конь");
                        return false;
                    }
                }
            }
        }

        if (chessBoard[shapeRow][shapeCol].getClass() == Elephant.class || chessBoard[shapeRow][shapeCol].getClass() == Queen.class) {
            if (moveCol - shapeCol >= 0 && moveRow - shapeRow <= 0 ) {
                for (int i = 1; i <= Math.abs(moveCol - shapeCol); i++) {
                    if (chessBoard[shapeRow - i][shapeCol + i] != null) {
                        System.err.println("Прыгать через фигуры может только конь");
                        return false;
                    }
                }
            } else if (moveCol - shapeCol <= 0 && moveRow - shapeRow >= 0) {
                for (int i = 1; i <= Math.abs(moveCol - shapeCol); i++) {
                    if (chessBoard[shapeRow + i][shapeCol - i] != null) {
                        System.err.println("Прыгать через фигуры может только конь");
                        return false;
                    }
                }
            } else if (moveCol - shapeCol <= 0 && moveRow - shapeRow <= 0) {
                for (int i = 1; i <= Math.abs(moveCol - shapeCol); i++) {
                    if (chessBoard[shapeRow - i][shapeCol - i] != null) {
                        System.err.println("Прыгать через фигуры может только конь");
                        return false;
                    }
                }
            } else if (moveCol - shapeCol >= 0 && moveRow - shapeRow >= 0) {
                for (int i = 1; i <= Math.abs(moveCol - shapeCol); i++) {
                    if (chessBoard[shapeRow + i][shapeCol + i] != null) {
                        System.err.println("Прыгать через фигуры может только конь");
                        return false;
                    }
                }
            }
        }

        if (chessBoard[moveRow][moveCol] == null) {
            return true;
        }

        if (chessBoard[shapeRow][shapeCol].isWhite() && chessBoard[moveRow][moveCol].isWhite() ||
                !chessBoard[shapeRow][shapeCol].isWhite() && !chessBoard[moveRow][moveCol].isWhite()) {
            System.err.println("Вы не можете  съесть свою же фигуру");
            return false;
        }
        return true;
    }

    public void move() {
        if (isPossibleWalk) {
            logger.warn("Неправильный ход");
            System.err.println("Такой ход невозможен, попробуйте еще раз:");
            isPossibleWalk = false;
        } else if (whiteTurn) {
            System.out.println("_________________________________\n"
                            + "Ход белых:\n");
        } else {
            System.out.println("_________________________________\n"
                            + "Ход черных:\n");
        }
        walk = player_move.nextLine();

        if (walk.equalsIgnoreCase("Закончить игру")) {
            gameContinues = false;
            System.out.println("Игра окончена");
            return;
        }
        String lowerCase = walk.toLowerCase();
        String[] components = lowerCase.split(" ");

        shapeRow = 7 - (components[0].charAt(1) - '1');
        shapeCol = components[0].charAt(0) - 'a';
        moveRow = 7 - (components[2].charAt(1) - '1');
        moveCol = components[2].charAt(0) - 'a';

        if (movePossible()) {
            if (chessBoard[moveRow][moveCol] != null && chessBoard[moveRow][moveCol].getClass() == King.class && whiteTurn) {
                gameContinues = false;
                logger.info("Белые победили");
                System.out.println("Белые победили");
                return;
            } else if (chessBoard[moveRow][moveCol] != null && chessBoard[moveRow][moveCol].getClass() == King.class && !whiteTurn) {
                gameContinues = false;
                logger.info("Черные победили");
                System.out.println("Черные победили");
                return;
            }
            chessBoard[moveRow][moveCol] = chessBoard[shapeRow][shapeCol];
            chessBoard[shapeRow][shapeCol] = null;
            whiteTurn = !whiteTurn;
            logger.info("ход перешел к другому игроку");
        } else {
            isPossibleWalk = true;
            move();
        }
    }
}
