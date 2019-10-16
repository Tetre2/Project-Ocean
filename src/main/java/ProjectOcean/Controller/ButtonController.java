package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.StudyPlan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

/**
 * Represents a graphical component of a study plan's button.
 */
public class ButtonController extends Button {

    @FXML private Button buttonStudyPlan;

    private int studyPlanNumber;
    private StudyPlan studyPlan;
    private CoursePlanningSystem model;
    private ApplicationController applicationController;

    public ButtonController(CoursePlanningSystem model, ApplicationController applicationController, int studyPlanNumber, StudyPlan studyPlan) {
        this.studyPlanNumber = studyPlanNumber;
        this.studyPlan = studyPlan;
        this.model = model;
        this.applicationController = applicationController;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/ButtonView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        initiateButtonName();

    }

    private void initiateButtonName() {
        buttonStudyPlan.setText("SPlan " + studyPlanNumber);
    }

    @FXML
    private void onStudyPlanClicked(MouseEvent event) {

        if (!(this.studyPlan == model.getStudent().getCurrentStudyPlan())) {
            model.getStudent().setCurrentStudyPlan(studyPlan);
            buttonStudyPlan.setDefaultButton(true);
            applicationController.removeCurrentScheduleView(); // removes current schedule;

            StudyPlanController studyPlanController = new StudyPlanController(model, applicationController);
            applicationController.addNewStudyPlanController(studyPlanController);
        }
        event.consume();

    }
}
