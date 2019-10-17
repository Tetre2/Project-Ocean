package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Represents the visual component of a course
 */

public class CourseListIconController extends VBox implements Movable {

    @FXML private Text courseCodeText;
    @FXML private Text courseNameText;
    @FXML private Text studyPointsText;
    @FXML private AnchorPane itIndicator;
    @FXML private AnchorPane mathIndicator;
    @FXML private AnchorPane natureIndicator;

    private static CoursePlanningSystem model;
    private final ICourse course;
    private final ShowDetailedInformationWindow showDetailedInformationWindow;
    private final AddIconToScreen addIconToScreen;

    public CourseListIconController(ICourse course, CoursePlanningSystem model, ShowDetailedInformationWindow showDetailedInformationWindow, AddIconToScreen addIconToScreen) {
        this.model = model;
        this.course = course;
        this.showDetailedInformationWindow = showDetailedInformationWindow;
        this.addIconToScreen = addIconToScreen;

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
        String courseName = course.getCourseName();
        if (courseName.length() > 25) {
            courseName = courseName.substring(0, 27) + "...";
        }
        this.courseNameText.setText(courseName);
        this.courseCodeText.setText(course.getCourseCode());
        this.studyPointsText.setText(course.getStudyPoints() + " hp");
        indicateCourseTypes();
    }

    private void indicateCourseTypes() {
        String courseTypes = course.getCourseTypes();
        if(courseTypes.contains("IT")) {
            this.itIndicator.setStyle("-fx-background-color: #40E0D0");
        }
        if(courseTypes.contains("MA")) {
            this.mathIndicator.setStyle("-fx-background-color: #DF3C3C");
        }
        if(courseTypes.contains("NA")) {
            this.natureIndicator.setStyle("-fx-background-color: #3DC134");
        }
    }

    @FXML
    private void onMousedClicked(){
        showDetailedInformationWindow.showDetailedInformationWindow(course);
    }

    @Override
    public ICourse getICourse() {
        return course;
    }

    /**
     * Relocates the CourseListIconController instance according to the point parameter
     * @param p the point representing the current mouse coordinates
     */
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
        CourseListIconController icon = (CourseListIconController) event.getSource();
        ClipboardContent content = new ClipboardContent();
        content.putString(icon.toString());


        //Check from which parent the object started in.
        switch (icon.getParent().getId()){
            case "workspaceContainer":
                model.removeCourseFromWorkspace(icon.getICourse());
                break;
            default:
        }

         //MUST come after the above statement
        icon = new CourseListIconController(icon.getICourse(), model, this.showDetailedInformationWindow, this.addIconToScreen);
        addIconToScreen.addIconToScreen(icon);

        icon.startDragAndDrop(TransferMode.MOVE).setContent(content);
        icon.setVisible(true);
        icon.setMouseTransparent(true);
        event.consume();
    }
}
