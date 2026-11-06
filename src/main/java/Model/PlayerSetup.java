package Model;

/**
 * One player's name and color as entered on the setup screen.
 */
public record PlayerSetup(String name, PlayerColor color) {
    /**
     * Creates a player setup entry with a validated name and color.
     * The name is trimmed before it is stored, must not be blank, and must not contain commas.
     * The color must not be null.
     *
     * @param name the player's name entered on the setup screen
     * @param color the player's selected color
     * @throws IllegalArgumentException if the name is null, blank, the name contains a comma, or the color is null
     */
    public PlayerSetup {
        name = validateName(name);
        validateColor(color);
    }

    /**
     * Validates a player name and returns the trimmed value.
     * The name must not be null, blank, or contain commas.
     *
     * @param name the player name to validate
     * @return the validated player name with leading and trailing whitespace removed
     * @throws IllegalArgumentException if the name is null, blank, or contains a comma
     */
    private static String validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Player name cannot be null");
        }
        if (name.isBlank()) { // do not accept blank names
            throw new IllegalArgumentException("Player name cannot be empty");
        }
        if (name.contains(",")) { // do not accept names with commas
            throw new IllegalArgumentException("Player name cannot contain commas");
        }
        return name.trim(); // use the trimmed name
    }

    /**
     * Validates that a player color is not null.
     *
     * @param color the player color to validate
     * @throws IllegalArgumentException if the color is null
     */
    private static void validateColor(PlayerColor color) {
        if (color == null) {
            throw new IllegalArgumentException("Player color cannot be null");
        }
    }
}
