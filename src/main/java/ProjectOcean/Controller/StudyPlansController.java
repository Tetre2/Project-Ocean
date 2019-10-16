package ProjectOcean.Controller;

import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.StudyPlan;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Represents a graphical component of a study plan.
 */
public class StudyPlansController extends VBox {

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

        // Set new buttons properties, values taken from button created in ApplicationWindow.fxml
        Button newButton = new Button();
        newButton.setText("SPlan " + studyPlanContainer.getChildren().size());
        newButton.setTextAlignment(studyPlanView.getTextAlignment());
        newButton.setPrefSize(studyPlanView.getPrefWidth(), studyPlanView.getPrefHeight());
        newButton.setNodeOrientation(NodeOrientation.INHERIT);
        studyPlanContainer.getChildren().add(studyPlanContainer.getChildren().size() - 1, newButton);
        studyPlanContainer.setMargin(newButton, new Insets(30, 30, 0, 30)); //TODO: this is a property for the VBox

        //newButton.setDefaultButton(true);

        model.addStudyPlan();
        //showChosenStudyPlan(model.getStudent().getCurrentStudyPlan());
        event.consume();
    }

    private void displayAllStudyPlans() {
        studyPlanContainer.getChildren().clear();
        studyPlanContainer.getChildren().add(addButton);
        int studyPlans = model.getStudent().getAllStudyPlans().size();
        System.out.println(studyPlans);
        for (StudyPlan studyPlan : model.getStudent().getAllStudyPlans()) {
            //model.getStudent().setCurrentStudyPlan(studyPlan);
            // Set new buttons properties, values taken from button created in ApplicationWindow.fxml
            Button newButton = new Button();
            newButton.setText("SPlan " + studyPlanContainer.getChildren().size());

            newButton.setPrefSize(studyPlanView.getPrefWidth(), studyPlanView.getPrefHeight());
            //newButton.setAlignment(studyPlanView.getAlignment());
            //newButton.setNodeOrientation(NodeOrientation.INHERIT);
            newButton.setTextAlignment(studyPlanView.getTextAlignment());
            studyPlanContainer.setMargin(newButton, new Insets(30, 30, 0, 30)); //TODO: this is a property for the VBox

            studyPlanContainer.getChildren().add(studyPlanContainer.getChildren().size() - 1, newButton);

            //model.addStudyPlan();
            //CourseListIconController iconController = new CourseListIconController(course, model, applicationController);
            //workspaceContainer.getChildren().add(iconController);
        }
        for (int i = 0; i < studyPlans; i++) {



        }
    }

}
