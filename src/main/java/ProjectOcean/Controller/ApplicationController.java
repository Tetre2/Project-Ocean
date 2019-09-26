package ProjectOcean.Controller;

import java.io.IOException;

import ProjectOcean.Model.CourseInfo;
import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ApplicationController extends VBox {

    private CoursePlanningSystem coursePlanningSystem;
    private SearchBrowseController searchBrowseController;

    @FXML
    private AnchorPane searchBrowseWindow;

    public ApplicationController() {
        this.coursePlanningSystem = new CoursePlanningSystem(new CourseInfo());
        this.searchBrowseController = new SearchBrowseController(this.coursePlanningSystem);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ApplicationWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        searchBrowseWindow.getChildren().add(searchBrowseController);

    }

}
