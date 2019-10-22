package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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

    private Node owner;
    private ClipboardContent content;
    private static CoursePlanningSystem model;
    private final ICourse course;
    private final ShowDetailedInformationWindow showDetailedInformationWindow;
    private final AddIconToScreen addIconToScreen;
    private final VisualFeedback visualFeedback;

    public CourseListIconController(ICourse course, CoursePlanningSystem model, VisualFeedback visualFeedback, ShowDetailedInformationWindow showDetailedInformationWindow, AddIconToScreen addIconToScreen) {
        this.model = model;
        this.course = course;
        this.showDetailedInformationWindow = showDetailedInformationWindow;
        this.addIconToScreen = addIconToScreen;
        this.visualFeedback = visualFeedback;

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


      //  CourseListIconController draggedObject = getICourse();
        owner = this.getParent();
        copyDraggedObjectToClipBoard(this);



        //Check from which parent the object started in and delete from the model.
        switch (owner.getId()){
            case "workspaceContainer":
                model.removeCourseFromWorkspace(course);
                break;
            default:
        }



         //MUST come after the above statement
        visualFeedback.showAvailablePlacementInSchedule(course);
        CourseListIconController draggedObject = new CourseListIconController(course, model,this.visualFeedback, this.showDetailedInformationWindow, this.addIconToScreen);
        addIconToScreen.addIconToScreen(draggedObject);


        draggedObject.startDragAndDrop(TransferMode.MOVE).setContent(content);
        draggedObject.setVisible(true);
        draggedObject.setMouseTransparent(true);
        event.consume();
    }

    private void copyDraggedObjectToClipBoard(CourseListIconController icon){

        content = new ClipboardContent();
        content.putString(icon.toString());
    }
}
