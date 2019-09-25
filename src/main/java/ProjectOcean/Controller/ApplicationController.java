package ProjectOcean.Controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;

public class ApplicationController extends VBox {

    @FXML VBox contentWindow;

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

        WorkspaceController workspace = new WorkspaceController();

        contentWindow.getChildren().add(0, workspace);
    }

}
