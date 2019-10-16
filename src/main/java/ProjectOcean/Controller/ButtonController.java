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
    private StudyPlansController studyPlansController;

    public ButtonController(CoursePlanningSystem model, ApplicationController applicationController, StudyPlansController studyPlansController, int studyPlanNumber, StudyPlan studyPlan) {
        this.studyPlanNumber = studyPlanNumber;
        this.studyPlan = studyPlan;
        this.model = model;
        this.applicationController = applicationController;
        this.studyPlansController = studyPlansController;

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
            // Important to exclude last button: the "addButton" is not a ButtonController. Would throw cast error.
            for (int i = 0; i < model.getStudent().getAllStudyPlans().size(); i++) {
                ButtonController buttonController = (ButtonController) studyPlansController.getStudyPlans().get(i);
                if (buttonController.studyPlan == model.getStudent().getCurrentStudyPlan()) {
                    buttonController.buttonStudyPlan.setDefaultButton(false);
                }
            }
            model.getStudent().setCurrentStudyPlan(studyPlan);
            buttonStudyPlan.setDefaultButton(true);
            applicationController.removeCurrentScheduleView(); // removes current schedule;

            StudyPlanController studyPlanController = new StudyPlanController(model, applicationController);
            applicationController.addNewStudyPlanController(studyPlanController);
        }
        event.consume();

    }

    /**
     * @return get a study plan connected to given (this) button
     */
    public StudyPlan getStudyPlan() {
        return studyPlan;
    }

    /**
     * Set active button to non-active
     */
    public void deActivateDefaultButton() {
        buttonStudyPlan.setDefaultButton(false);
    }

    /**
     * Set non-active button to active
     */
    public void activateDefaultButton() {
        buttonStudyPlan.setDefaultButton(true);
    }
}
