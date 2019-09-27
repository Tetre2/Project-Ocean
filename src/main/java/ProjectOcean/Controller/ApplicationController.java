package ProjectOcean.Controller;

import java.io.IOException;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ApplicationController extends VBox {

    @FXML VBox contentWindow;
    @FXML private AnchorPane searchBrowseWindow;

    private CoursePlanningSystem coursePlanningSystem;
    private SearchBrowseController searchBrowseController;
    private WorkspaceController workspaceController;



    public ApplicationController() {
        this.coursePlanningSystem = new CoursePlanningSystem();
        this.searchBrowseController = new SearchBrowseController(this.coursePlanningSystem);
        this.workspaceController = new WorkspaceController();

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

}
