package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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

    private CoursePlanningSystem model;
    private ApplicationController applicationController;

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
        displayAllCourses();
    }

    private void displayAllCourses() {
        for(UUID id : model.getAllCoursesIDs()) {
            CourseListIconController iconController = new CourseListIconController(id, model, applicationController);
            searchResultVBox.getChildren().add(iconController);
        }
    }

    @FXML
    private void executeSearch() {
        searchResultVBox.getChildren().clear();
        if(searchField.getText().isEmpty()) {
            displayAllCourses();
        }
        else {
            String searchText = searchField.getText();
            searchText = searchText.trim();
            searchText = searchText.toLowerCase();
            searchText = searchText.trim().replaceAll(" +", " ");
            String[] searchTerms = searchText.split(" ");
            displayCourses(model.executeSearch(searchTerms));
        }
    }

    private void displayCourses(List<UUID> searchResult) {
        for(UUID id : searchResult) {
            CourseListIconController iconController = new CourseListIconController(id, model, applicationController);
            searchResultVBox.getChildren().add(iconController);
        }
    }
}
