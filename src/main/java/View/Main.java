package View;

/**
 * Launcher for the runnable JAR; separate from LudoApp so JavaFX starts correctly from a fat JAR.
 */
public class Main {
    public static void main(String[] args) {
        LudoApp.main(args);
    }
}
