package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
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
import java.util.UUID;

public class DetailedController extends VBox {

    @FXML private VBox detailedViewRoot;
    @FXML private Label studyPeriod;
    @FXML private Label examinator;
    @FXML private Label examinationMeans;
    @FXML private Label language;
    @FXML private TextArea courseDescription;
    @FXML private VBox requiredCourses;
    @FXML private Hyperlink coursePM;
    @FXML private Label courseCodeNameStudyPoints;

    private CoursePlanningSystem model;
    private HostServices hostServices;

    public DetailedController(CoursePlanningSystem model, HostServices hostServices) {
        this.model = model;
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

        detailedViewRoot.setVgrow(this, Priority.ALWAYS);

        setDetailedInfo(model.getAllCoursesIDs().get(0));

    }

    public void setDetailedInfo(UUID uuid){
        clear();
        String header = model.getCourseCode(uuid) + model.getCourseName(uuid) + model.getStudyPoints(uuid);
        setHeader(header);

        setStudyPeriod(model.getStudyPeriod(uuid));

        setExaminator(model.getExaminator(uuid));

        setExaminationMeans(model.getExaminationMeans(uuid));

        setLanguage(model.getLanguage(uuid));

        setRequiredCourses(model.getRequiredCourses(uuid));

        setCoursePMLink(model.getCoursePMLink(uuid));

        setCourseDescription(model.getCourseDescription(uuid));
    }

    private void setHeader(String header){
        this.courseCodeNameStudyPoints.setText(header);
    }

    private void setStudyPeriod(String studyPeriod) {
        this.studyPeriod.setText(studyPeriod);
    }

    private void setExaminator(String examinator) {
        this.examinator.setText(examinator);
    }

    private void setExaminationMeans(String examinationMeans) {
        this.examinationMeans.setText(examinationMeans);
    }

    private void setLanguage(String language) {
        this.language.setText(language);
    }

    private void setRequiredCourses(List<UUID> courses) {

        for (UUID uuid : courses) {
            Label courseName = new Label(model.getCourseCode(uuid));
            requiredCourses.getChildren().add(courseName);
        }

    }

    private void setCoursePMLink(String coursePMLink){
        coursePM.setTooltip(new Tooltip(coursePMLink));
    }

    @FXML
    private void setOnMouseClickedCoursePMLink(){
        String s = coursePM.getTooltip().getText();
        hostServices.showDocument(s);
    }

    private void setCourseDescription(String courseDescription) {
        this.courseDescription.setText(courseDescription);
    }

    private void clear(){
        studyPeriod.setText("");
        examinator.setText("");
        examinationMeans.setText("");
        language.setText("");
        courseDescription.setText("");
        coursePM.setText("");
        courseCodeNameStudyPoints.setText("");
        requiredCourses.getChildren().clear();
    }

}
