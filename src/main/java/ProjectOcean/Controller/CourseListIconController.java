package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.UUID;

public class CourseListIconController extends VBox {

    @FXML
    private Text courseCodeText;

    @FXML
    private Text courseNameText;

    @FXML
    private Text studyPointsText;

    private static CoursePlanningSystem model;

    private UUID id;

    public CourseListIconController(UUID id, CoursePlanningSystem model) {
        this.model = model;
        this.id = id;



        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/CourseView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        populateIcon();
    }

    private void populateIcon() {
        //TODO This method might not be the best way to trim course name in the case that its to long.
        String courseName = this.model.getCourseName(this.id);
        if (courseName.length() > 25) {
            courseName = courseName.substring(0, 27) + "...";
        }
        this.courseNameText.setText(courseName);
        this.courseCodeText.setText(this.model.getCourseCode(this.id));
        this.studyPointsText.setText(this.model.getCourseStudyPoints(this.id) + " hp");
    }
}
