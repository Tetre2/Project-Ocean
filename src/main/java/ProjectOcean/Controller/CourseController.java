package ProjectOcean.Controller;

import ProjectOcean.Controller.FunctionalInterfaces.AddIconToScreen;
import ProjectOcean.Controller.FunctionalInterfaces.ShowDetailedInformationWindow;
import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.ICourse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.List;

/**
 * Represents the visual component of a course
 */

public class CourseController extends VBox implements Movable {


    @FXML private Label courseCodeText;
    @FXML private Label courseNameText;
    @FXML private Label studyPointsText;
    @FXML private VBox typeIndicator;

    private ClipboardContent content;
    private static CoursePlanningSystem model;
    private final ICourse course;
    private final ShowDetailedInformationWindow showDetailedInformationWindow;
    private final AddIconToScreen addIconToScreen;
    private final VisualFeedback visualFeedback;
    private final RemoveYear removeYear;

    public CourseController(ICourse course, CoursePlanningSystem model, VisualFeedback visualFeedback, ShowDetailedInformationWindow showDetailedInformationWindow, AddIconToScreen addIconToScreen, RemoveYear removeYear) {
        CourseController.model = model;
        this.course = course;
        this.showDetailedInformationWindow = showDetailedInformationWindow;
        this.addIconToScreen = addIconToScreen;
        this.visualFeedback = visualFeedback;
        this.removeYear = removeYear;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/CourseView.fxml"));
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
        this.studyPointsText.setText(course.getStudyPoints());
        indicateCourseTypes();
    }

    private void indicateCourseTypes() {
        List<String> courseTypes = course.getCourseTypes();
        for(String s : courseTypes) {
            switch(s) {
                case "Informationsteknik" :
                    paintIndicator("#40E0D0");
                    break;

                case "Matematik" :
                    paintIndicator("#DF3C3C");
                    break;

                case "Naturvetenskap" :
                    paintIndicator("#3DC134");
                    break;
            }
        }
    }

    private void paintIndicator(String colour) {
        Pane typeIndicatorPane = new Pane();
        typeIndicatorPane.setStyle("-fx-background-color: " + colour);
        typeIndicatorPane.setPrefHeight(50);
        typeIndicator.getChildren().add(typeIndicatorPane);
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
     * Relocates the CourseController instance according to the point parameter
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

        Node owner = this.getParent();
        copyDraggedObjectToClipBoard(this);

        //Check from which parent the object started in and delete from the model.
        switch (owner.getId()){
            case "workspaceContainer":
                model.removeCourseFromWorkspace(course);
                break;
            case "yearGrid":
                removeYear.removeYear(course);
            default:
        }

         //MUST come after the above statement
        visualFeedback.showAvailablePlacementInSchedule(course);
        CourseController draggedObject = new CourseController(course, model,this.visualFeedback, this.showDetailedInformationWindow, this.addIconToScreen, removeYear);
        addIconToScreen.addIconToScreen(draggedObject);

        draggedObject.startDragAndDrop(TransferMode.MOVE).setContent(content);
        draggedObject.setVisible(true);
        draggedObject.setMouseTransparent(true);
        event.consume();
    }

    private void copyDraggedObjectToClipBoard(CourseController icon){
        content = new ClipboardContent();
        content.putString(icon.toString());
    }
}
