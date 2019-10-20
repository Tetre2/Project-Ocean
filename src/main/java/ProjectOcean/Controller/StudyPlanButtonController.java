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
public class StudyPlanButtonController extends Button {

    @FXML private Button studyPlanButton;

    private final int nOfStudyPlans;
    private final StudyPlan studyPlan;
    private final CoursePlanningSystem model;
    private final ShowCurrentStudyPlan showStudyPlan;
    private final DeactivateStudyPlanButton deactivate;

    public StudyPlanButtonController(CoursePlanningSystem model, ShowCurrentStudyPlan showStudyPlan, DeactivateStudyPlanButton deactivate, int nOfStudyPlans, StudyPlan studyPlan) {
        this.nOfStudyPlans = nOfStudyPlans;
        this.studyPlan = studyPlan;
        this.model = model;
        this.showStudyPlan = showStudyPlan;
        this.deactivate = deactivate;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/StudyPlanButtonView.fxml"));
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
        studyPlanButton.setText("SPlan " + nOfStudyPlans);
    }

    @FXML
    private void onStudyPlanClicked(MouseEvent event) {
        if (!isThisStudyPlanCurrentStudyPlan()) {
            this.deactivate.deactivateStudyPlanButton();
            model.setCurrentStudyPlan(studyPlan);
            studyPlanButton.setDefaultButton(true);
            showStudyPlan.showCurrentStudyPlan();
        }
        event.consume();
    }

    /**
     * Method checks whether this buttons study plan is the applications current study plan (showing).
     * @return Boolean indicating if study plan is the current.
     */
    public boolean isThisStudyPlanCurrentStudyPlan() {
        return this.studyPlan == model.getCurrentStudyPlan();
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
    public void deactivateButton() {
        studyPlanButton.setDefaultButton(false);
    }

    /**
     * Set non-active button to active
     */
    public void activateButton() {
        studyPlanButton.setDefaultButton(true);
    }
}
