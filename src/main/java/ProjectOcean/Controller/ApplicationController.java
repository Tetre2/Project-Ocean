package ProjectOcean.Controller;

import java.io.IOException;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ApplicationController extends VBox {

    private CoursePlanningSystem model;

    @FXML
    private VBox searchBrowseWindow;

    public ApplicationController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ApplicationWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.searchBrowseWindow.getChildren().add(new SearchBrowseController(this.model));
    }

}
