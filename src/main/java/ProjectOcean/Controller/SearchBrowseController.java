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
import java.util.List;
import java.util.UUID;

/**
 * Represents the visual component of the search bar and list of courses
 */
public class SearchBrowseController extends AnchorPane {

    @FXML private VBox searchResultVBox;
    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private CheckBox studyPointCheckBox15;
    @FXML private CheckBox studyPointCheckBox7_5;
    @FXML private CheckBox studyPeriodCheckbox1;
    @FXML private CheckBox studyPeriodCheckbox2;
    @FXML private CheckBox studyPeriodCheckbox3;
    @FXML private CheckBox studyPeriodCheckbox4;

    private CoursePlanningSystem model;
    private ApplicationController applicationController;
    private List<UUID> currentSearchResult;

    /**
     *
     * @param model: An instance of the course planning system
     * @param applicationController: An instance of the main controller.
     */
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
        //If no search text, show all courses, else execute search through model method, and adds the id:s to currentSearchResult
        //list
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
        filterAndAddCourses();
    }

    private void filterAndAddCourses() {
        //For each id in the search result, check if its corresponding course study period matches any
        //of the selected study period checkboxes, in which case it, through filterAndAddCourseBasedOnStudyPoint(),
        //checks if the course study points matches any of the selected study points checkpoints, at which point it is displayed.
        //If all or none of the study period checkboxes are selected, all courses that matches the study points checkboxes are shown.
        for (UUID id : currentSearchResult) {
            if (studyPeriodCheckbox1.isSelected() && Integer.parseInt(model.getStudyPeriod(id)) == 1) {
                filterAndAddCourseBasedOnStudyPoint(id);
            } else if (studyPeriodCheckbox2.isSelected() && Integer.parseInt(model.getStudyPeriod(id)) == 2) {
                filterAndAddCourseBasedOnStudyPoint(id);
            } else if (studyPeriodCheckbox3.isSelected() && Integer.parseInt(model.getStudyPeriod(id)) == 3) {
                filterAndAddCourseBasedOnStudyPoint(id);
            } else if (studyPeriodCheckbox4.isSelected() && Integer.parseInt(model.getStudyPeriod(id)) == 4) {
                filterAndAddCourseBasedOnStudyPoint(id);
            } else if (!studyPeriodCheckbox1.isSelected() && !studyPeriodCheckbox2.isSelected() && !studyPeriodCheckbox3.isSelected() && !studyPeriodCheckbox4.isSelected()) {
                filterAndAddCourseBasedOnStudyPoint(id);
            }
        }
    }

    private void filterAndAddCourseBasedOnStudyPoint(UUID id) {
        //Checks for the course corresponding to the id if its study points matches any of the
        //selected study points checkboxes and if so displays it. If all or none of the study points checkboxes
        //are selected, the course is shown regardless.
        if (studyPointCheckBox7_5.isSelected() && Float.parseFloat(model.getStudyPoints(id)) == 7.5f) {
            CourseListIconController iconController = new CourseListIconController(id, model, applicationController);
            searchResultVBox.getChildren().add(iconController);
        } else if (studyPointCheckBox15.isSelected() && Float.parseFloat(model.getStudyPoints(id)) == 15f) {
            CourseListIconController iconController = new CourseListIconController(id, model, applicationController);
            searchResultVBox.getChildren().add(iconController);
        } else if (!studyPointCheckBox7_5.isSelected() && !studyPointCheckBox15.isSelected()) {
            CourseListIconController iconController = new CourseListIconController(id, model, applicationController);
            searchResultVBox.getChildren().add(iconController);
        }
    }
}
