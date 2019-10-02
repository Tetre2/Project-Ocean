package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Constructor loads a graphical representation of a study plan as a fxml-file and creates a instance of YearController.
 */
public class StudyPlanController extends VBox {

    @FXML private VBox yearContentView;

    //TODO: use this to add a year in model CPS
    private final CoursePlanningSystem model;

    //private IModelStudyPlan model;
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

        yearContentView.getChildren().add(0, yearController);
    }

}
