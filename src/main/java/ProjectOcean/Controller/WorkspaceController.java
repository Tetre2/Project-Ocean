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

public class WorkspaceController extends VBox implements Observer {

    @FXML
    FlowPane workspaceContainer;

    private ApplicationController app;
    private CoursePlanningSystem model;

    public WorkspaceController(CoursePlanningSystem model, ApplicationController app) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/WorkspaceWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.app = app;
        this.model = model;
        model.addObserver(this);
    }

    @FXML
    public void onDragOver(DragEvent event){
        event.acceptTransferModes(TransferMode.MOVE);
        Movable icon = (Movable) event.getGestureSource();

        app.moveIconToCursor(icon,event);
        event.consume();
    }

    @FXML
    public void onDragReleased(DragEvent event){

        Movable course = (Movable) event.getGestureSource();
        model.addCourseToWorkspace(course.getUUID());
     //   System.out.println(model.getCoursesInWorkspaceIDs());

        displayAllCoursesInWorkspace();
        event.setDropCompleted(true);

        app.getChildren().remove(course);

        event.consume();
    }

    private void displayAllCoursesInWorkspace() {
        workspaceContainer.getChildren().clear();
        for(UUID id : model.getCoursesInWorkspaceIDs()) {
            CourseListIconController iconController = new CourseListIconController(id, model, app);
            workspaceContainer.getChildren().add(iconController);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        displayAllCoursesInWorkspace();
    }
}
