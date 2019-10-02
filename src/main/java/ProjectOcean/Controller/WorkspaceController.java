package ProjectOcean.Controller;

import java.io.IOException;
import java.util.UUID;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class WorkspaceController extends VBox {

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
    }

    @FXML
    public void courseDragOverEvent(DragEvent event){
        event.acceptTransferModes(TransferMode.MOVE);
        CourseListIconController icon = (CourseListIconController) event.getGestureSource();

        app.moveIconToCursor(icon,event);
        event.consume();
    }

    @FXML
    public void courseReleaseEvent(DragEvent event){

        CourseListIconController course = (CourseListIconController) event.getGestureSource();
        model.addCourseToWorkspace(course.getUUID());
     //   System.out.println(model.getCoursesInWorkspaceIDs());

        displayAllCoursesInWorkspace();
        event.setDropCompleted(true);

        //TODO Använd före och efter för att göra tester.
       // System.out.println("Före: "+ app.getChildren());
        app.getChildren().remove(course);

      //  System.out.println("Efter" + app.getChildren());
        event.consume();
    }

    private void displayAllCoursesInWorkspace() {
        workspaceContainer.getChildren().clear();
        for(UUID id : model.getCoursesInWorkspaceIDs()) {
            CourseListIconController iconController = new CourseListIconController(id, model, app);
            workspaceContainer.getChildren().add(iconController);
        }
    }

}
