package ProjectOcean.Controller;

import java.io.IOException;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

/**
 * Constructor loads a graphical representation of the application as a fxml-file.
 * Creates an instance of class StudyPlanController.
 */
public class ApplicationController extends VBox {

    @FXML private VBox contentWindow;

    private final CoursePlanningSystem model;
    private final StudyPlanController studyPlanController;

    public ApplicationController() {
        this.model = new CoursePlanningSystem();
        this.studyPlanController = new StudyPlanController(model);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ProjectOcean/View/ApplicationWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        contentWindow.getChildren().add(1, studyPlanController);
    }

}
