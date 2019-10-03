package ProjectOcean.Controller;

import java.io.IOException;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ApplicationController extends AnchorPane {

    @FXML private VBox contentWindow;
    @FXML private AnchorPane dragFeature;
    @FXML private AnchorPane searchBrowseWindow;

    private CoursePlanningSystem coursePlanningSystem;
    private SearchBrowseController searchBrowseController;
    private WorkspaceController workspaceController;

    public ApplicationController() {
        this.coursePlanningSystem = new CoursePlanningSystem();
        this.searchBrowseController = new SearchBrowseController(coursePlanningSystem, this);
        this.workspaceController = new WorkspaceController(coursePlanningSystem, this);

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
    }

    @FXML
    public void onDragOver(DragEvent event) {

        Movable icon = (Movable) event.getGestureSource();
        moveIconToCursor(icon, event);

        //Don't know whether this is necessary or not
        event.consume();

    }

    @FXML
    public void onDragDone(DragEvent event) {

        Movable icon = (Movable) event.getGestureSource();
        getChildren().remove(icon);
        event.consume();

    }

    public void addIconToScreen(Movable icon){
        //Kolla om det går att fixa en lösning där Movable interfacet direkt kräver att det är en Node.
        dragFeature.getChildren().add((Node)icon);
    }


    public void moveIconToCursor(Movable icon, DragEvent event){
        icon.relocateToPoint(new Point2D(event.getSceneX(), event.getSceneY()));
    }

}
