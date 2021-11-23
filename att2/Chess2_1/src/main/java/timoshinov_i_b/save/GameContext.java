package timoshinov_i_b.save;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import main.java.timoshinov_i_b.utils.FileUtils;
import timoshinov_i_b.shapes.*;

import java.io.File;
import java.io.IOException;


@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class GameContext {

    private static final Logger log = LoggerFactory.getLogger(GameContext.class);

    private ChessShape[][] chessBoard;
    private boolean whiteTurn;

    public ChessShape[][] getChessBoard() {
        return chessBoard;
    }

    public boolean getWhiteTurn() {
        return whiteTurn;
    }

    public void setChessBoard(ChessShape[][] chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }


    public GameContext(ChessShape[][] chessBoard, boolean isWhiteTurn) {
        this.chessBoard = chessBoard;
        this.whiteTurn = isWhiteTurn;
    }

    public GameContext() {

    }

    public static void save(String saveName, GameContext gc) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        File savesDir = FileUtils.getAbsolutePathOfSavesDirectory().toFile();
        if (savesDir.mkdir()) {
            log.info("Saves directory " + savesDir.getAbsolutePath() + " was made");
        }
        File save = new File(savesDir.getAbsolutePath(), saveName + ".json");
        if (save.createNewFile()) {
            log.info("Save file " + save.getAbsolutePath() + " was made");
        }
        objectMapper.writeValue(save, gc);
    }

    public static GameContext read(File file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        return objectMapper.readValue(file, GameContext.class);
    }

}
