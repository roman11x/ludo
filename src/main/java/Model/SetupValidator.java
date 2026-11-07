package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Validates setup input: no duplicate names or missing difficulty, unique colors.
 */
public class SetupValidator {
    /**
     * Prevents instantiation of this utility class.
     */
    private SetupValidator() {
    }

    /**
     * Validates the setup selections for starting a game.
     * Checks that a difficulty is selected, that a player list is provided,
     * that the number of players is between 2 and 4, and that player names
     * and colors are unique.
     *
     * @param players the player setup entries to validate
     * @param difficulty the selected game difficulty
     * @return a list of validation error messages, or an empty list if the setup is valid
     */
    public static List<String> validate(List<PlayerSetup> players, Difficulty difficulty) {
        var errors = new ArrayList<String>();
        if (difficulty == null) {
            errors.add("Please select a difficulty");
        }
        if (players == null) {
            errors.add("Please add players");
        } else {
            if (players.size() < 2 || players.size() > 4) {
                errors.add("Please add between 2 and 4 players");
            }
            if (!hasUniqueNames(players)) {
                errors.add("Please use unique player names");
            }
            if (!hasUniqueColors(players)) {
                errors.add("Please use unique player colors");
            }
        }
        return errors;
    }

    /**
     * Determines whether all player names in the supplied setup list are unique,
     * ignoring differences in letter case.
     *
     * @param players the player setup entries whose names are checked
     * @return true if each player name is unique when compared case-insensitively; false otherwise
     */
    private static boolean hasUniqueNames(List<PlayerSetup> players) {
        return players.stream()
                .map(PlayerSetup::name)
                .map(name -> name.toLowerCase(Locale.ROOT))
                .distinct()
                .count() == players.size();
    }

    /**
     * Determines whether all player colors in the supplied setup list are unique.
     *
     * @param players the player setup entries whose colors are checked
     * @return true if each player color is unique; false otherwise
     */
    private static boolean hasUniqueColors(List<PlayerSetup> players) {
        return players.stream()
                .map(PlayerSetup::color)
                .distinct()
                .count() == players.size();
    }

}
