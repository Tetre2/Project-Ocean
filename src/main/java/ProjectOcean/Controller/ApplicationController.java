package ProjectOcean.Controller;

import java.io.IOException;
import java.util.UUID;

import ProjectOcean.IO.CourseSaverLoader;
import ProjectOcean.IO.StudyPlanSaverLoader;
import ProjectOcean.Model.CoursePlanningSystem;
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
public class ApplicationController extends AnchorPane {

    @FXML private VBox contentWindow;
    @FXML private AnchorPane dragFeature;
    @FXML private AnchorPane searchBrowseWindow;

    private CoursePlanningSystem coursePlanningSystem;
    private SearchBrowseController searchBrowseController;
    private WorkspaceController workspaceController;
    private final static StudyPlanSaverLoader studyPlanSaverLoader = new StudyPlanSaverLoader();
    private final static CourseSaverLoader courseSaverLoader = new CourseSaverLoader();
    private static DetailedController detailedController;
    private HostServices hostServices;

    public ApplicationController(HostServices hostServices) {
        this.hostServices = hostServices;
        try {
            this.coursePlanningSystem = new CoursePlanningSystem(studyPlanSaverLoader.loadStudent(), courseSaverLoader.loadStudyPlans());
        } catch (IOException e) {
            //file can't be found -> display error message + maybe create a new file
            e.printStackTrace();
        }
        this.searchBrowseController = new SearchBrowseController(coursePlanningSystem, this);
        this.workspaceController = new WorkspaceController(coursePlanningSystem, this);
        detailedController = new DetailedController(coursePlanningSystem, this);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ApplicationWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        contentWindow.getChildren().add(0, workspaceController);
        searchBrowseWindow.getChildren().add(searchBrowseController);

        courseSaverLoader.savePreMadeCourses();
    }

    @FXML
    private void onDragOver(DragEvent event) {

        Movable icon = (Movable) event.getGestureSource();
        moveIconToCursor(icon, event);

        event.consume();

    }

    @FXML
    private void onDragDone(DragEvent event) {

        Movable icon = (Movable) event.getGestureSource();
        getChildren().remove(icon);
        event.consume();

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
    public void moveIconToCursor(Movable icon, DragEvent event){
        icon.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
    }

    /**
     * Clears and adds a detailedController to the contentWindow
     * @param id the UUID representing the course from which the details will be taken from
     */
    public void showDetailedInformation(UUID id){
        contentWindow.getChildren().clear();
        detailedController.setDetailedInfo(id);
        contentWindow.getChildren().add(detailedController);
    }

    /**
     * Clears and adds the workspace component to the window
     */
    public void showStudyPlanWorkspaceWindow(){
        contentWindow.getChildren().clear();
        contentWindow.getChildren().add(workspaceController);
    }

    private void showStudyPlanNotFoudMessage(){

    }

    /**
     * @return the hostServices instance
     */
    public HostServices getHostServices() {
        return hostServices;
    }

    @FXML
    /**
     * Method is called from the menubar in the view
     */
    public void onSaveClicked(){
        saveStudent();
    }

    /**
     * Method saves all properties of student in a json file
     */
    public void saveStudent(){
        studyPlanSaverLoader.saveStudyplans(coursePlanningSystem.getStudent());
    }

}
