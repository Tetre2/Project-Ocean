package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Represents the visual component of the search bar and list of courses
 */
public class SearchBrowseController extends AnchorPane {

    @FXML
    private VBox searchResultVBox;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private CheckBox studyPointCheckBox15;
    @FXML
    private CheckBox studyPointCheckBox7_5;

    private CoursePlanningSystem model;
    private ApplicationController applicationController;
    private List<UUID> currentSearchResult;

    public SearchBrowseController(CoursePlanningSystem model, ApplicationController applicationController) {
        this.model = model;
        this.applicationController = applicationController;

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
        executeSearch();
    }

    private void displayAllCourses() {
        for (UUID id : model.getAllCoursesIDs()) {
            CourseListIconController iconController = new CourseListIconController(id, model, applicationController);
            searchResultVBox.getChildren().add(iconController);
        }
    }

    @FXML
    private void executeSearch() {
        if (searchField.getText().isEmpty()) {
            currentSearchResult = model.getAllCoursesIDs();
        } else {
            currentSearchResult = model.executeSearch(searchField.getText());
        }
        displayCourses();
    }

    @FXML
    private void displayCourses() {
        searchResultVBox.getChildren().clear();
        for (UUID id : currentSearchResult) {
            if (studyPointCheckBox7_5.isSelected() && Float.parseFloat(model.getStudyPoints(id)) == 7.5f) {
                CourseListIconController iconController = new CourseListIconController(id, model, applicationController);
                searchResultVBox.getChildren().add(iconController);
            } else if (studyPointCheckBox15.isSelected() && Float.parseFloat(model.getStudyPoints(id)) == 15f) {
                CourseListIconController iconController = new CourseListIconController(id, model, applicationController);
                searchResultVBox.getChildren().add(iconController);
            } else if(!studyPointCheckBox7_5.isSelected() && !studyPointCheckBox15.isSelected()) {
                CourseListIconController iconController = new CourseListIconController(id, model, applicationController);
                searchResultVBox.getChildren().add(iconController);
            }
        }
        System.out.println("Nu");
    }
}
