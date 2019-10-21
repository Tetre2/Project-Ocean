package ProjectOcean.Controller;

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
    private final ShowCurrentStudyPlan showStudyPlan;
    private final SetCurrentStudyPlan setCurrentStudyPlan;
    private final IsThisStudyPlanCurrentStudyPlan isThisStudyPlanCurrentStudyPlan;
    private final DeactivateStudyPlanButton deactivateStudyPlanButton;

    public StudyPlanButtonController(ShowCurrentStudyPlan showStudyPlan, SetCurrentStudyPlan setCurrentStudyPlan,
                                     IsThisStudyPlanCurrentStudyPlan isThisStudyPlanCurrentStudyPlan,
                                     DeactivateStudyPlanButton deactivateStudyPlanButton, int nOfStudyPlans) {
        this.nOfStudyPlans = nOfStudyPlans;
        this.showStudyPlan = showStudyPlan;
        this.setCurrentStudyPlan = setCurrentStudyPlan;
        this.isThisStudyPlanCurrentStudyPlan = isThisStudyPlanCurrentStudyPlan;
        this.deactivateStudyPlanButton = deactivateStudyPlanButton;

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
        if (!isThisStudyPlanCurrentStudyPlan.isThisStudyPlanCurrentStudyPlan(this)) {
            this.deactivateStudyPlanButton.deactivateStudyPlanButton();
            setCurrentStudyPlan.setCurrentStudyPlan(this);
            studyPlanButton.setDefaultButton(true);
            showStudyPlan.showCurrentStudyPlan();
        }
        event.consume();
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
