package ProjectOcean.Controller;

import ProjectOcean.Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.*;

/**
 * Represents the visual graphic component of a year.
 */
public class YearController extends VBox implements Observer {

    @FXML private GridPane yearGrid;

    private final CoursePlanningSystem model;
    private final int year;
    private final RefactorDraggedObjectToCursor moveDraggedObjectToCursor;
    private final AddIconToScreen addIconToScreen;
    private final RemoveCourseFromSchedule removeCourseFromSchedule;

    private Map<ICourse, Tuple<Integer,Integer>> coursesInYear;
    private ICourse courseTmp;


    public YearController(int year, CoursePlanningSystem model, RefactorDraggedObjectToCursor moveDraggedObjectToCursor, AddIconToScreen addIconToScreen, RemoveCourseFromSchedule removeCourseFromSchedule) {
        this.model = model;
        this.year = year;
        this.moveDraggedObjectToCursor = moveDraggedObjectToCursor;
        this.addIconToScreen = addIconToScreen;
        this.removeCourseFromSchedule = removeCourseFromSchedule;
        this.coursesInYear = new HashMap<>();


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
        updateCoursesInMap();
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

        moveDraggedObjectToCursor.relocateDraggedObjectToCursor(icon, event);
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
        moveDraggedObjectToCursor.relocateDraggedObjectToCursor(icon, event);
        event.consume();
    }

    public void setGreenBorderColorInSlots(String studyPeriod){
        //TODO gör sen
      /*  System.out.println("YEARGRID CHildren:" + yearGrid.getChildren());
        List<Node> slots = yearGrid.getChildrenUnmodifiable();
        for (Node slot: slots) {
            System.out.println("SLOT: " + slot);
            int column = 1 + GridPane.getColumnIndex(slot);
            //int row = 1 +GridPane.getRowIndex(slot);
            if(column == Integer.parseInt(studyPeriod)){
                slot.setStyle("-fx-background-color: green");
            }
            else{
                slot.setStyle("-fx-background-color: red");
            }
        }*/
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
        updateCoursesInMap();
        displayAllCoursesInStudyPlan();
    }

    private void updateCoursesInMap(){
        //TODO erase comments
        coursesInYear.clear();
        IYear y = model.getStudent().getCurrentStudyPlan().getSchedule().getYear(year);

        System.out.println("----------------\n");
        for (int studyPeriod = 1; studyPeriod <= y.getStudyPeriodsSize(); studyPeriod++) {
            for (int slot = 1; slot <= 2; slot++) {
                ICourse course = y.getCourseInStudyPeriod(studyPeriod,slot);
                if(course != null) {
                    courseTmp = new Course (
                            course.getCourseCode(),
                            course.getCourseName(),
                            course.getStudyPoints(),
                            course.getStudyPeriod(),
                            course.getExaminer(),
                            course.getExaminationMeans(),
                            course.getLanguage(),
                            course.getRequiredCourses(),
                            course.getCoursePMLink(),
                            course.getCourseDescription()
                    );
                    coursesInYear.put( courseTmp , new Tuple<>(studyPeriod, slot));


                    System.out.println("COURSE CODE: "
                            + courseTmp.getCourseCode()
                            + " SP: "
                            + coursesInYear.get(courseTmp).getStudyPeriod()
                            + " S: "
                            + coursesInYear.get(courseTmp).getSlot());
                }

            }
        }
     //   System.out.println(coursesInYear.toString());
    }

    /**
     * Removes a given course in the model.
     * @param course
     */
    public void removeCourse(ICourse course){
        Tuple<Integer, Integer> location = coursesInYear.get(course);
        model.removeCourse(year, location.getStudyPeriod(), location.getSlot());
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

    //TODO change name to actual thing
    private void addCourseControllersAccordingToModel() {
        IYear y = model.getStudent().getCurrentStudyPlan().getSchedule().getYear(year);

        for(Map.Entry<ICourse,Tuple<Integer,Integer>> entry : coursesInYear.entrySet()){
            Tuple<Integer, Integer> location = entry.getValue();

            yearGrid.add(
                    new ScheduleCourseController(model, entry.getKey(), this.addIconToScreen, removeCourseFromSchedule), location.getStudyPeriod() -1, location.getSlot() -1);

        }
    }
}
