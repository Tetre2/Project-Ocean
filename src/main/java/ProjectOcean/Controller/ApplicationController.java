package ProjectOcean.Controller;

import java.io.IOException;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ApplicationController extends VBox {

    private CoursePlanningSystem coursePlanningSystem;
    private SearchBrowseController searchBrowseController;

    @FXML
    private AnchorPane searchBrowseWindow;

    @FXML
    private VBox contentWindow;

    public ApplicationController(HostServices hostServices) {
        this.coursePlanningSystem = new CoursePlanningSystem();
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
        contentWindow.getChildren().add(new DetailedController(null, hostServices));

    }

}
