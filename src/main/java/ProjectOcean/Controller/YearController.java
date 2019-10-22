package ProjectOcean.Controller;

import ProjectOcean.Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
import ProjectOcean.Model.IYear;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.*;

/**
 * Represents the visual graphic component of a year.
 */
public class YearController extends VBox implements Observer {

    @FXML private GridPane yearGrid;
    @FXML private Button removeYearButton;
    @FXML private Label yearLabel;

    private final CoursePlanningSystem model;
    private final IYear year;
    private final RelocateDraggedObjectToCursor relocateDraggedObjectToCursor;
    private final AddIconToScreen addIconToScreen;
    private final VisualFeedback visualFeedback;
    private final ShowDetailedInformationWindow showDetailedInformationWindow;

    private Map<ICourse, Tuple<Integer,Integer>> coursesInYear;
    private ICourse courseTmp;


    public YearController(IYear year, CoursePlanningSystem model, RelocateDraggedObjectToCursor relocateDraggedObjectToCursor, AddIconToScreen addIconToScreen, int yearIndex, VisualFeedback visualFeedback, ShowDetailedInformationWindow showDetailedInformationWindow) {
        this.model = model;
        this.year = year;
        this.relocateDraggedObjectToCursor = relocateDraggedObjectToCursor;
        this.addIconToScreen = addIconToScreen;
        this.coursesInYear = new HashMap<>();
        this.visualFeedback = visualFeedback;
        this.showDetailedInformationWindow = showDetailedInformationWindow;

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
        this.yearLabel.setText(String.valueOf(yearIndex));
        displayAllCoursesInStudyPlan();

    }

    /**
     * Method controlling valid transfer modes within GridPane
     * @param event Move over GridPane event
     */
    @FXML
    private void onDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        Movable draggedObject = (Movable) event.getGestureSource();

        relocateDraggedObjectToCursor.relocateDraggedObjectToCursor(draggedObject, event);
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

        model.addCourse(icon.getICourse(), year.getID(), studyPeriod, slot);

        event.setDropCompleted(true);
        relocateDraggedObjectToCursor.relocateDraggedObjectToCursor(icon, event);
        event.consume();
    }

    /**
     * Deletes the this from the observer list in the model.
     */
    public void deleteFromObserver(){
        model.deleteObserver(this);
    }

    public void setGreenBorderColorInSlots(String studyPeriod){
        //TODO gör sen
      //  System.out.println("YEARGRID CHildren:" + yearGrid.getChildren());

        List<Node> slots = yearGrid.getChildren();

        for (Node slot: slots) {
            //Make sure that the group that displays the gridlines are not counted.
            if(slots.indexOf(slot) != 0) {
               // System.out.println("SLOT: " + slot);
                Integer column = 1 + GridPane.getColumnIndex(slot);
                //int row = 1 +GridPane.getRowIndex(slot);
                if (column == Integer.parseInt(studyPeriod)) {
                    slot.setStyle("-fx-border-color: green;" + "-fx-border-width: 3;" + "-fx-border-radius: 3");
                } else {
                    slot.setStyle("-fx-border-color: red;"  + "-fx-border-width: 3;" + "-fx-border-radius: 3");
                }
            }
        }
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
       // System.out.println("UPDATE");
        updateCoursesInMap();
        displayAllCoursesInStudyPlan();
    }

    private void updateCoursesInMap(){
        //TODO erase comments

        coursesInYear.clear();
        //TODO Check with Fille that I use year.getID in the right way.
        IYear y = model.getStudent().getCurrentStudyPlan().getYear(year.getID());

        //System.out.println("----------------\n");
        if( y !=null){
            for (int studyPeriod = 1; studyPeriod <= y.getStudyPeriodsSize(); studyPeriod++) {
                for (int slot = 1; slot <= 2; slot++) {
                    ICourse course = y.getCourseInStudyPeriod(studyPeriod,slot);
                    if(course != null) {
                        courseTmp = new Course(
                                course.getCourseCode(),
                                course.getCourseName(),
                                course.getStudyPoints(),
                                course.getStudyPeriod(),
                                course.getExaminer(),
                                course.getExaminationMeans(),
                                course.getLanguage(),
                                course.getRequiredCourses(),
                                course.getCoursePMLink(),
                                course.getCourseDescription(),
                                course.getCourseTypes()
                        );

                        coursesInYear.put( courseTmp , new Tuple<>(studyPeriod, slot));


                    /*    System.out.println("COURSE CODE: "
                                + courseTmp.getCourseCode()
                                + " SP: "
                                + coursesInYear.get(courseTmp).getStudyPeriod()
                                + " S: "
                                + coursesInYear.get(courseTmp).getSlot());*/
                    }

                }
            }
        }
    }

    /**
     * Removes a given course in the model.
     * @param course
     */
    public void removeCourse(ICourse course){
        //TODO, do a try catch here or handle potential errors.
        Tuple<Integer, Integer> location = coursesInYear.get(course);
        model.removeCourse(year.getID(), location.getStudyPeriod(), location.getSlot());
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
         //   System.out.print(yearGrid.getChildren().get(1));
            //TODO maybe do a check if the element is not a group instead?
            yearGrid.getChildren().remove(1);
        }
    }

    //TODO change name to actual thing
    private void addCourseControllersAccordingToModel() {
        for(Map.Entry<ICourse,Tuple<Integer,Integer>> entry : coursesInYear.entrySet()){
            Tuple<Integer, Integer> location = entry.getValue();

            if(entry.getKey() != null) {
                CourseController course = new CourseController(
                        entry.getKey(),
                        model,
                        visualFeedback,
                        showDetailedInformationWindow,
                        this.addIconToScreen
                ) ;
                yearGrid.add(course, location.getStudyPeriod() - 1,location.getSlot() - 1);

                //Check whether we add it to a "correct" study period and otherwise make the background orange.
                if(Integer.parseInt(entry.getKey().getStudyPeriod()) != (location.getStudyPeriod())){
                    course.setStyle("-fx-background-color: orange");
                }
            }
        }

        //Checks where courses in the model are null so we can add empty panes in those slots. (Needed for visual feedback)
        IYear y = model.getStudent().getCurrentStudyPlan().getYear(year.getID());

        if(y != null){
            for (int studyPeriod = 1; studyPeriod <= y.getStudyPeriodsSize(); studyPeriod++) {
                for (int slot = 1; slot <= 2; slot++) {
                    ICourse course = y.getCourseInStudyPeriod(studyPeriod, slot);
                    if (course == null) {
                        yearGrid.add(new Pane(), studyPeriod - 1, slot - 1);
                    }
                }
            }
        }

      //  System.out.println(yearGrid.getChildren());

    }

    @FXML
    public void removeYear() {
        model.removeYear(year.getID());
    }
}
