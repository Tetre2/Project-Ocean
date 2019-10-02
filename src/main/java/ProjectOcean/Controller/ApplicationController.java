package ProjectOcean.Controller;

import java.io.IOException;
import java.util.UUID;
import ProjectOcean.Model.CoursePlanningSystem;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Represents the root visual object, only contains empty containers
 */
public class ApplicationController extends VBox {

    @FXML private AnchorPane searchBrowseWindow;
    @FXML private VBox contentWindow;

    private final static CoursePlanningSystem coursePlanningSystem = new CoursePlanningSystem();
    private static DetailedController detailedController;
    private SearchBrowseController searchBrowseController;
    private StudyPlanController studyPlanController = new StudyPlanController(coursePlanningSystem);
    private HostServices hostServices;

    public ApplicationController(HostServices hostServices) {
        this.hostServices = hostServices;
        this.searchBrowseController = new SearchBrowseController(this.coursePlanningSystem, this);
        detailedController = new DetailedController(coursePlanningSystem, this);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ApplicationWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        contentWindow.getChildren().add( studyPlanController);
        searchBrowseWindow.getChildren().add(searchBrowseController);
    }

    public void showDetailedInformation(UUID id){
        contentWindow.getChildren().clear();
        detailedController.setDetailedInfo(id);
        contentWindow.getChildren().add(detailedController);
    }

    public void showStudyPlanWorkspaceWindow(){
        contentWindow.getChildren().clear();
    }

    public HostServices getHostServices() {
        return hostServices;
    }
}
