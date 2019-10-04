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
 * Represents the visual graphic component of a year.
 */
public class YearController extends VBox {

    @FXML private GridPane sp1sp2;
    @FXML private GridPane sp3sp4;
    @FXML private Label yearLabel;

    private final CoursePlanningSystem model;
    private final ApplicationController applicationController;
    private final int year;
    private ScheduleCourseController course1;
    //TODO: create 8 courses as instance of ScheduleCourseController and put them in the grid

    public YearController(int year, CoursePlanningSystem model, ApplicationController applicationController) {
        this.model = model;
        this.year = year;
        this.applicationController = applicationController;
        course1 = new ScheduleCourseController();


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/YearView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        sp1sp2.add(course1, 0, 0);
    }

    /**
     * Method controlling valid transfer modes within GridPane
     * @param event Move over GridPane event
     */
    @FXML
    private void onDragOver(DragEvent event) {
        event.acceptTransferModes(TransferMode.MOVE);
        Movable icon = (Movable) event.getGestureSource();

        applicationController.moveIconToCursor(icon, event);
        event.consume();
    }

    /**
     * Method taking care of dropping a course in corresponding box in Years GridPane
     * @param event Release event in a GridPane
     */
    @FXML
    private void onDragRelease(DragEvent event) {
        int studyPeriod = calculateStudyPeriod(event.getX());
        int slot = calculateSlot(event.getY());

        event.acceptTransferModes(TransferMode.MOVE);
        Movable icon = (Movable) event.getGestureSource();

        sp1sp2.add(new Label(model.getCourse(icon.getUUID()).getCourseCode()), studyPeriod, slot);
        model.addCourse(icon.getUUID(), year, calculateStudyPeriod(event.getSceneX()), calculateSlot(event.getSceneY()));

        event.setDropCompleted(true);
        applicationController.moveIconToCursor(icon, event);
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
