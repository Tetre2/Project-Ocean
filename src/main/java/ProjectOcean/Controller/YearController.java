package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
import ProjectOcean.Model.Year;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

/**
 * Represents the visual graphic component of a year.
 */
public class YearController extends VBox implements Observer {

    @FXML private GridPane yearGrid;

    private final CoursePlanningSystem model;
    private final ApplicationController applicationController;
    private final int year;


    public YearController(int year, CoursePlanningSystem model, ApplicationController applicationController) {
        this.model = model;
        this.year = year;
        this.applicationController = applicationController;


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/YearView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        model.addObserver(this);

        displayAllCoursesInStudyPlan();

    }

    /**
     * Method controlling valid transfer modes within GridPane
     * @param event Move over GridPane event
     */
    @FXML
    private void onDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        Movable icon = (Movable) event.getGestureSource();

        applicationController.moveDraggedObjectToCursor(icon, event);
        event.consume();
    }

    /**
     * Method taking care of dropping a course in corresponding box in Years GridPane
     * @param event Release event in a GridPane
     */
    @FXML
    private void onDragRelease(DragEvent event) {
        int studyPeriod = calculateStudyPeriod(event.getX());
        int slot = calculateSlot(event.getY());

        event.acceptTransferModes(TransferMode.MOVE);
        Movable icon = (Movable) event.getGestureSource();

        model.addCourse(icon.getICourse(), year, studyPeriod, slot);

        event.setDropCompleted(true);
        applicationController.moveDraggedObjectToCursor(icon, event);
        event.consume();
    }

    /**
     * Method calculating study period depending on user mouse input
     * @param x mouse x-coordinate
     * @return index representing the study period
     */
    private int calculateStudyPeriod(double x) {
        double slotWidth = yearGrid.getWidth()/4;
        for (int i = 1; i <= 4; i++) {
            if(x < (slotWidth * i)){
                return i;
            }
        }
        return -1;
    }

    /**
     * Method calculating slot depending on user mouse input
     * @param y mouse y-coordinate
     * @return index representing the slot
     */
    private int calculateSlot(double y) {
        if (y < yearGrid.getHeight() / 2) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        displayAllCoursesInStudyPlan();
    }


    /**
     * Displays all the courses that are currently in the model's study plan
     */
    private void displayAllCoursesInStudyPlan(){
        clearStudyPlanGridPane();
        addCourseControllersAccordingToModel();
    }

    private void clearStudyPlanGridPane() {
        int nElements = yearGrid.getChildren().size() - 1;
        for (int i = 0; i < nElements; i++) {
            yearGrid.getChildren().remove(1);
        }
    }

    private void addCourseControllersAccordingToModel() {
        Year y = model.getStudent().getCurrentStudyPlan().getSchedule().getYear(year);

        for (int studyPeriod = 1; studyPeriod <= y.getStudyPeriods().size(); studyPeriod++) {
            for (int slot = 1; slot <= 2; slot++) {

                if(slot == 1){
                    ICourse course = y.getStudyPeriod(studyPeriod).getCourse1();
                    if(course != null) {
                        yearGrid.add(new ScheduleCourseController(model, course, applicationController, year, studyPeriod, slot), studyPeriod - 1, slot - 1);
                    }
                }else{
                    ICourse course = y.getStudyPeriod(studyPeriod).getCourse2();
                    if(course != null) {
                        yearGrid.add(new ScheduleCourseController(model, course, applicationController, year, studyPeriod , slot), studyPeriod - 1, slot - 1);
                    }
                }

            }
        }
    }
}
