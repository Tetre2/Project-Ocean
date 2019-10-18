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

    private int nOfStudyPlans;
    private StudyPlan studyPlan;
    private CoursePlanningSystem model;
    private ApplicationController applicationController;
    private StudyPlansController studyPlansController;

    public ButtonController(CoursePlanningSystem model, ApplicationController applicationController, StudyPlansController studyPlansController, int nOfStudyPlans, StudyPlan studyPlan) {
        this.nOfStudyPlans = nOfStudyPlans;
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
        buttonStudyPlan.setText("SPlan " + nOfStudyPlans);
    }

    @FXML
    private void onStudyPlanClicked(MouseEvent event) {

        if (!isThisButtonsStudyPlanTheCurrentStudyPlan()) {
            studyPlansController.deactivateCurrStudyPlanButton();
            model.getStudent().setCurrentStudyPlan(studyPlan);
            buttonStudyPlan.setDefaultButton(true);
            applicationController.showAStudyPlan();
        }

        event.consume();

    }

    /**
     * Method checks whether this buttons study plan is the applications current study plan (showing).
     * @return Boolean indicating if study plan is the current.
     */
    public boolean isThisButtonsStudyPlanTheCurrentStudyPlan() {
        return this.studyPlan == model.getStudent().getCurrentStudyPlan();
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
