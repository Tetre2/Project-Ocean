package ProjectOcean.Controller;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Constructor loads a graphical representation of a year as a fxml-file.
 */
public class YearController extends VBox {

    @FXML private GridPane sp1sp2;
    @FXML private GridPane sp3sp4;
    @FXML private Label yearLabel;

    private final CoursePlanningSystem model;
    private final int year;

    public YearController(CoursePlanningSystem model, int year) {
        this.model = model;
        this.year = year;

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

    /**
     * Method controlling valid transfer modes within GridPane
     * @param event Move over GridPane event
     */
    @FXML
    private void courseDragOverEvent(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }

    /**
     * Method taking care of dropping a course in corresponding box in years GridPane
     * @param event Release event in a GridPane
     */
    @FXML
    private void courseReleaseEvent(DragEvent event) {
        int studyPeriod = calculateStudyPeriod(event.getX());
        int slot = calculateSlot(event.getY());
        model.addCourse(new Course(), year, studyPeriod, slot);

        event.setDropCompleted(true);
        event.consume();
    }

    /**
     * Method calculating study period depending on user mouse input
     * @param x mouse x-coordinate
     * @return index representing the study period
     */
    private int calculateStudyPeriod(double x) {
        if (x < sp1sp2.getWidth() / 2) {
            return 0;
        } else {
            return 1;
        }
    }

    /**
     * Method calculating slot depending on user mouse input
     * @param y mouse y-coordinate
     * @return index representing the slot
     */
    private int calculateSlot(double y) {
        if (y < sp1sp2.getHeight() / 2) {
            return 0;
        } else {
            return 1;
        }
    }

}
