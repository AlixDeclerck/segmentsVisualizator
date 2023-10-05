package be.umons.BSPHI;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static javafx.fxml.FXMLLoader.load;

/**
 * TestInteractif launch the application with graphical interface
 */
public class TestInteractif extends Application {

    public static final String TITLE = "View Heuristics (group9) Implementation";
    public static final String MAIN_PAGE = "userInterfaces/navigation/main.fxml";

    @Override
    public void start(Stage s) {
        try {
            Parent root = load(getClass().getResource(MAIN_PAGE));
            Scene scene = new Scene(root);
            s.setTitle(TITLE);
            s.setScene(scene);
            s.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
