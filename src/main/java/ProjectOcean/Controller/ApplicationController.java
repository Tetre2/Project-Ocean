package ProjectOcean.Controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class ApplicationController extends VBox {

    @FXML
    private VBox contentWindow;

    StudyPlanController studyPlanController = new StudyPlanController();

    public ApplicationController() {
        //ResourceBundle bundle = java.util.ResourceBundle.getBundle("Internationalization/Lang_sv");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ProjectOcean/View/ApplicationWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        contentWindow.getChildren().add(1, studyPlanController);
    }

}
