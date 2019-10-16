package ProjectOcean.Controller;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
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

    @FXML private FlowPane workspaceContainer;

    private final ApplicationController applicationController;
    private final CoursePlanningSystem model;

    public WorkspaceController(CoursePlanningSystem model, ApplicationController applicationController) {
        this.applicationController = applicationController;
        this.model = model;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/WorkspaceWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        model.addObserver(this);

        displayAllCoursesInWorkspace();
    }

    @FXML
    private void onDragOver(DragEvent event){
        event.acceptTransferModes(TransferMode.MOVE);
        Movable icon = (Movable) event.getGestureSource();

        applicationController.moveDraggedObjectToCursor(icon,event);
        event.consume();
    }

    @FXML
    private void onDragReleased(DragEvent event){

        Movable course = (Movable) event.getGestureSource();
        model.addCourseToWorkspace(course.getICourse());

        event.setDropCompleted(true);

        applicationController.getChildren().remove(course);

        event.consume();
    }

    private void displayAllCoursesInWorkspace() {
        workspaceContainer.getChildren().clear();
        for(ICourse course : model.getCoursesInWorkspace()) {
            CourseListIconController iconController = new CourseListIconController(course, model, applicationController);
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
