package View;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * The JavaFX application: creates the primary window and shows the first scene.
 */
public class LudoApp extends Application {

    @Override
    public void start(Stage stage) {
        // Set the window title
        stage.setTitle("Ludo – Team Sloth");
        // Create a label with the app name as placeholder content
        Label label = new Label("Ludo – Team Sloth");
        // Put the label in a layout pane that centers its content
        StackPane root = new StackPane(label);
        // Create an 800x600 scene containing the layout pane
        Scene scene = new Scene(root, 800, 600);
        // Attach the scene to the window
        stage.setScene(scene);
        // Show the window
        stage.show();
    }

    public static void main(String[] args) {
        // Launch the JavaFX runtime, which calls start()
        launch(args);
    }
}
