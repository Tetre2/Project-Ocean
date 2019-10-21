package ProjectOcean.Controller;

import java.io.IOException;

import ProjectOcean.IO.*;
import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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

    private final CoursePlanningSystem model;
    private final SearchBrowseController searchBrowseController;
    private final WorkspaceController workspaceController;
    private final ScheduleController scheduleController;
    private static DetailedController detailedController;
    private final HostServices hostServices;
    private static ICourseLoader courseSaveLoader = SaveloaderFactory.createICourseSaveLoader();
    private static IStudyPlanSaverLoader studyPlanSaverLoader = SaveloaderFactory.createIStudyPlanSaverLoader();

    public ApplicationController(HostServices hostServices) {
        this.hostServices = hostServices;
        this.model = CoursePlanningSystem.getInstance();
        initiateModel();
        this.searchBrowseController = new SearchBrowseController(model, this::showDetailedInformationWindow, this::addIconToScreen);
        this.workspaceController = new WorkspaceController(model, this::moveDraggedObjectToCursor, this::showDetailedInformationWindow, this::addIconToScreen, this::removeMovableChild);
        this.scheduleController = new ScheduleController(model, this::moveDraggedObjectToCursor, this::addIconToScreen);
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
    public void showStudyPlanWorkspaceWindow() {
        contentWindow.getChildren().clear();
        contentWindow.getChildren().add(workspaceController);
        contentWindow.getChildren().add(scheduleController);
    }

    @FXML
    private void onDragOver(DragEvent event) {
        Movable draggedObject = (Movable) event.getGestureSource();
        moveDraggedObjectToCursor(draggedObject, event);

        event.consume();
    }

    @FXML
    private void onDragDone(DragEvent event) {
        Movable draggedObject = (Movable) event.getGestureSource();
        getChildren().remove(draggedObject);
        event.consume();

    }

    private void instantiateChildControllers() {
        contentWindow.getChildren().add(0, workspaceController);
        searchBrowseWindow.getChildren().add(searchBrowseController);
        contentWindow.getChildren().add(1, scheduleController);
    }

    private void initiateModel() {
        tryLoadCoursesFromJSON();
        tryLoadWorkspaceFromJSON();
        tryLoadStudyplansFromJSON();
        tryLoadCurrentStudyplanFromJSON();
    }

    private void tryLoadCoursesFromJSON() {
        try {
            model.fillModelWithCourses(courseSaveLoader.loadCoursesFile());
        } catch (CoursesNotFoundException e) {
            ButtonType buttonType = showAndGetResultFromDialogBox();
            if (buttonType == ButtonType.YES) {
                courseSaveLoader.createCoursesFile();
                tryLoadCoursesFromJSON();
            } else {
                System.exit(0);
            }
        }
    }

    private void tryLoadWorkspaceFromJSON(){
        try {
            model.setWorkspace(studyPlanSaverLoader.loadWorkspace());
            return;
        } catch (StudyPlanNotFoundException e) {
        } catch (OldStudyplanExeption oldStudyplanExeption) {
        }
        ButtonType buttonType = showAndGetResultFromDialogBox();
        if (buttonType == ButtonType.YES) {
            studyPlanSaverLoader.createNewStudentFile();
            tryLoadWorkspaceFromJSON();
        } else {
            System.exit(0);
        }
    }

    private void tryLoadStudyplansFromJSON() {
        try {
            model.setStudyPlans(studyPlanSaverLoader.loadStudyplans());
            return;
        } catch (StudyPlanNotFoundException e) {
        } catch (OldStudyplanExeption oldStudyplanExeption) {
        }
        ButtonType buttonType = showAndGetResultFromDialogBox();
        if (buttonType == ButtonType.YES) {
            studyPlanSaverLoader.createNewStudentFile();
            tryLoadStudyplansFromJSON();
        } else {
            System.exit(0);
        }
    }

    private void tryLoadCurrentStudyplanFromJSON(){
        try {
            model.setCurrentStudyPlan(studyPlanSaverLoader.loadCurrentStudyPlan(model.getStudent().getAllStudyPlans()));
            return;
        } catch (StudyPlanNotFoundException e) {
        } catch (OldStudyplanExeption oldStudyplanExeption) {
        }
        ButtonType buttonType = showAndGetResultFromDialogBox();
        if (buttonType == ButtonType.YES) {
            studyPlanSaverLoader.createNewStudentFile();
            tryLoadCurrentStudyplanFromJSON();
        } else {
            System.exit(0);
        }
    }

    private ButtonType showAndGetResultFromDialogBox(){
        Alert alert = new Alert(Alert.AlertType.NONE, "Could not find file!\n" + "Do you want to create a new file", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        return alert.getResult();
    }

    /**
     * Adds the icon to the drag surface
     *
     * @param icon the movable icon to be added
     */
    public void addIconToScreen(Movable icon) {
        dragFeature.getChildren().add((Node) icon);
    }

    /**
     * Moves the icon to the cursor position
     *
     * @param icon  the icon to be moved
     * @param event the event representing the mouse drag
     */
    public void moveDraggedObjectToCursor(Movable icon, DragEvent event) {
        Point2D mousePosition = new Point2D(event.getSceneX(), event.getSceneY());
        icon.relocateToPoint(mousePosition);
    }

    /**
     * Clears and adds a detailedController to the contentWindow
     *
     * @param course the ICourse representing the course from which the details will be taken from
     */
    public void showDetailedInformationWindow(ICourse course) {
        contentWindow.getChildren().clear();
        detailedController.setDetailedInfo(course);
        contentWindow.getChildren().add(detailedController);
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
    public void onSaveClicked() {
        saveModel();
    }

    /**
     * Method saves all properties of student in a json file
     */
    public void saveModel() {
        studyPlanSaverLoader.saveModel(model.getStudent());
    }

    private void removeMovableChild(Movable course) {
        this.getChildren().remove(course);
    }

}
