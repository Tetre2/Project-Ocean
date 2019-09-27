package ProjectOcean.Controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class WorkspaceController extends Pane {

    public WorkspaceController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/WorkspaceWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
