package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.StudyPlan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Represents a graphical component of a study plan.
 */
public class StudyPlanSelectorController extends AnchorPane {

    @FXML private VBox studyPlanContainer;
    @FXML private Button addButton;

    private CoursePlanningSystem model;
    private ShowCurrentStudyPlan showStudyPlan;

    public StudyPlanSelectorController(CoursePlanningSystem model, ShowCurrentStudyPlan showStudyPlan) {

        this.model = model;
        this.showStudyPlan = showStudyPlan;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/StudyPlanSelectorWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        displayAllStudyPlanButtons();

    }

    @FXML
    private void onAddStudyPlanClicked(MouseEvent event) {
        deactivateStudyPlanButton();
        model.addStudyPlan(); // Method also setting new studyPlan to current.
        displayAllStudyPlanButtons();
        showStudyPlan.showCurrentStudyPlan();
        event.consume();
    }

    private void displayAllStudyPlanButtons() {
        studyPlanContainer.getChildren().clear();
        studyPlanContainer.getChildren().add(addButton);

        for (StudyPlan studyPlan : model.getStudent().getAllStudyPlans()) {
            int nOfStudyPlans = studyPlanContainer.getChildren().size();
            StudyPlanButtonController newButton = new StudyPlanButtonController(model, showStudyPlan, this::deactivateStudyPlanButton, nOfStudyPlans, studyPlan);

            studyPlanContainer.getChildren().add(nOfStudyPlans - 1, newButton);

            if (newButton.getStudyPlan() == model.getStudent().getCurrentStudyPlan()) {
                newButton.activateButton();
            }
        }
    }

    private void deactivateStudyPlanButton() {
        StudyPlanButtonController spbController = getCurrStudyPlansButtonController();
        if (spbController != null) {
            spbController.deactivateButton();
        }
    }

    // Premise: there is an active study plan button.
    private StudyPlanButtonController getCurrStudyPlansButtonController() {
        StudyPlanButtonController spc = null;
        for (int i = 0; i < model.getStudent().getAllStudyPlans().size(); i++) {
            StudyPlanButtonController spbController = (StudyPlanButtonController) studyPlanContainer.getChildren().get(i);
            if (spbController.getStudyPlan() == model.getStudent().getCurrentStudyPlan()) {
                spc = spbController;
            }
        }
        return spc;
    }

    /**
     * Removing current study plan
     */
    public void deleteCurrentStudyPlan() {
        if (!studyPlanExists()) {
            new Alert(Alert.AlertType.ERROR, "Warning: you have no study plan to delete!").showAndWait();
        } else {
            StudyPlanButtonController spbController = getCurrStudyPlansButtonController();
            model.getStudent().removeStudyPlan(spbController.getStudyPlan());
        }
    }

    private boolean studyPlanExists() {
        return model.getStudent().getAllStudyPlans().size() > 0;
    }

    /**
     *  Show all study plan buttons
     */
    public void showAllStudyPlanButtons() {
        setFirstStudyPlanAsCurrent();
        displayAllStudyPlanButtons();
    }

    private void setFirstStudyPlanAsCurrent() {
        if (studyPlanExists()) {
            StudyPlan sp = model.getStudent().getAllStudyPlans().get(0);
            model.getStudent().setCurrentStudyPlan(sp);
        }
    }

}
