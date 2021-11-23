package timoshinov_i_b.chessboard;

import timoshinov_i_b.shapes.*;

public class ChessBoard {

    private ChessShape[][] chessBoard = new ChessShape[8][8];

    public ChessShape[][] getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(ChessShape[][] chessBoard) {
        this.chessBoard = chessBoard;
    }

    public ChessBoard() {
        createChessBoard();
    }

    public void createChessBoard() {
        for (int row = 0; row < chessBoard.length; row++) {
            for (int col = 0; col < chessBoard[row].length; col++) {
                if (row == 0) {
                    switch (col) {
                        case 0:
                            chessBoard[row][col] = new Tura(false);
                            break;
                        case 1:
                            chessBoard[row][col] = new Horse(false);
                            break;
                        case 2:
                            chessBoard[row][col] = new Elephant(false);
                            break;
                        case 3:
                            chessBoard[row][col] = new Queen(false);
                            break;
                        case 4:
                            chessBoard[row][col] = new King(false);
                            break;
                        case 5:
                            chessBoard[row][col] = new Elephant(false);
                            break;
                        case 6:
                            chessBoard[row][col] = new Horse(false);
                            break;
                        case 7:
                            chessBoard[row][col] = new Tura(false);
                            break;
                    }
                } else if (row == 1) {
                    chessBoard[row][col] = new Pawn(false);
                } else if (row == 6) {
                    chessBoard[row][col] = new Pawn(true);
                } else if (row == 7) {
                    switch (col) {
                        case 0:
                            chessBoard[row][col] = new Tura(true);
                            break;
                        case 1:
                            chessBoard[row][col] = new Horse(true);
                            break;
                        case 2:
                            chessBoard[row][col] = new Elephant(true);
                            break;
                        case 3:
                            chessBoard[row][col] = new Queen(true);
                            break;
                        case 4:
                            chessBoard[row][col] = new King(true);
                            break;
                        case 5:
                            chessBoard[row][col] = new Elephant(true);
                            break;
                        case 6:
                            chessBoard[row][col] = new Horse(true);
                            break;
                        case 7:
                            chessBoard[row][col] = new Tura(true);
                            break;
                    }
                } else {
                    chessBoard[row][col] = null;
                }
            }
        }
    }

    public void paintBoard() {
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
}
