package Model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SetupValidatorTest {
    /**
     * Creates a list containing the requested number of predefined player setup entries.
     * Players are selected in order from the available default players.
     *
     * @param count the number of player setup entries to include
     * @return a list containing the first {@code count} predefined player setup entries
     * @throws IndexOutOfBoundsException if {@code count} is less than 0 or greater than the number of predefined players
     */
    static List<PlayerSetup> createPlayers(int count) {
        List <PlayerSetup> players = List.of(new PlayerSetup("Player1", PlayerColor.RED),
                new PlayerSetup("Player2", PlayerColor.BLUE),
                new PlayerSetup("Player3", PlayerColor.GREEN),
                new PlayerSetup("Player4", PlayerColor.YELLOW));
        return players.subList(0, count);
    }

    @Test
    void acceptsValidSetup() {
        var errorsList = SetupValidator.validate(createPlayers(4), Difficulty.EASY);

        assertTrue(errorsList.isEmpty());
    }

    @Test
    void rejectsNullDifficulty() {
        var errorsList = SetupValidator.validate(createPlayers(4), null);
        var expectedList = List.of("Please select a difficulty");

        assertEquals(expectedList, errorsList);
    }

    @Test
    void rejectsNullPlayers() {
        var errorsList = SetupValidator.validate(null, Difficulty.EASY);
        var expectedList = List.of("Please add players");

        assertEquals(expectedList, errorsList);
    }


    @Test
    void rejectsZeroPlayers() {
        var errorsList = SetupValidator.validate(createPlayers(0), Difficulty.EASY);
        var expectedList = List.of("Please add between 2 and 4 players");

        assertEquals(expectedList, errorsList);
    }

    @Test
    void rejectsSinglePlayer() {
        var errorsList = SetupValidator.validate(createPlayers(1), Difficulty.EASY);
        var expectedList = List.of("Please add between 2 and 4 players");

        assertEquals(expectedList, errorsList);
    }

    @Test
    void rejectsMoreThanFourPlayers() {
        var playersList = List.of(
                new PlayerSetup("Player1", PlayerColor.RED),
                new PlayerSetup("Player2", PlayerColor.BLUE),
                new PlayerSetup("Player3", PlayerColor.GREEN),
                new PlayerSetup("Player4", PlayerColor.YELLOW),
                new PlayerSetup("Player5", PlayerColor.BLUE)
        );

        var errorsList = SetupValidator.validate(playersList, Difficulty.EASY);
        var expectedList = List.of("Please add between 2 and 4 players",
                "Please use unique player colors");

        assertEquals(expectedList, errorsList);
    }

    @Test
    void rejectsDuplicateNamesIgnoringCase() {
        var players = List.of(new PlayerSetup("Player", PlayerColor.BLUE),
                new PlayerSetup("player", PlayerColor.RED));

        var errorsList = SetupValidator.validate(players, Difficulty.EASY);
        var expectedList = List.of("Please use unique player names");

        assertEquals(expectedList, errorsList);
    }

    @Test
    void rejectsDuplicateColors() {
        var players = List.of(new PlayerSetup("Player", PlayerColor.BLUE),
                new PlayerSetup("Player2", PlayerColor.BLUE));

        var errorsList = SetupValidator.validate(players, Difficulty.EASY);
        var expectedList = List.of("Please use unique player colors");

        assertEquals(expectedList, errorsList);
    }

    @Test
    void rejectsDuplicateNamesAndColors() {
        var players = List.of(new PlayerSetup("Player", PlayerColor.RED),
                new PlayerSetup("Player", PlayerColor.RED));

        var errorsList = SetupValidator.validate(players, Difficulty.EASY);
        var expectedList = List.of("Please use unique player names",
                "Please use unique player colors");

        assertEquals(expectedList, errorsList);
    }

    @Test
    void rejectsNullPlayersAndDifficulty() {
        var errorsList = SetupValidator.validate(null, null);
        var expectedList = List.of("Please select a difficulty",
                "Please add players");

        assertEquals(expectedList, errorsList);
    }

}
