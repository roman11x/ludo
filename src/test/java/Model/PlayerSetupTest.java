package Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerSetupTest {

    @Test
    void nullPlayerNameRejected() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new PlayerSetup(null, PlayerColor.RED));

        assertTrue(e.getMessage().contains("Player name cannot be null"));
    }

    @Test
    void nullPlayerColorRejected() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new PlayerSetup("Player", null));

        assertTrue(e.getMessage().contains("Player color cannot be null"));
    }

    @Test
    void emptyPlayerNameRejected() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new PlayerSetup("", PlayerColor.RED));

        assertTrue(e.getMessage().contains("Player name cannot be blank"));
    }

    @Test
    void blankPlayerNameRejected() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new PlayerSetup("  ", PlayerColor.RED));

        assertTrue(e.getMessage().contains("Player name cannot be blank"));
    }

    @Test
    void playerNameWithCommaRejected() {
        var e = assertThrows(IllegalArgumentException.class,
                () -> new PlayerSetup("Player,Name", PlayerColor.RED));

        assertTrue(e.getMessage().contains("Player name cannot contain commas"));
    }

    @Test
    void untrimmedPlayerNameIsTrimmed() {
        var player = new PlayerSetup("  Player  ", PlayerColor.RED);

        assertEquals("Player", player.name());
    }

    @Test
    void spacesInsidePlayerNameSurvive() {
        var player = new PlayerSetup(" Player Name ", PlayerColor.RED);

        assertEquals("Player Name", player.name());
    }

    @Test
    void playerSetupCreatedNormally() {
        var player = new PlayerSetup("Player", PlayerColor.RED);

        assertEquals(new PlayerSetup("Player", PlayerColor.RED), player);
    }
}
