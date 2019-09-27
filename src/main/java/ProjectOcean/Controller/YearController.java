package ProjectOcean.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Constructor loads a graphical representation of a year as a fxml-file.
 */
public class YearController extends VBox {

    public YearController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ProjectOcean/View/YearView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
