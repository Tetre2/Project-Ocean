package ProjectOcean.Controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
import ProjectOcean.Model.IYear;
import ProjectOcean.Model.Course;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observer;
import java.util.Observable;

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


    /**
     * Sets the border color of all slots based on where the dragged course will fit.
     * @param studyPeriod of the course currently being dragged.
     */
    public void setBorderColorInSlots(String studyPeriod){
        List<Node> slots = yearGrid.getChildren();

        for (Node slot: slots) {
            //Makes sure that the first element (a group) that displays the gridlines is not counted.
            if(slots.indexOf(slot) != 0) {
                Integer column = 1 + GridPane.getColumnIndex(slot);
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

    /**
     * Gets called when the model is updated.
     * @param o is the observable
     * @param arg is an argument.
     */
    @Override
    public void update(Observable o, Object arg) {
        updateCoursesInMap();
        displayAllCoursesInStudyPlan();
    }

    private void updateCoursesInMap(){
        coursesInYear.clear();
        if( year != null){
            copyCoursesInModeltoMap();
        }
    }

    private void copyCoursesInModeltoMap(){
        for (int studyPeriod = 1; studyPeriod <= year.getStudyPeriodsSize(); studyPeriod++) {
            for (int slot = 1; slot <= 2; slot++) {
                ICourse course = year.getCourseInStudyPeriod(studyPeriod,slot);
                if(course != null) {
                    coursesInYear.put( createNewCopyOfCourse(course), new Tuple<>(studyPeriod, slot));
                }
            }
        }
    }

    private ICourse createNewCopyOfCourse(ICourse course){
        return new Course(
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
    }

    /**
     * Displays all the courses that are currently in the model's study plan
     */
    private void displayAllCoursesInStudyPlan(){
        clearStudyPlanGridPane();
        addCoursesToGridPane();
    }

    private void clearStudyPlanGridPane() {
        int nElements = yearGrid.getChildren().size() - 1;
        for (int i = 0; i < nElements; i++) {
            //Would be good if we could check it the element is "group" instead of relying on which order it are among the children.
            yearGrid.getChildren().remove(1);
        }
    }

    private void addCoursesToGridPane() {
        for(Map.Entry<ICourse,Tuple<Integer,Integer>> entry : coursesInYear.entrySet()){
            Tuple<Integer, Integer> location = entry.getValue();

            if(entry.getKey() != null){
                addCourseControllerToYearGrid(entry, location);
            }
        }
        fillEmptySlotsWithPanes();
    }

    private void addCourseControllerToYearGrid(Map.Entry<ICourse,Tuple<Integer,Integer>> entry, Tuple<Integer, Integer> location) {
        CourseController course = new CourseController(
                entry.getKey(),
                model,
                visualFeedback,
                showDetailedInformationWindow,
                this.addIconToScreen, this::removeCourse
        ) ;
        yearGrid.add(course, location.getStudyPeriod() - 1,location.getSlot() - 1);

        if(courseIsInWrongStudyPeriod(entry.getKey(), location.getStudyPeriod())){
            course.setStyle("-fx-background-color: orange");
        }
    }

    private boolean courseIsInWrongStudyPeriod(ICourse course, int studyPeriod){
        return Integer.parseInt(course.getStudyPeriod()) != (studyPeriod);
    }

    private void fillEmptySlotsWithPanes(){
        if(year != null){
            for (int studyPeriod = 1; studyPeriod <= year.getStudyPeriodsSize(); studyPeriod++) {
                for (int slot = 1; slot <= 2; slot++) {
                    ICourse course = year.getCourseInStudyPeriod(studyPeriod, slot);
                    if (course == null) {
                        yearGrid.add(new Pane(), studyPeriod - 1, slot - 1);
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
        Tuple<Integer, Integer> location = coursesInYear.get(course);
        if(location != null){
            model.removeCourse(year.getID(), location.getStudyPeriod(), location.getSlot());
        }
    }

    @FXML
    public void removeYear() {
        model.removeYear(year.getID());
    }
}
