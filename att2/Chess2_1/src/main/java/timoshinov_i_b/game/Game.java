package timoshinov_i_b.game;

import timoshinov_i_b.chessboard.ChessBoard;
import timoshinov_i_b.commandProviders.CommandProvider;
import timoshinov_i_b.commandProviders.ConsoleCommandProvider;
import timoshinov_i_b.commandProviders.ScriptedCommandProvider;
import timoshinov_i_b.save.SaveGame;
import timoshinov_i_b.shapes.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import main.java.timoshinov_i_b.utils.FileUtils;

public class Game {
    private static final Logger log = LoggerFactory.getLogger(Game.class);

    Scanner sc = new Scanner(System.in);
    private CommandProvider cp;

    private ChessBoard chessBoard;
    private int shapeRow, shapeCol, moveRow, moveCol;
    private Boolean whiteTurn;
    private Boolean isPossibleWalk = false;
    private Boolean gameContinues;

    public Game() {
        this.chessBoard = new ChessBoard();
        this.gameContinues = true;
        this.whiteTurn = true;
    }

    public Boolean getGameContinues() {
        return this.gameContinues;
    }

    public void begin() {
        boolean gameEnd = false;
        while (!gameEnd) {
            this.chessBoard.createChessBoard();
            String gameMode;
            System.out.println("MAIN MENU");
            System.out.println("_________________________________\n"
                    + "Start Game - start a new game");
            System.out.println("_________________________________\n"
                    + "Demonstration - show a demo batch");
            System.out.println("_________________________________\n"
                    + "Load - load a saved game");
            System.out.println("_________________________________\n"
                    + "Exit - quit the game\n"+
                    "_________________________________\n");
            gameMode = sc.nextLine();
            switch (gameMode.toLowerCase()) {
                case "start game" -> {
                    log.info("game started");
                    this.whiteTurn = true;
                    this.gameContinues = true;
                    cp = new ConsoleCommandProvider();
                    startGame(this);
                }

                case "demonstration" -> {
                    log.info("demonstration started");
                    cp = new ScriptedCommandProvider();
                    this.whiteTurn = true;
                    this.gameContinues = true;
                    startGame(this);
                }

                case "exit" -> {
                    log.info("game ended");
                    gameEnd = true;
                }

                case "load" -> {
                    try {
                        File savesDirectory = FileUtils.getAbsolutePathOfSavesDirectory().toFile();
                        if (!savesDirectory.exists()) {
                            System.out.println("Can not find saves folder");
                            log.error("Can not find saves folder");
                        } else {
                            File[] savesList = savesDirectory.listFiles();
                            List<String> names = Arrays.stream(savesList)
                                    .map(x -> x.toPath().getFileName().toString())
                                    .filter(x -> x.endsWith(".json"))
                                    .map(x -> {
                                        String filename = x;
                                        filename = filename.substring(0, filename.lastIndexOf('.'));
                                        return filename;
                                    })
                                    .collect(Collectors.toList());
                            if (names.size() > 0) {
                                System.out.println("Available saves files: ");
                                names.forEach(x -> System.out.println("    " + x));
                                System.out.println("Type name of save");
                                String filename = sc.nextLine();
                                int index = names.indexOf(filename);
                                if (index != -1) {
                                    SaveGame sg = SaveGame.read(savesList[index]);
                                    this.chessBoard.setChessBoard(sg.getChessBoard());
                                    whiteTurn = sg.getWhiteTurn();
                                    cp = new ConsoleCommandProvider();
                                    this.gameContinues = true;
                                    log.info("save loaded");
                                    startGame(this);
                                } else {
                                    System.out.println("Can not find this file");
                                    log.error("Can not find this file");
                                }
                            } else {
                                System.out.println("Saves folder is empty.");
                            }
                        }
                    } catch (IOException e) {
                        log.info("Can not read save file. Error: " + e.getMessage());
                    }
                }

                default -> {
                    log.error("unknown command");
                    System.out.println("unknown command");
                }
            }
        }
    }

    private void startGame(Game game) {
        while (game.gameContinues) {
            game.chessBoard.paintBoard();
            game.step(game.chessBoard.getChessBoard());
        }
    }

    private boolean drawChecker(ChessShape[][] chessBoard) {
        int whiteElephantCount = 0;
        int blackElephantCount = 0;
        int whiteHorseCount = 0;
        int blackHorseCount = 0;
        int otherFiguresCount = 0;
        for (int row = 0; row  < chessBoard.length; row ++) {
            for (int col = 0; col < chessBoard.length; col++) {
                if (chessBoard[row][col] != null && chessBoard[row][col].getClass() == Horse.class) {
                    if (chessBoard[row][col].isWhite()) {
                        whiteHorseCount++;
                    } else {
                        blackHorseCount++;
                    }
                } else if (chessBoard[row][col] != null && chessBoard[row][col].getClass() == Elephant.class) {
                    if (chessBoard[row][col].isWhite()) {
                        whiteElephantCount++;
                    } else {
                        blackElephantCount++;
                    }
                } else if(chessBoard[row][col] != null && chessBoard[row][col].getClass() != King.class) {
                    otherFiguresCount++;
                }
            }
        }
        return otherFiguresCount == 0 && ((whiteHorseCount == 1 && blackHorseCount == 1) || (whiteElephantCount == 1 && blackElephantCount == 1)
                || (whiteHorseCount == 1 && blackElephantCount == 1) || (whiteElephantCount == 1 && blackHorseCount == 1));
    }

    private boolean movePossible(ChessShape[][]chessBoard) {
        if (shapeRow < 0 || shapeRow > 7 || shapeCol < 0 || shapeCol > 7 || moveRow < 0
                || moveRow > 7 || moveCol < 0 || moveCol > 7) {
            System.out.println("You can't go outside the board");
            return false;
        }

        if (chessBoard[shapeRow][shapeCol] == null) {
            System.err.println("There is no figure");
            return false;
        }

        if ((chessBoard[shapeRow][shapeCol].isWhite() && !whiteTurn)
                || (!chessBoard[shapeRow][shapeCol].isWhite() && whiteTurn)) {
            System.err.println("It's not your turn now");
            return false;
        }

        if (chessBoard[shapeRow][shapeCol].getClass() == Pawn.class) {
            if ((moveCol == shapeCol + 1 || moveCol == shapeCol - 1) && chessBoard[moveRow][moveCol] == null) {
                    System.err.println("This figure can't walk like that");
                    return false;
            }
        }

        if (!chessBoard[shapeRow][shapeCol].canWalk(shapeRow, shapeCol, moveRow,
                moveCol)) {
            System.err.println("This figure can't walk like that");
            return false;
        }

        if (chessBoard[shapeRow][shapeCol].getClass() == Tura.class || chessBoard[shapeRow][shapeCol].getClass() == Queen.class) {
            if (shapeCol == moveCol) {
                for (int i = 1; i < Math.abs(moveRow - shapeRow); i++) {
                    if (chessBoard[i][shapeCol] != null) {
                        System.err.println("Only a horse can jump over the pieces");
                        return false;
                    }
                }
            } else if (shapeRow == moveRow) {
                for (int i = 1; i < Math.abs(moveCol - shapeCol); i++) {
                    if (chessBoard[shapeRow][i] != null) {
                        System.err.println("Only a horse can jump over the pieces");
                        return false;
                    }
                }
            }
        }

        if (chessBoard[shapeRow][shapeCol].getClass() == Elephant.class || chessBoard[shapeRow][shapeCol].getClass() == Queen.class) {
            if (moveCol - shapeCol >= 0 && moveRow - shapeRow <= 0 ) {
                for (int i = 1; i <= Math.abs(moveCol - shapeCol); i++) {
                    if (chessBoard[shapeRow - i][shapeCol + i] != null) {
                        System.err.println("Only a horse can jump over the pieces");
                        return false;
                    }
                }
            } else if (moveCol - shapeCol <= 0 && moveRow - shapeRow >= 0) {
                for (int i = 1; i <= Math.abs(moveCol - shapeCol); i++) {
                    if (chessBoard[shapeRow + i][shapeCol - i] != null) {
                        System.err.println("Only a horse can jump over the pieces");
                        return false;
                    }
                }
            } else if (moveCol - shapeCol <= 0 && moveRow - shapeRow <= 0) {
                for (int i = 1; i <= Math.abs(moveCol - shapeCol); i++) {
                    if (chessBoard[shapeRow - i][shapeCol - i] != null) {
                        System.err.println("Only a horse can jump over the pieces");
                        return false;
                    }
                }
            } else if (moveCol - shapeCol >= 0 && moveRow - shapeRow >= 0) {
                for (int i = 1; i <= Math.abs(moveCol - shapeCol); i++) {
                    if (chessBoard[shapeRow + i][shapeCol + i] != null) {
                        System.err.println("Only a horse can jump over the pieces");
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
            System.err.println("You can't destroy your own figure");
            return false;
        }
        return true;
    }

    private void step(ChessShape[][]chessBoard) {
        if (isPossibleWalk) {
            System.err.println("Such a move is impossible, try again:");
            isPossibleWalk = false;
        } else if (whiteTurn) {
            System.out.println("_________________________________\n"
                            + "White turn:");
        } else {
            System.out.println("_________________________________\n"
                            + "Black turn:");
        }
        String command;
        System.out.println("Choose what you want to do:");
        System.out.println("move - make a move (for example:move a2 a4):");
        System.out.println("surrender");
        System.out.println("save - save the current game ");
        System.out.println("exit - exit to the main menu");
        System.out.println("castling(long or short) - Perform castling");
        command = cp.getNextLine();
        String[] arr = command.split(" ");
        String firstCommand = arr[0];

        switch (firstCommand.toLowerCase()) {
            case "move" -> {
                shapeRow = 7 - (arr[1].charAt(1) - '1');
                shapeCol = arr[1].charAt(0) - 'a';
                moveRow = 7 - (arr[2].charAt(1) - '1');
                moveCol = arr[2].charAt(0) - 'a';

                if (movePossible(chessBoard)) {
                    if (chessBoard[moveRow][moveCol] != null && chessBoard[moveRow][moveCol].getClass() == King.class && whiteTurn) {
                        gameContinues = false;
                        System.out.println("White won");
                        return;
                    } else if (chessBoard[moveRow][moveCol] != null && chessBoard[moveRow][moveCol].getClass() == King.class && !whiteTurn) {
                        gameContinues = false;
                        System.out.println("Black won");
                        return;
                    }
                    if (drawChecker(chessBoard)) {
                        gameContinues = false;
                        System.out.println("Drawn Game");
                        return;
                    }
                    chessBoard[moveRow][moveCol] = chessBoard[shapeRow][shapeCol];
                    chessBoard[shapeRow][shapeCol] = null;
                    whiteTurn = !whiteTurn;
                } else {
                    isPossibleWalk = true;
                }
            }

            case "castling" -> {
                int castlingRow;
                if (whiteTurn) {
                    castlingRow = 7;
                } else {
                    castlingRow = 0;
                }
                if (arr[1].equals("long") && chessBoard[castlingRow][4].getClass() == King.class
                        && chessBoard[castlingRow][0].getClass() == Tura.class) {
                    if (chessBoard[castlingRow][1] == null && chessBoard[castlingRow][2] == null
                            && chessBoard[castlingRow][3] == null) {
                        chessBoard[castlingRow][4] = null;
                        chessBoard[castlingRow][2] = new King(whiteTurn);
                        chessBoard[castlingRow][0] = null;
                        chessBoard[castlingRow][3] = new Tura(whiteTurn);
                    } else  {
                        System.out.println("Castling is impossible");
                    }
                } else if (arr[1].equals("short") && chessBoard[castlingRow][4].getClass() == King.class
                        && chessBoard[castlingRow][7].getClass() == Tura.class) {
                    if (chessBoard[castlingRow][5] == null && chessBoard[castlingRow][6] == null) {
                        chessBoard[castlingRow][4] = null;
                        chessBoard[castlingRow][6] = new King(whiteTurn);
                        chessBoard[castlingRow][7] = null;
                        chessBoard[castlingRow][5] = new Tura(whiteTurn);
                    } else  {
                        System.out.println("Castling is impossible");
                    }
                }
            }

            case "save" -> {
                try {
                    if (arr.length < 2) {
                        System.out.println("SaveName is not founded");
                        log.info("SaveName is not founded");
                        break;
                    }
                    String saveName = arr[1];
                    SaveGame sg = new SaveGame();
                    sg.setChessBoard(chessBoard);
                    sg.setWhiteTurn(whiteTurn);
                    SaveGame.save(saveName, sg);
                } catch (IOException e) {
                    System.out.println("Failed to save that game.");
                    log.info("Failed to save that game.\n Error: " + e.getMessage());
                }
            }

            case "exit" -> {
                gameContinues = false;
                System.out.println("The game is over");
            }

            case "surrender" -> {
                if (whiteTurn){
                    gameContinues = false;
                    System.out.println("Black won");
                } else {
                    gameContinues = false;
                    System.out.println("White won");
                }
            }

            default -> {
                log.error("unknown command");
                System.out.println("unknown command, try again");
            }
        }
    }
}