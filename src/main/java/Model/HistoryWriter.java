package Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Appends a completed game to the history CSV.
 */
public class HistoryWriter {
    //history csv template header
    private static final String HEADER = "gameID,difficulty,"
            + "timestamp(DD/MM/YYYY; HH:MM:SS),"
            + "1st_place_name,2nd_place_name,3rd_place_name,4rd_place_name,"
            + "game_duration(HH:MM:SS)";

    private final Path path; // the path to the history csv file

    /**
     * @param path a path that includes a parent directory.
     */
    public HistoryWriter(Path path) {
        this.path = path;
    }

    public void createFileIfNotExists() throws IOException {
        if (!Files.exists(path)) {
            // Create the parent directory and any missing directories above it.
            Files.createDirectories(path.getParent());
            Files.writeString(path, HEADER + "\n");
        }
    }
}
