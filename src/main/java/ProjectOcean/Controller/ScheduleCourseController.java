package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.UUID;

/**
 * Represents the visual graphic component of a course in study plan.
 */
public class ScheduleCourseController extends VBox {

    @FXML private Label courseCodeLabel;
    private final CoursePlanningSystem model;
    private final UUID id;

    public ScheduleCourseController(CoursePlanningSystem model, UUID id) {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ScheduleCourseView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.model = model;
        this.id = id;
        this.courseCodeLabel.setText(model.getCourse(id).getCourseCode());
    }

}
