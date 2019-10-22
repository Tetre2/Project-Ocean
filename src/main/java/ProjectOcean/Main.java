package ProjectOcean;

import ProjectOcean.Controller.ApplicationController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Starts the application
 */
public class Main extends Application {

    private ApplicationController root;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Course Planning System");
        root = new ApplicationController(getHostServices());
        primaryStage.setScene(new Scene(root, 1200, 800));
        primaryStage.show();
    }

    /**
     * This method is run when the program terminates, it saves the parameters the student have
     */
    @Override
    public void stop() {
        root.saveModel();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
