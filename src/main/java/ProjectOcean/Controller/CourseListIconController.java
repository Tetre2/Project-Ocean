package ProjectOcean.Controller;

import ProjectOcean.Model.IModelCourseListIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.UUID;

public class CourseListIconController {

    @FXML
    private Text courseCodeText;

    @FXML
    private Text courseNameText;

    @FXML
    private Text studyPointsText;

    private static IModelCourseListIcon model;

    private UUID id;

    public CourseListIconController(UUID id, IModelCourseListIcon model) {
        this.model = model;
        this.id = id;
        populateIcon();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ProjectOcean/View/CourseView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void populateIcon() {
        this.courseCodeText.setText(this.model.getCourseCode(this.id));
        this.courseNameText.setText(this.model.getCourseName(this.id));
        this.studyPointsText.setText(this.model.getCourseStudyPoints(this.id));
    }
}
