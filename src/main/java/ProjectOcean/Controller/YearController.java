package ProjectOcean.Controller;

import ProjectOcean.Model.Course;
import ProjectOcean.Model.CoursePlanningSystem;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Constructor loads a graphical representation of a year as a fxml-file.
 */
public class YearController extends VBox {

    @FXML private GridPane sp1sp2;
    @FXML private GridPane sp3sp4;
    @FXML private Label yearLabel;

    private CoursePlanningSystem model;
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

    @FXML
    private void handleDragOver(DragEvent event) {

    }

    /**
     * Method taking care of dropping a course in corresponding box in years GridPane
     * @param event Release event in a GridPane
     * @throws FileNotFoundException
     */
    @FXML
    private void handleDrop(DragEvent event) throws FileNotFoundException {
        sp1sp2.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent arg0) {
                if(arg0.getEventType() == MouseEvent.MOUSE_MOVED){
                    int studyPeriod = calculateStudyPeriod(arg0.getX());
                    int slot = calculateSlot(arg0.getY());
                    model.addCourse(new Course(), year, studyPeriod, slot);
                }
            }
        });

        event.consume();
    }

    /**
     * Method calculating study period depending on user mouse input
     * @param x mouse x-coordinate
     * @return index representing the study period
     */
    private int calculateStudyPeriod(double x) {
        if (x < sp1sp2.getWidth() / 2)
            return 0;
        return 1;
    }

    /**
     * Method calculating slot depending on user mouse input
     * @param y mouse y-coordinate
     * @return index representing the slot
     */
    private int calculateSlot(double y) {
        if (y < sp1sp2.getHeight() / 2)
            return 0;
        return 1;
    }

}
