package ProjectOcean.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ResourceBundle;

public class DetailedController extends VBox {

    @FXML
    private VBox detailedViewRoot;
    @FXML
    private Label studyPeriod;
    @FXML
    private Label examinator;
    @FXML
    private Label examinationMeans;
    @FXML
    private Label language;
    @FXML
    private VBox requiredCourses;
    @FXML
    private Hyperlink coursePM;
    @FXML
    private Label courseDescription;

    

    public DetailedController() {
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("Internationalization/Lang_sv");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/DetailedWindow.fxml"), bundle);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        detailedViewRoot.setVgrow(this, Priority.ALWAYS);

    }


}
