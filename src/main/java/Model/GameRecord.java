package Model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * One row of the game-history CSV.
 */
public record GameRecord(int gameID,
                         Difficulty difficulty,
                         LocalDateTime startTime,
                         List<String> rankedNames,
                         Duration duration) {
    /**
     * Creates a record without an assigned ID. The ID is set to 0 as a
     * placeholder because valid IDs are positive. When saving, the history
     * writer checks the file, creates a record with a valid ID, and appends it.
     */
    public GameRecord(Difficulty difficulty,
               LocalDateTime startTime,
               List<String> rankedNames,
               Duration duration) {
        this(0, difficulty, startTime, rankedNames, duration);
    }
}
