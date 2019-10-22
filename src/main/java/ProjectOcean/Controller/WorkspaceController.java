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

    private final CoursePlanningSystem model;
    private final MoveDraggedObjectToCursor moveDraggedObjectToCursor;
    private final ShowDetailedInformationWindow showDetailedInformationWindow;
    private final AddIconToScreen addIconToScreen;
    private final RemoveMovableChild removeMovableChild;

    public WorkspaceController(CoursePlanningSystem model,
                               MoveDraggedObjectToCursor moveDraggedObjectToCursor,
                               ShowDetailedInformationWindow showDetailedInformationWindow,
                               AddIconToScreen addIconToScreen,
                               RemoveMovableChild removeMovableChild) {

        this.moveDraggedObjectToCursor = moveDraggedObjectToCursor;
        this.showDetailedInformationWindow = showDetailedInformationWindow;
        this.addIconToScreen = addIconToScreen;
        this.model = model;
        this.removeMovableChild = removeMovableChild;


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

        moveDraggedObjectToCursor.moveDraggedObjectToCursor(icon,event);
        event.consume();
    }

    @FXML
    private void onDragReleased(DragEvent event){

        Movable course = (Movable) event.getGestureSource();
        model.addCourseToWorkspace(course.getICourse());

        event.setDropCompleted(true);

        removeMovableChild.removeMovableChild(course);

        event.consume();
    }

    private void displayAllCoursesInWorkspace() {
        workspaceContainer.getChildren().clear();
        for(ICourse course : model.getCoursesInWorkspace()) {
            CourseListIconController iconController = new CourseListIconController(course, model, showDetailedInformationWindow, addIconToScreen);
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
