package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.StudyPlan;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.function.Predicate;

/**
 * Represents a graphical component of a study plan.
 */
public class StudyPlansController extends AnchorPane {

    @FXML private VBox studyPlanContainer;
    @FXML private Button addButton;

    private CoursePlanningSystem model;
    private ApplicationController applicationController;

    public StudyPlansController(CoursePlanningSystem model, ApplicationController applicationController) {

        this.model = model;
        this.applicationController = applicationController;

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

        model.addStudyPlan(); // method setting new studyPlan to current

        // Create a study plans button TOD: simplify this
        int nOfStudyPlans = studyPlanContainer.getChildren().size();
        StudyPlan currentStudyPlan = model.getStudent().getCurrentStudyPlan();
        ButtonController newButton = new ButtonController(model, applicationController, this, nOfStudyPlans, currentStudyPlan);

        // Add button last in GUI list
        studyPlanContainer.getChildren().add(nOfStudyPlans - 1, newButton);

        // Show the new StudyPlan in ScheduleView
        applicationController.showAStudyPlan();

        newButton.setDefaultButton(true);

        event.consume();

    }

    private void displayAllStudyPlans() {
        studyPlanContainer.getChildren().clear();
        studyPlanContainer.getChildren().add(addButton);

        for (StudyPlan studyPlan : model.getStudent().getAllStudyPlans()) {
            // Set new buttons properties, values taken from button created in ApplicationWindow.fxml
            int nOfStudyPlans = studyPlanContainer.getChildren().size();
            ButtonController newButton = new ButtonController(model, applicationController, this, nOfStudyPlans, studyPlan);

            studyPlanContainer.getChildren().add(nOfStudyPlans - 1, newButton);
        }

        // Loop activate button which is set as current in model
        for (int i = 0; i < model.getStudent().getAllStudyPlans().size(); i++) {
            ButtonController buttonController = (ButtonController) studyPlanContainer.getChildren().get(i);
            if (buttonController.getStudyPlan() == model.getStudent().getCurrentStudyPlan()) {
                buttonController.activateDefaultButton();
            }
        }
    }

    //TOD: give ButtonController this function and make it private then
    public void deactivateCurrStudyPlanButton() {
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

    /**
     * @return An Observable list containing all nodes in the VBox "studyPlanContainer"
     */
    public ObservableList<Node> getStudyPlans() {
        return studyPlanContainer.getChildren();
    }

}
