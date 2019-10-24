package ProjectOcean.Controller;

import ProjectOcean.Controller.FunctionalInterfaces.ToggleStudyPlanWindow;
import ProjectOcean.Model.CoursePlanningSystem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.*;

/**
 * Represents a graphical component of a study plan.
 */
class StudyPlanSelectorController extends AnchorPane implements Observer {

    @FXML private VBox studyPlanContainer;

    private final CoursePlanningSystem model;
    private final ToggleStudyPlanWindow toggleStudyPlanWindow;
    private final SortedMap<Integer, StudyPlanButtonController> mapStudyPlanAndController = new TreeMap<>();

    public StudyPlanSelectorController(CoursePlanningSystem model, ToggleStudyPlanWindow toggleStudyPlanWindow) {

        this.model = model;
        this.toggleStudyPlanWindow = toggleStudyPlanWindow;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "/fxml/StudyPlanSelectorWindow.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        model.addObserver(this);
        updateMapStudyPlanAndControllerAccordingToModel();
        displayAllStudyPlanButtons();

    }

    @FXML
    private void onAddStudyPlanClicked(MouseEvent event) {
        model.addStudyPlan();
        toggleStudyPlanWindow.toggleStudyPlanWindow();
        event.consume();
    }

    /**
     * Updates the view according to the model.
     */
    @Override
    public void update(Observable o, Object ob) {
        updateMapStudyPlanAndControllerAccordingToModel();
        displayAllStudyPlanButtons();
    }

    private void updateMapStudyPlanAndControllerAccordingToModel() {
        mapStudyPlanAndController.clear();
        List<Integer> ids = model.getStudyPlanIds();
        for (Integer id : ids) {
            StudyPlanButtonController newButton = new StudyPlanButtonController(model, toggleStudyPlanWindow, id);
            mapStudyPlanAndController.put(id, newButton);
        }
    }

    private void displayAllStudyPlanButtons() {
        addStudyPlansToWindow();
    }

    private void addStudyPlansToWindow() {
        studyPlanContainer.getChildren().clear();
        for (Map.Entry entry : mapStudyPlanAndController.entrySet()) {
            StudyPlanButtonController spButton = (StudyPlanButtonController) entry.getValue();
            int nOfStudyPlans = studyPlanContainer.getChildren().size();
            studyPlanContainer.getChildren().add(nOfStudyPlans, spButton);

            if (entry.getKey().equals(model.getCurrentStudyPlan().getId())) {
                spButton.activateButton();
            }
        }
    }

}
