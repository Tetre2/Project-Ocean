package ProjectOcean.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ResourceBundle;

public class DetailedController extends VBox {

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

    }


}
