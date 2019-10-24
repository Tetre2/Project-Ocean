package ProjectOcean.Controller;

import java.io.IOException;

import ProjectOcean.IO.CourseLoader;
import ProjectOcean.IO.Exceptions.CoursesNotFoundException;
import ProjectOcean.IO.Exceptions.StudyPlanNotFoundException;
import ProjectOcean.IO.Exceptions.OldFileException;
import ProjectOcean.IO.SaverLoaderFactory;
import ProjectOcean.IO.StudyPlanSaverLoader;
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
    @FXML private AnchorPane studyPlanWindow;

    private final CoursePlanningSystem model;
    private final SearchBrowseController searchBrowseController;
    private final WorkspaceController workspaceController;
    private final StudyPlanController studyPlanController;
    private final StudyPlanSelectorController studyPlanSelectorController;
    private static DetailedController detailedController;
    private static final CourseLoader courseSaveLoader = SaverLoaderFactory.createICourseSaveLoader();
    private static final StudyPlanSaverLoader studyPlanSaverLoader = SaverLoaderFactory.createIStudyPlanSaverLoader();

    public ApplicationController(HostServices hostServices) {
        this.model = CoursePlanningSystem.getInstance();
        initiateModel();
        this.studyPlanSelectorController = new StudyPlanSelectorController(model, this::toggleStudyPlanWindow);
        this.searchBrowseController = new SearchBrowseController(model, this::showDetailedInformationWindow, this::addIconToScreen);
        this.workspaceController = new WorkspaceController(model, this::moveDraggedObjectToCursor, this::showDetailedInformationWindow, this::addIconToScreen, this::removeMovableChild);
        this.studyPlanController = new StudyPlanController(model, this::moveDraggedObjectToCursor, this::addIconToScreen);
        detailedController = new DetailedController(this::showStudyPlanWorkspaceWindow, hostServices);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/ApplicationWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        populateWithChildControllers();
        toggleStudyPlanWindow();
    }

    /**
     * Clears contentWindow's current window and implicitly shows StudyPlan and Workspace
     */
    private void showStudyPlanWorkspaceWindow() {
        contentWindow.getChildren().clear();
        contentWindow.getChildren().add(workspaceController);
        contentWindow.getChildren().add(studyPlanController);
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

    private void toggleStudyPlanWindow() {
        if (isStudyPlanWindowVisible()) {
            removeCurrentStudyPlanController();
        }
        // Create and show a new Controller based on currentStudyPlan, if there is some study plan
        if (studyPlanExists()) {
            StudyPlanController studyPlanController = new StudyPlanController(model, this::moveDraggedObjectToCursor, this::addIconToScreen);
            addNewStudyPlanController(studyPlanController);
            model.updateOnStudyPlanClicked();
        }
    }

    private boolean isStudyPlanWindowVisible() {
        return contentWindow.getChildren().size() == 2;
    }

    private boolean studyPlanExists() {
        return model.getAllStudyPlans().size() > 0;
    }

    /**
     * Remove the active study plan in the content window: StudyPlanController
     */
    private void removeCurrentStudyPlanController() {
        contentWindow.getChildren().remove(1);
    }

    private void populateWithChildControllers() {
        contentWindow.getChildren().add(0, workspaceController);
        searchBrowseWindow.getChildren().add(searchBrowseController);
        contentWindow.getChildren().add(1, studyPlanController);
        studyPlanWindow.getChildren().add(studyPlanSelectorController);
    }

    private void initiateModel() {
        tryLoadCoursesFromJSON();
        tryLoadWorkspaceFromJSON();
        tryLoadStudyPlansFromJSON();
        tryLoadCurrentStudyPlanFromJSON();
    }

    private void tryLoadCoursesFromJSON() {
        Alert alert;
        try {
            model.fillModelWithCourses(courseSaveLoader.loadCoursesFile());
            return;
        } catch (CoursesNotFoundException e) {
            alert = new Alert(Alert.AlertType.WARNING, "Could not find/load courses!\n" + "You have probably removed/moved the courses.json file from its origin\n" + "Try to download the program again", ButtonType.CLOSE);
            alert.showAndWait();
        }catch (OldFileException e) {
            alert = new Alert(Alert.AlertType.WARNING, "Old courses!\n" + "Update the program", ButtonType.CLOSE);
            alert.showAndWait();
        }
        if (alert.getResult() == ButtonType.CLOSE) {
            System.exit(0);
        }

    }

    private void tryLoadWorkspaceFromJSON(){
        Alert alert;
        try {
            model.setWorkspace(studyPlanSaverLoader.loadWorkspace());
            return;
        } catch (StudyPlanNotFoundException e) {
            alert = new Alert(Alert.AlertType.NONE, "Could not find file!\n" + "Do you want to create a new file", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
        } catch (OldFileException oldFileException) {
            alert = new Alert(Alert.AlertType.NONE, "Old version of study plan found!\n" + "Do you want to create a new with the right version", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
        }

        if (alert.getResult() == ButtonType.YES) {
            studyPlanSaverLoader.createNewStudentFile();
            tryLoadWorkspaceFromJSON();
        } else {
            System.exit(0);
        }
    }

    private void tryLoadStudyPlansFromJSON() {
        Alert alert;
        try {
            model.setStudyPlans(studyPlanSaverLoader.loadStudyPlans());
            return;
        }  catch (StudyPlanNotFoundException e) {
            alert = new Alert(Alert.AlertType.NONE, "Could not find file!\n" + "Do you want to create a new file", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
        } catch (OldFileException oldFileException) {
            alert = new Alert(Alert.AlertType.NONE, "Old version of study plan found!\n" + "Do you want to create a new with the right version", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
        }

        if (alert.getResult() == ButtonType.YES) {
            studyPlanSaverLoader.createNewStudentFile();
            tryLoadWorkspaceFromJSON();
        } else {
            System.exit(0);
        }
    }

    private void tryLoadCurrentStudyPlanFromJSON(){
        Alert alert;
        try {
            model.setCurrentStudyPlan(studyPlanSaverLoader.loadCurrentStudyPlan(model.getStudent().getAllStudyPlans()));
            return;
        }  catch (StudyPlanNotFoundException e) {
            alert = new Alert(Alert.AlertType.NONE, "Could not find file!\n" + "Do you want to create a new file", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
        } catch (OldFileException oldFileException) {
            alert = new Alert(Alert.AlertType.NONE, "Old version of study plan found!\n" + "Do you want to create a new with the right version", ButtonType.YES, ButtonType.NO);
            alert.showAndWait();
        }

        if (alert.getResult() == ButtonType.YES) {
            studyPlanSaverLoader.createNewStudentFile();
            tryLoadWorkspaceFromJSON();
        } else {
            System.exit(0);
        }
    }

    /**
     * Adds the icon to the drag surface
     *
     * @param icon the movable icon to be added
     */
    private void addIconToScreen(Movable icon) {
        dragFeature.getChildren().add((Node) icon);
    }

    /**
     * Moves the icon to the cursor position
     *
     * @param icon  the icon to be moved
     * @param event the event representing the mouse drag
     */
    private void moveDraggedObjectToCursor(Movable icon, DragEvent event) {
        Point2D mousePosition = new Point2D(event.getSceneX(), event.getSceneY());
        icon.relocateToPoint(mousePosition);
    }

    /**
     * Clears and adds a detailedController to the contentWindow
     *
     * @param course the ICourse representing the course from which the details will be taken from
     */
    private void showDetailedInformationWindow(ICourse course) {
        contentWindow.getChildren().clear();
        detailedController.setDetailedInfo(course);
        contentWindow.getChildren().add(detailedController);
    }

    /**
     * Method is called from the menubar in the view
     */
    @FXML
    private void onSaveClicked() {
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
     * @param StudyPlanController the study plan to be shown
     */
    private void addNewStudyPlanController(StudyPlanController StudyPlanController) {
        contentWindow.getChildren().add(1, StudyPlanController);
    }

    private void removeMovableChild(Movable course) {
        this.getChildren().remove(course);
    }

}
