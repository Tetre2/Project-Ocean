package ProjectOcean.Controller;

import ProjectOcean.Model.ICourse;
import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Controller for the detailed view of a course
 */
public class DetailedController extends VBox {

    @FXML private VBox detailedViewRoot;
    @FXML private Label studyPeriod;
    @FXML private Label examiner;
    @FXML private Label examinationMeans;
    @FXML private Label language;
    @FXML private TextArea courseDescription;
    @FXML private VBox requiredCourses;
    @FXML private Hyperlink coursePM;
    @FXML private Label courseCodeNameStudyPoints;
    @FXML private Label courseTypeLabel;

    private HostServices hostServices;
    private GoBackToMainContent goBack;

    /**
     * Creates the view for the detailed view without any info in it.
     * @param goBack is a callback to the showStudyPlanWorkspaceWindow method in applicationController.
     */
    public DetailedController(GoBackToMainContent goBack, HostServices hostServices) {
        this.goBack = goBack;
        this.hostServices = hostServices;

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("Internationalization/Lang_sv");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/DetailedWindow.fxml"), bundle);
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //sets the detailed view so it can grow vertically in its parents
        detailedViewRoot.setVgrow(this, Priority.ALWAYS);

    }

    /**
     * Sets all info a course has and show it in the detailed window
     * @param course is the unique id for one specific course
     */
    public void setDetailedInfo(ICourse course){
        clear();
        String header = course.getCourseCode() + " - " + course.getCourseName() + " - " + course.getStudyPoints();
        setHeader(header);

        setStudyPeriod(course.getStudyPeriod());

        setExaminer(course.getExaminer());

        setExaminationMeans(course.getExaminationMeans());

        setLanguage(course.getLanguage());

        setRequiredCourses(course.getRequiredCourses());

        setCoursePMLink(course.getCoursePMLink());

        setCourseDescription(course.getCourseDescription());

        setCourseType(course.getCourseTypes());
    }

    private void setHeader(String header){
        this.courseCodeNameStudyPoints.setText(header);
    }

    private void setStudyPeriod(String studyPeriod) {
        this.studyPeriod.setText(studyPeriod);
    }

    private void setExaminer(String examiner) {
        this.examiner.setText(examiner);
    }

    private void setExaminationMeans(String examinationMeans) {
        this.examinationMeans.setText(examinationMeans);
    }

    private void setLanguage(String language) {
        this.language.setText(language);
    }

    private void setCourseType(List<String> courseTypes) {
        if(courseTypes.isEmpty()) {
            courseTypeLabel.setText("Allmän");
        } else {
            String courseTypesString = courseTypes.toString();
            //Trims the brackets from List.toString
            courseTypesString = courseTypesString.substring(1, courseTypesString.length()-1);
            this.courseTypeLabel.setText(courseTypesString);
        }

    }

    private void setRequiredCourses(List<String> courses) {
        //Creates a new label for each required course and adds them to the VBox
        for (String course : courses) {
            Label courseName = new Label(course);
            requiredCourses.getChildren().add(courseName);
        }

    }

    private void setCoursePMLink(String coursePMLink){
        coursePM.setTooltip(new Tooltip(coursePMLink));
    }

    private void setCourseDescription(String courseDescription) {
        this.courseDescription.setText(courseDescription);
    }

    /**
     * clears all info
     */
    private void clear(){
        studyPeriod.setText("");
        examiner.setText("");
        examinationMeans.setText("");
        language.setText("");
        courseDescription.setText("");
        coursePM.setTooltip(new Tooltip(""));
        coursePM.setVisited(false);
        courseCodeNameStudyPoints.setText("");
        requiredCourses.getChildren().clear();
    }

    @FXML
    /**
     * Opens the course-PM in a web browser
     */
    private void setOnMouseClickedCoursePMLink(){
        String s = coursePM.getTooltip().getText();
        hostServices.showDocument(s);
    }

    @FXML
    private void setOnBackClicked(){
        goBack.goBack();
    }

}
