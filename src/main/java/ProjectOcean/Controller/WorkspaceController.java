package ProjectOcean.Controller;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * Controls the workspace in the application
 */

public class WorkspaceController extends VBox implements Observer {

    @FXML FlowPane workspaceContainer;

    private ApplicationController applicationController;
    private CoursePlanningSystem model;

    public WorkspaceController(CoursePlanningSystem model, ApplicationController applicationController) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/WorkspaceWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.applicationController = applicationController;
        this.model = model;
        model.addObserver(this);

        displayAllCoursesInWorkspace();
    }

    @FXML
    private void onDragOver(DragEvent event){
        event.acceptTransferModes(TransferMode.MOVE);
        Movable icon = (Movable) event.getGestureSource();

        applicationController.moveIconToCursor(icon,event);
        event.consume();
    }

    @FXML
    private void onDragReleased(DragEvent event){

        Movable course = (Movable) event.getGestureSource();
        model.addCourseToWorkspace(course.getUUID());

        displayAllCoursesInWorkspace();
        event.setDropCompleted(true);

        applicationController.getChildren().remove(course);

        event.consume();
    }

    private void displayAllCoursesInWorkspace() {
        workspaceContainer.getChildren().clear();
        for(UUID id : model.getCoursesInWorkspaceIDs()) {
            CourseListIconController iconController = new CourseListIconController(id, model, applicationController);
            workspaceContainer.getChildren().add(iconController);
        }
    }

    /**
     * Updates the view according to the model.
     */
    @Override
    public void update(Observable o, Object arg) {
        displayAllCoursesInWorkspace();
    }
}
