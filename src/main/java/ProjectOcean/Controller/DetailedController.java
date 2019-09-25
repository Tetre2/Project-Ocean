package ProjectOcean.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class DetailedController extends VBox {

    public DetailedController() {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/DetailedWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }


}
