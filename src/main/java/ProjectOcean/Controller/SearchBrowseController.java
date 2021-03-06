package ProjectOcean.Controller;

import ProjectOcean.Controller.FunctionalInterfaces.AddIconToScreen;
import ProjectOcean.Controller.FunctionalInterfaces.ShowDetailedInformationWindow;
import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the visual component of the search bar and list of courses
 */
class SearchBrowseController extends AnchorPane {

    @FXML private VBox searchResultVBox;
    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private CheckBox studyPointCheckBox15;
    @FXML private CheckBox studyPointCheckBox7_5;
    @FXML private CheckBox studyPeriodCheckbox1;
    @FXML private CheckBox studyPeriodCheckbox2;
    @FXML private CheckBox studyPeriodCheckbox3;
    @FXML private CheckBox studyPeriodCheckbox4;

    private final CoursePlanningSystem model;
    private List<ICourse> currentSearchResult;
    private final ShowDetailedInformationWindow showDetailedInformationWindow;
    private final AddIconToScreen addIconToScreen;
    private final VisualFeedback visualFeedback;

    /**
     * @param model: An instance of the course planning system
     * @param showDetailedInformationWindow callback to the showDetailedInformationWindow method
     * @param addIconToScreen callback to the addIconToScreen method
     * @param visualFeedback callback to the method showAvailablePlacementInSchedule
     */
    public SearchBrowseController(CoursePlanningSystem model, VisualFeedback visualFeedback, ShowDetailedInformationWindow showDetailedInformationWindow, AddIconToScreen addIconToScreen) {
        this.model = model;
        this.showDetailedInformationWindow = showDetailedInformationWindow;
        this.addIconToScreen = addIconToScreen;
        this.visualFeedback = visualFeedback;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/SearchBrowseWindow.fxml"));
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

    @FXML
    private void onActionSearchButton() {
        executeSearch();
    }

    @FXML
    private void onActionSearchField() {
        executeSearch();
    }

    private void executeSearch() {
        //If no search text, show all courses, else execute search through model method, and adds the id:s to currentSearchResult
        //list
        if (searchField.getText().isEmpty()) {
            currentSearchResult = model.getAllCourses();
        } else {
            currentSearchResult = model.executeSearch(searchField.getText());
        }
        filterAndDisplayCourses();
    }

    @FXML
    private void onActionCheckbox() {
        filterAndDisplayCourses();
    }

    private void filterAndDisplayCourses() {
        searchResultVBox.getChildren().clear();

        List<ICourse> filteredSearchResult = new ArrayList<>(currentSearchResult);
        filterBasedOnStudyPeriod(filteredSearchResult);
        filterBasedOnStudyPoints(filteredSearchResult);
        //Displays filtered result
        for(ICourse course : filteredSearchResult) {
            CourseController courseController = new CourseController(course, model, visualFeedback, showDetailedInformationWindow, addIconToScreen, null);
            searchResultVBox.getChildren().add(courseController);
        }
    }

    private void filterBasedOnStudyPeriod(List<ICourse> filteredSearchResult) {
        //Copies filteredSearchResult and clears it, and for each ICourse in the copy, check if its study period matches any
        //of the selected study period checkboxes, in which case it is added to filteredSearchResult.
        //If all or none of the study period checkboxes are selected, all courses that matches the study points checkboxes are shown.
        List<ICourse> preFilterSearchResult = new ArrayList<>(filteredSearchResult);
        filteredSearchResult.clear();
        for (ICourse course : preFilterSearchResult) {
            if (studyPeriodCheckbox1.isSelected() && Integer.parseInt(course.getStudyPeriod()) == 1) {
                filteredSearchResult.add(course);
            } else if (studyPeriodCheckbox2.isSelected() && Integer.parseInt(course.getStudyPeriod()) == 2) {
                filteredSearchResult.add(course);
            } else if (studyPeriodCheckbox3.isSelected() && Integer.parseInt(course.getStudyPeriod()) == 3) {
                filteredSearchResult.add(course);
            } else if (studyPeriodCheckbox4.isSelected() && Integer.parseInt(course.getStudyPeriod()) == 4) {
                filteredSearchResult.add(course);
            } else if (!studyPeriodCheckbox1.isSelected() && !studyPeriodCheckbox2.isSelected() && !studyPeriodCheckbox3.isSelected() && !studyPeriodCheckbox4.isSelected()) {
                filteredSearchResult.add(course);
            }
        }
    }

    private void filterBasedOnStudyPoints(List<ICourse> filteredSearchResult) {
        //Copies filteredSearchResult and clears it, and for each ICourse in the copy, check if its study period matches any
        //of the selected study period checkboxes, in which case it is added to filteredSearchResult. If all or none of the study points checkboxes
        //are selected, the course is shown regardless.
        List<ICourse> preFilterSearchResult = new ArrayList<>(filteredSearchResult);
        filteredSearchResult.clear();
        for (ICourse course : preFilterSearchResult) {
            if (studyPointCheckBox7_5.isSelected() && Float.parseFloat(course.getStudyPoints()) == 7.5f) {
                filteredSearchResult.add(course);
            } else if (studyPointCheckBox15.isSelected() && Float.parseFloat(course.getStudyPoints()) == 15f) {
                filteredSearchResult.add(course);
            } else if (!studyPointCheckBox7_5.isSelected() && !studyPointCheckBox15.isSelected()) {
                filteredSearchResult.add(course);
            }
        }
    }
}
