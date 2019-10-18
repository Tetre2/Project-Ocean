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
public class StudyPlansController extends AnchorPane {

    @FXML private VBox studyPlanContainer;
    @FXML private Button addButton;

    private CoursePlanningSystem model;
    private ShowAStudyPlan showStudyPlan;

    public StudyPlansController(CoursePlanningSystem model, ShowAStudyPlan showStudyPlan) {

        this.model = model;
        this.showStudyPlan = showStudyPlan;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/StudyPlansWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        displayAllStudyPlans();

    }

    @FXML
    private void onAddStudyPlanClicked(MouseEvent event) {

        deactivateCurrStudyPlanButton();
        model.addStudyPlan(); // Method also setting new studyPlan to current.

        displayAllStudyPlans();
        showStudyPlan.showAStudyPlan();

        event.consume();

    }

    private void displayAllStudyPlans() {
        studyPlanContainer.getChildren().clear();
        studyPlanContainer.getChildren().add(addButton);

        for (StudyPlan studyPlan : model.getStudent().getAllStudyPlans()) {
            // Set new buttons properties, values taken from button created in ApplicationWindow.fxml
            int nOfStudyPlans = studyPlanContainer.getChildren().size();
            ButtonController newButton = new ButtonController(model, showStudyPlan, this::deactivateCurrStudyPlanButton, nOfStudyPlans, studyPlan);

            studyPlanContainer.getChildren().add(nOfStudyPlans - 1, newButton);

            if (newButton.getStudyPlan() == model.getStudent().getCurrentStudyPlan()) {
                newButton.activateDefaultButton();
            }
        }
    }

    private void deactivateCurrStudyPlanButton() {
        ButtonController buttonController = getCurrStudyPlansButtonController();
        if (buttonController != null) {
            buttonController.deActivateDefaultButton();
        }
    }

    // Premise: there is an active study plan button.
    private ButtonController getCurrStudyPlansButtonController() {
        ButtonController bc = null;
        for (int i = 0; i < model.getStudent().getAllStudyPlans().size(); i++) {
            ButtonController buttonController = (ButtonController) studyPlanContainer.getChildren().get(i);
            if (buttonController.getStudyPlan() == model.getStudent().getCurrentStudyPlan()) {
                bc = buttonController;
            }
        }
        return bc;
    }

    /**
     * Removing current study plan and show another one afterwards
     */
    public void deleteCurrentStudyPlan() {
        if (model.getStudent().getAllStudyPlans().size() == 0) {
            new Alert(Alert.AlertType.ERROR, "Warning: you have no study plan to delete!").showAndWait();
        } else {
            ButtonController buttonController = getCurrStudyPlansButtonController();
            model.getStudent().removeStudyPlan(buttonController.getStudyPlan());
            setCurrentStudyPlan();
            displayAllStudyPlans();
        }
    }

    private void setCurrentStudyPlan() {
        if (model.getStudent().getAllStudyPlans().size() > 0) {
            StudyPlan sp = model.getStudent().getAllStudyPlans().get(0);
            model.getStudent().setCurrentStudyPlan(sp);
        }
    }

}
