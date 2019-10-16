package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Represents a graphical component of a study plan.
 */
public class ScheduleController extends VBox {

    @FXML private VBox yearContentView;

    private final YearController yearController;

    public ScheduleController(CoursePlanningSystem model, MoveDraggedObjectToCursor moveDraggedObjectToCursor, AddIconToScreen addIconToScreen) {
        this.yearController = new YearController(1, model, moveDraggedObjectToCursor, addIconToScreen);

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
