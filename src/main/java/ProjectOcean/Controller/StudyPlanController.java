package ProjectOcean.Controller;

import ProjectOcean.Controller.FunctionalInterfaces.AddIconToScreen;
import ProjectOcean.Controller.FunctionalInterfaces.RelocateDraggedObjectToCursor;
import ProjectOcean.Controller.FunctionalInterfaces.ShowDetailedInformationWindow;
import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
import ProjectOcean.Model.IYear;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents a graphical component of a study plan.
 */
class StudyPlanController extends VBox implements Observer {

    @FXML private VBox yearContentView;
    @FXML private Button addYearButton;
    @FXML private ScrollPane scrollPane;

    private final CoursePlanningSystem model;
    private final List<YearController> yearControllers;
    private final RelocateDraggedObjectToCursor relocateDraggedObjectToCursor;
    private final AddIconToScreen addIconToScreen;
    private final VisualFeedback visualFeedback;
    private final ShowDetailedInformationWindow showDetailedInformationWindow;

    public StudyPlanController(CoursePlanningSystem model, RelocateDraggedObjectToCursor relocateDraggedObjectToCursor, AddIconToScreen addIconToScreen, VisualFeedback visualFeedback, ShowDetailedInformationWindow showDetailedInformationWindow) {
        this.relocateDraggedObjectToCursor = relocateDraggedObjectToCursor;
        this.addIconToScreen = addIconToScreen;
        this.yearControllers = new ArrayList<>();
        this.model = model;
        this.visualFeedback = visualFeedback;
        this.showDetailedInformationWindow = showDetailedInformationWindow;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/StudyPlanWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        // Add observer
        model.addObserver(this);
        updateControllerAccordingToModel();
        displayAllYearsInSchedule();

    }

    /**
     * Updates the view according to the model.
     */
    @Override
    public void update(Observable o, Object arg) {
        updateControllerAccordingToModel();
        displayAllYearsInSchedule();
    }

    private void scrollDownScrollPane() {
        scrollPane.layout();
        scrollPane.setVvalue(1.0);
    }

    private void displayAllYearsInSchedule() {
        yearContentView.getChildren().clear();
        for (YearController yearController: yearControllers) {
            yearContentView.getChildren().add(yearController);
        }
    }

    /**
     * Displays visual feedback depending on where the course is placed.
     * @param course is the current object being drag and dropped.
     */
    public void setVisualFeedbackForCoursePlacement(ICourse course){
        for (YearController yearController: yearControllers) {
            yearController.setBorderColorInSlots(course.getStudyPeriod());
        }
    }

    private void updateControllerAccordingToModel() {
        deleteObserversAndChildren();

        List<IYear> years = model.getYears();
        int yearIndex = 0;

        for (IYear y : years) {
            yearIndex++;
            yearControllers.add(new YearController(y, model, relocateDraggedObjectToCursor, addIconToScreen, yearIndex, visualFeedback, showDetailedInformationWindow ));
        }
    }

    private void deleteObserversAndChildren(){
        for (YearController yearController: yearControllers) {
            yearController.deleteFromObserver();
        }
        yearControllers.clear();
    }

    @FXML
    private void addYear() {
        model.addYear();
        scrollDownScrollPane();
    }
}
