package ProjectOcean;

import ProjectOcean.Controller.ApplicationController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Starts the application
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Course Planning System");
        ApplicationController root = new ApplicationController(getHostServices());
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();

    }
}
