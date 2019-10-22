package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;

import ProjectOcean.Model.ICourse;
import ProjectOcean.Model.IYear;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents a graphical component of a study plan.
 */
public class ScheduleController extends VBox implements Observer {

    @FXML private VBox yearContentView;
    @FXML private Button addYearButton;
    private CoursePlanningSystem model;

    private List<IYear> years;
    private final List<YearController> yearControllers;
    private final RelocateDraggedObjectToCursor relocateDraggedObjectToCursor;
    private final AddIconToScreen addIconToScreen;
    private final VisualFeedback visualFeedback;
    private final ShowDetailedInformationWindow showDetailedInformationWindow;

    public ScheduleController(CoursePlanningSystem model, RelocateDraggedObjectToCursor relocateDraggedObjectToCursor, AddIconToScreen addIconToScreen, VisualFeedback visualFeedback, ShowDetailedInformationWindow showDetailedInformationWindow) {
        this.relocateDraggedObjectToCursor = relocateDraggedObjectToCursor;
        this.addIconToScreen = addIconToScreen;
        this.yearControllers = new ArrayList<>();
        this.model = model;
        this.visualFeedback = visualFeedback;
        this.showDetailedInformationWindow = showDetailedInformationWindow;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/StudyPlanWindow.fxml"));
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

    @Override
    public void update(Observable o, Object arg) {
        updateControllerAccordingToModel();
        displayAllYearsInSchedule();
    }

    private void displayAllYearsInSchedule() {
        yearContentView.getChildren().clear();
        for (YearController yearController: yearControllers) {
            yearContentView.getChildren().add(yearController);
        }
    }

    public void setVisualFeedbackForCoursePlacement(ICourse course){
        for (YearController yearController: yearControllers) {
            yearController.setGreenBorderColorInSlots(course.getStudyPeriod());
        }
    }

    private void updateControllerAccordingToModel() {
        years = model.getYears();
        for (YearController yearController: yearControllers) {
            yearController.deleteFromObserver();
        }

        yearControllers.clear();
        int yearIndex = 0;

        for (IYear y :
                years) {
            yearIndex++;
            //System.out.println("YEAR");
            yearControllers.add(new YearController(y, model, relocateDraggedObjectToCursor, addIconToScreen, yearIndex, visualFeedback, showDetailedInformationWindow ));
        }
    }

    @FXML
    public void addYear() {
        model.addYear();
    }
}
