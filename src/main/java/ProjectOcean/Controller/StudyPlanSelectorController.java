package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a graphical component of a study plan.
 */
public class StudyPlanSelectorController extends AnchorPane {

    @FXML private VBox studyPlanContainer;
    @FXML private Button addButton;

    private final CoursePlanningSystem model;
    private final ShowCurrentStudyPlan showStudyPlan;
    private Map<StudyPlanButtonController, Integer> mapStudyPlanAndController = new HashMap<>();

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
        if (model.getAllStudyPlans().size() != 0) {
            deactivateStudyPlanButton();
        }
        model.addStudyPlan(); // Method also setting new studyPlan to current.
        displayAllStudyPlanButtons();
        showStudyPlan.showCurrentStudyPlan();
        event.consume();
    }

    private void displayAllStudyPlanButtons() {
        studyPlanContainer.getChildren().clear();
        studyPlanContainer.getChildren().add(addButton);
        List<Integer> ids = model.getStudyPlanIds(); // Ask model for id:s of all study plans

        for (Integer id : ids) {
            int nOfStudyPlans = studyPlanContainer.getChildren().size();
            StudyPlanButtonController newButton = new StudyPlanButtonController(showStudyPlan, this::setCurrentStudyPlan,
                    this::isThisStudyPlanCurrentStudyPlan, this::deactivateStudyPlanButton, nOfStudyPlans);
            studyPlanContainer.getChildren().add(nOfStudyPlans - 1, newButton);
            mapStudyPlanAndController.put(newButton, id);

            if (mapStudyPlanAndController.get(newButton).equals(model.getCurrentStudyPlan().getId())) {
                newButton.activateButton();
            }
        }
    }

    private void deactivateStudyPlanButton() {
        StudyPlanButtonController spbController = getCurrStudyPlansButtonController();
        spbController.deactivateButton();
    }

    private StudyPlanButtonController getCurrStudyPlansButtonController() {
        for (Node nodeButton : studyPlanContainer.getChildren()) {
            StudyPlanButtonController spButton = (StudyPlanButtonController) nodeButton;
            if (mapStudyPlanAndController.get(spButton).equals(model.getCurrentStudyPlan().getId())) {
                return spButton;
            }
        }
        return null;
    }

    /**
     * Removing current study plan
     */
    public void deleteCurrentStudyPlan() {
        if (!studyPlanExists()) {
            new Alert(Alert.AlertType.ERROR, "Warning: you have no study plan to delete!").showAndWait();
        } else {
            StudyPlanButtonController spbController = getCurrStudyPlansButtonController();
            model.removeStudyPlan(mapStudyPlanAndController.get(spbController));
        }
    }

    private boolean studyPlanExists() {
        return model.getAllStudyPlans().size() > 0;
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
            model.setFirstStudyPlanAsCurrent();
        }
    }

    private void setCurrentStudyPlan(StudyPlanButtonController buttonController) {
        model.setCurrentStudyPlan(mapStudyPlanAndController.get(buttonController));
    }

    private boolean isThisStudyPlanCurrentStudyPlan(StudyPlanButtonController buttonController) {
        return model.getCurrentStudyPlan().getId() == mapStudyPlanAndController.get(buttonController);
    }

}
