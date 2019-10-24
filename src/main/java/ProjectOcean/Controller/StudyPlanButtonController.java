package ProjectOcean.Controller;

import ProjectOcean.Controller.FunctionalInterfaces.ToggleStudyPlanWindow;
import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

/**
 * Represents a graphical component of a study plan's button.
 */
class StudyPlanButtonController extends AnchorPane {

    @FXML private Button studyPlanButton;

    private final CoursePlanningSystem model;
    private final ToggleStudyPlanWindow toggleStudyPlanWindow;
    private final int studyPlanId;

    public StudyPlanButtonController(CoursePlanningSystem model, ToggleStudyPlanWindow toggleStudyPlanWindow, int studyPlanId) {
        this.model = model;
        this.toggleStudyPlanWindow = toggleStudyPlanWindow;
        this.studyPlanId = studyPlanId;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/StudyPlanButtonView.fxml"));
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
        studyPlanButton.setText("SPlan " + studyPlanId);
    }

    @FXML
    private void onStudyPlanClicked(MouseEvent event) {
        setCurrentStudyPlan();
        event.consume();
    }

    @FXML
    private void onDeleteStudyPlanClicked() {
        deleteStudyPlan();
        toggleStudyPlanWindow.toggleStudyPlanWindow();
    }

    /**
     * Removing current study plan
     */
    private void deleteStudyPlan() {
        if (!studyPlanExists()) {
            new Alert(Alert.AlertType.ERROR, "Warning: you have no study plan to delete!").showAndWait();
        } else {
            model.removeStudyPlan(studyPlanId);
        }
    }

    private boolean studyPlanExists() {
        return model.getAllStudyPlans().size() > 0;
    }

    private void setCurrentStudyPlan() {
        model.setCurrentStudyPlan(studyPlanId);
    }

    /**
     * Set non-active button to active
     */
    public void activateButton() {
        studyPlanButton.setDefaultButton(true);
    }
}
