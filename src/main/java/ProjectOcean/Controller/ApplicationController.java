package ProjectOcean.Controller;

import java.io.IOException;
import java.util.List;

import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the root visual object, only contains empty containers
 */

public class ApplicationController extends AnchorPane implements VisualFeedback {

    @FXML private VBox contentWindow;
    @FXML private AnchorPane dragFeature;
    @FXML private AnchorPane searchBrowseWindow;

    private final CoursePlanningSystem model;
    private final SearchBrowseController searchBrowseController;
    private final WorkspaceController workspaceController;
    private final ScheduleController scheduleController;
    private static DetailedController detailedController;
    private final HostServices hostServices;

    public ApplicationController(HostServices hostServices) {
        this.hostServices = hostServices;
        this.model = CoursePlanningSystem.getInstance();
        this.searchBrowseController = new SearchBrowseController(model, this, this::showDetailedInformationWindow, this::addIconToScreen);
        this.workspaceController = new WorkspaceController(model, this, this::relocateDraggedObjectToCursor, this::showDetailedInformationWindow, this::addIconToScreen, this::removeMovableChild);
        this.scheduleController = new ScheduleController(model, this::relocateDraggedObjectToCursor, this::addIconToScreen);
        detailedController = new DetailedController(this::showStudyPlanWorkspaceWindow, hostServices);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ApplicationWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        instantiateChildControllers();
    }



    /**
     * Clears contentWindow's current window and implicitly shows StudyPlan and Workspace
     */
    public void showStudyPlanWorkspaceWindow(){
        contentWindow.getChildren().clear();
        contentWindow.getChildren().add(workspaceController);
        contentWindow.getChildren().add(scheduleController);
    }


    @FXML
    private void onDragOver(DragEvent event) {

        Movable draggedObject = (Movable) event.getGestureSource();
        relocateDraggedObjectToCursor(draggedObject, event);

        event.consume();

    }

    @FXML
    private void onDragDone(DragEvent event) {

        Movable draggedObject = (Movable) event.getGestureSource();
        getChildren().remove(draggedObject);
        model.update();
        event.consume();

    }

    private void instantiateChildControllers() {
        contentWindow.getChildren().add(0, workspaceController);
        searchBrowseWindow.getChildren().add(searchBrowseController);
        contentWindow.getChildren().add(1, scheduleController);
    }

    /**
     * Adds the icon to the drag surface
     * @param icon the movable icon to be added
     */
    public void addIconToScreen(Movable icon){
        dragFeature.getChildren().add((Node)icon);
    }

    /**
     * Moves the icon to the cursor position
     * @param icon the icon to be moved
     * @param event the event representing the mouse drag
     */
    public void relocateDraggedObjectToCursor(Movable icon, DragEvent event){
        Point2D mousePosition = new Point2D(event.getSceneX(), event.getSceneY());
        icon.relocateToPoint(mousePosition);
    }

    /**
     * Clears and adds a detailedController to the contentWindow
     * @param course the ICourse representing the course from which the details will be taken from
     */
    public void showDetailedInformationWindow(ICourse course) {
        contentWindow.getChildren().clear();
        detailedController.setDetailedInfo(course);
        contentWindow.getChildren().add(detailedController);
    }

    /**
     * Makes the course slots in schedule light up with a color responding to if the course can be placed or not.
     */
    public void showAvailablePlacementInSchedule(ICourse course){
        scheduleController.setVisualFeedbackForCoursePlacement(course);
    }

    public void removeCourse(int yearID, int studyPeriod, int slot){
        model.removeCourse(yearID, studyPeriod, slot);
    }

    /**
     * @return the hostServices instance
     */
    public HostServices getHostServices() {
        return hostServices;
    }


    /**
     * Method is called from the menubar in the view
     */
    @FXML
    public void onSaveClicked(){
        saveStudent();
    }

    /**
     * Method saves all properties of student in a json file
     */
    public void saveStudent(){
        model.saveStudentToJSON();
    }

    private void removeMovableChild(Movable course) {
        this.getChildren().remove(course);
    }

}
