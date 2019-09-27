package ProjectOcean.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ResourceBundle;

public class StudyPlanController extends VBox {

    @FXML
    private VBox yearContentView;

    private IModelStudyPlan model;
    YearController yearController = new YearController();

    public StudyPlanController() {
        //ResourceBundle bundle = java.util.ResourceBundle.getBundle("Internationalization/Lang_sv");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ProjectOcean/View/StudyPlanWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        yearContentView.getChildren().add(0, yearController);
    }

}
