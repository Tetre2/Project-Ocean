package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.StudyPlan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    @FXML private Button studyPlanView;
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

        int studyPlanNumber = studyPlanContainer.getChildren().size();
        model.addStudyPlan(); // method setting new studyPlan to current
        StudyPlan currentStudyPlan = model.getStudent().getCurrentStudyPlan();
        ButtonController newButton = new ButtonController(model, applicationController, studyPlanNumber, currentStudyPlan);

        studyPlanContainer.getChildren().add(studyPlanContainer.getChildren().size() - 1, newButton);

        // Show the new StudyPlan in ScheduleView
        applicationController.removeCurrentScheduleView();
        StudyPlanController studyPlanController = new StudyPlanController(model, applicationController);
        applicationController.addNewStudyPlanController(studyPlanController);

        event.consume();

    }

    private void displayAllStudyPlans() {
        studyPlanContainer.getChildren().clear();
        studyPlanContainer.getChildren().add(addButton);

        for (StudyPlan studyPlan : model.getStudent().getAllStudyPlans()) {
            // Set new buttons properties, values taken from button created in ApplicationWindow.fxml
            int studyPlanNumber = studyPlanContainer.getChildren().size();
            ButtonController newButton = new ButtonController(model, applicationController, studyPlanNumber, studyPlan);

            studyPlanContainer.getChildren().add(studyPlanContainer.getChildren().size() - 1, newButton);
        }
    }

}
