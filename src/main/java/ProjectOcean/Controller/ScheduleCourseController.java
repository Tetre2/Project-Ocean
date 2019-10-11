package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.UUID;

/**
 * Represents the visual graphic component of a course in study plan.
 */
public class ScheduleCourseController extends VBox implements Movable{

    @FXML private Label courseCodeLabel;
    private final CoursePlanningSystem model;

    private final ApplicationController applicationController;
    private int year;
    private int studyPeriod;
    private int slot;
    private final UUID id;

    public ScheduleCourseController(CoursePlanningSystem model, UUID id, ApplicationController applicationController, int year, int studyPeriod, int slot) {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ScheduleCourseView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.model = model;
        this.slot = slot;
        this.applicationController = applicationController;
        this.id = id;
        this.year = year;
        this. studyPeriod = studyPeriod;
        this.courseCodeLabel.setText(model.getCourse(id).getCourseCode());
    }

    /**
     * @return the UUID of the Movable instance
     */
    @Override
    public UUID getUUID() {
        return id;
    }

    /**
     * Relocates the CourseListIconController instance according to the point parameter
     * @param p the point representing the current mouse coordinates
     */
    @Override
    public void relocateToPoint(Point2D p) {
        Point2D localCoords = new Point2D(this.getParent().sceneToLocal(p).getX(), this.getParent().sceneToLocal(p).getY() );

        relocate(
                (int) (localCoords.getX() -
                        (getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() -
                        (getBoundsInLocal().getHeight() / 2))
        );
    }

    @FXML
    private void dragDetected(MouseEvent event) {
        //Put a copy of the object that was dragged in the Clipboard to enable drag and drop.
        ScheduleCourseController icon = (ScheduleCourseController) event.getSource();
        ClipboardContent content = new ClipboardContent();
        content.putString(icon.toString());

        
        model.removeCourse(year, studyPeriod, slot);

        //MUST come after the above statement
        icon = new ScheduleCourseController(model ,icon.getUUID(), applicationController, year, studyPeriod, slot);
        applicationController.addIconToScreen(icon);

        icon.startDragAndDrop(TransferMode.MOVE).setContent(content);
        icon.setVisible(true);
        icon.setMouseTransparent(true);
        event.consume();
    }

}