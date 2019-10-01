package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.UUID;

/**
 * Represents the visual component of the search bar and list of courses
 */
public class SearchBrowseController extends AnchorPane {

    @FXML private VBox searchResultVBox;

    private CoursePlanningSystem model;

    public SearchBrowseController(CoursePlanningSystem model) {
        this.model = model;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/SearchBrowseWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //Displays all courses in CPS
        displayAllCourses();
    }

    private void displayAllCourses() {
        for(UUID id : model.getAllCoursesIDs()) {
            CourseListIconController iconController = new CourseListIconController(id, model);
            searchResultVBox.getChildren().add(iconController);
        }
    }
}
