package ProjectOcean.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;

import java.io.IOException;

public class ScheduleCourseController {

    @FXML private Label courseCodeLabel;

    public ScheduleCourseController() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ScheduleCourseView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
