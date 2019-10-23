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

public class ApplicationController extends AnchorPane implements VisualFeedback {

    @FXML private VBox contentWindow;
    @FXML private AnchorPane dragFeature;
    @FXML private AnchorPane searchBrowseWindow;
    @FXML private AnchorPane studyPlanWindow;

    private final CoursePlanningSystem model;
    private final SearchBrowseController searchBrowseController;
    private final WorkspaceController workspaceController;
    private final StudyPlanSelectorController studyPlanSelectorController;
    private ScheduleController scheduleController;
    private static DetailedController detailedController;
    private final HostServices hostServices;
    private static ICourseLoader courseSaveLoader = SaverLoaderFactory.createICourseSaveLoader();
    private static IStudyPlanSaverLoader studyPlanSaverLoader = SaverLoaderFactory.createIStudyPlanSaverLoader();

    public ApplicationController(HostServices hostServices) {
        this.hostServices = hostServices;
        this.model = CoursePlanningSystem.getInstance();

        initiateModel();

        this.searchBrowseController = new SearchBrowseController(model, this, this::showDetailedInformationWindow, this::addIconToScreen);
        this.workspaceController = new WorkspaceController(model, this, this::relocateDraggedObjectToCursor, this::showDetailedInformationWindow, this::addIconToScreen, this::removeMovableChild);
        this.scheduleController = new ScheduleController(model, this::relocateDraggedObjectToCursor, this::addIconToScreen, this, this::showDetailedInformationWindow);
        detailedController = new DetailedController(this::showStudyPlanWorkspaceWindow, hostServices);
        this.studyPlanSelectorController = new StudyPlanSelectorController(model, this::showCurrentStudyPlan);

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
        showCurrentStudyPlan();
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
        relocateDraggedObjectToCursor(draggedObject, event);
        event.consume();
    }

    @FXML
    private void onDragDone(DragEvent event) {
        Movable draggedObject = (Movable) event.getGestureSource();
        this.getChildren().remove(draggedObject);
        if (event.getTransferMode() == null){
            model.update();
        }
        event.consume();
    }

    @FXML
    private void onDeleteClicked() {
        studyPlanSelectorController.deleteCurrentStudyPlan();
        studyPlanSelectorController.showAllStudyPlanButtons();
        showCurrentStudyPlan();
    }

    private void showCurrentStudyPlan() {
        if (isScheduleViewVisible()) {
            removeCurrentScheduleController();
        }
        // Create and show a new Controller based on currentStudyPlan, if there is some study plan
        if (studyPlanExists()) {
            scheduleController = new ScheduleController(model, this::relocateDraggedObjectToCursor, this::addIconToScreen, this, this::showDetailedInformationWindow);
            addNewStudyPlanController(scheduleController);
        }
    }

    private boolean isScheduleViewVisible() {
        return contentWindow.getChildren().size() == 2;
    }

    private boolean studyPlanExists() {
        return model.getAllStudyPlans().size() > 0;
    }

    /**
     * Remove the active study plan in the content window: ScheduleController
     */
    private void removeCurrentScheduleController() {
        contentWindow.getChildren().remove(1);
    }

    private void instantiateChildControllers() {
        contentWindow.getChildren().add(0, workspaceController);
        searchBrowseWindow.getChildren().add(searchBrowseController);
        studyPlanWindow.getChildren().add(studyPlanSelectorController);
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
            return;
        } catch (CoursesNotFoundException e) {
        }catch (OldFileException e) {
        }
        ButtonType buttonType = showAndGetResultFromDialogBox();
        if (buttonType == ButtonType.YES) {
            courseSaveLoader.createCoursesFile();
            tryLoadCoursesFromJSON();
        } else {
            System.exit(0);
        }

    }

    private void tryLoadWorkspaceFromJSON(){
        try {
            model.setWorkspace(studyPlanSaverLoader.loadWorkspace());
            return;
        } catch (StudyPlanNotFoundException e) {
        } catch (OldFileException oldFileException) {
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
        } catch (OldFileException oldFileException) {
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
        } catch (OldFileException oldFileException) {
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
    public void relocateDraggedObjectToCursor(Movable icon, DragEvent event){
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
     * Makes the course slots in schedule light up with a color responding to if the course can be placed or not.
     */
    public void showAvailablePlacementInSchedule(ICourse course){
        scheduleController.setVisualFeedbackForCoursePlacement(course);
    }

    /**
     * Removes a course from the model.
     * @param yearID of the year that the course is placed.
     * @param studyPeriod where the course is currently at.
     * @param slot where the course is placed.
     */
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
    public void onSaveClicked() {
        saveModel();
    }

    /**
     * Method saves all properties of student in a json file
     */
    public void saveModel() {
        studyPlanSaverLoader.saveModel(model);
    }

    /**
     * Adds the study plan to be shown in the lower part of content window
     * @param scheduleController the study plan to be shown
     */
    public void addNewStudyPlanController(ScheduleController scheduleController) {
        contentWindow.getChildren().add(1, scheduleController);
    }

    private void removeMovableChild(Movable course) {
        this.getChildren().remove(course);
    }

}
