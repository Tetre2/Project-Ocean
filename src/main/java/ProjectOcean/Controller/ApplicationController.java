package ProjectOcean.Controller;

import java.io.IOException;
import java.util.UUID;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ApplicationController extends VBox {

    @FXML private AnchorPane searchBrowseWindow;
    @FXML private VBox contentWindow;

    private final static CoursePlanningSystem coursePlanningSystem = new CoursePlanningSystem();
    private static DetailedController detailedController;
    private SearchBrowseController searchBrowseController;

    public ApplicationController(HostServices hostServices) {
        this.searchBrowseController = new SearchBrowseController(this.coursePlanningSystem, this);
        detailedController = new DetailedController(coursePlanningSystem, hostServices);

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
        contentWindow.getChildren().add(detailedController);



    }

    public void showDetailedInformation(UUID id){
        detailedController.setDetailedInfo(id);
    }


}
