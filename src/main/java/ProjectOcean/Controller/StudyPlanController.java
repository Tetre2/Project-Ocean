package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Represents a graphical component of a study plan.
 */
public class StudyPlanController extends VBox {

    @FXML private VBox yearContentView;

    private final CoursePlanningSystem model;
    private YearController yearController;

    public StudyPlanController(CoursePlanningSystem model) {
        this.model = model;
        yearController = new YearController(model, 0);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/StudyPlanWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //Puts a first instance of a year into the study plan
        yearContentView.getChildren().add(0, yearController);
    }

}
