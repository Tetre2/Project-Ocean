package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.UUID;

/**
 * Represents the visual component of a course
 */

public class CourseListIconController extends VBox implements Movable {

    @FXML private Text courseCodeText;
    @FXML private Text courseNameText;
    @FXML private Text studyPointsText;

    private static CoursePlanningSystem model;
    private UUID id;
    private ApplicationController applicationController;


    public CourseListIconController(UUID id, CoursePlanningSystem model, ApplicationController applicationController) {
        this.model = model;
        this.id = id;
        this.applicationController = applicationController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/CourseView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        populateIcon();
    }

    /**
     * Fills the texts withs strings
     */
    private void populateIcon() {
        String courseName = this.model.getCourseName(this.id);
        if (courseName.length() > 25) {
            courseName = courseName.substring(0, 27) + "...";
        }
        this.courseNameText.setText(courseName);
        this.courseCodeText.setText(this.model.getCourseCode(this.id));
        this.studyPointsText.setText(this.model.getStudyPoints(this.id) + " hp");
    }


    @FXML
    private void onMousedClicked(){
        applicationController.showDetailedInformation(id);
    }

    public void relocateToPoint(Point2D p) {


        Point2D localCoords = new Point2D(this.getParent().sceneToLocal(p).getX(), this.getParent().sceneToLocal(p).getY() );

        relocate(
                (int) (localCoords.getX() -
                        (getBoundsInLocal().getWidth() / 2)),
                (int) (localCoords.getY() -
                        (getBoundsInLocal().getHeight() / 2))
        );
    }

    public UUID getUUID(){
        return id;
    }

    @FXML
    public void dragDetected(MouseEvent event) {

        CourseListIconController icon = (CourseListIconController) event.getSource();

        ClipboardContent content = new ClipboardContent();
        content.putString(icon.toString());

        icon = new CourseListIconController(icon.getUUID(), model, applicationController);

        applicationController.addIconToScreen(icon);

        icon.startDragAndDrop(TransferMode.MOVE).setContent(content);
        icon.setVisible(true);
        icon.setMouseTransparent(true);
        event.consume();
    }
}
