package ProjectOcean.IO;

import ProjectOcean.IO.Exceptions.OldFileException;
import ProjectOcean.IO.Exceptions.StudyPlanNotFoundException;
import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.StudyPlan;
import ProjectOcean.Model.Workspace;

import java.util.List;

public interface IStudyPlanSaverLoader {

    /**
     * Saves the students studyPlans to a file
     * @param model the model
     */
    void saveModel(CoursePlanningSystem model);

    /**
     * tries to load all studyPlans saved in the JSON file
     * @return a list of studyPlans
     * @throws StudyPlanNotFoundException if the program cant find the studyplan.json file
     */
    List<StudyPlan> loadStudyPlans() throws StudyPlanNotFoundException, OldFileException;

    /**
     * tries to load the current studyPlan saved in the JSON file
     * @return a studyPlan
     * @throws StudyPlanNotFoundException if the program cant find the studyplan.json file
     */
    StudyPlan loadCurrentStudyPlan(List<StudyPlan> studyPlans) throws StudyPlanNotFoundException, OldFileException;

    /**
     * tries to load a workspace saved in the JSON file
     * @return a workspace
     * @throws StudyPlanNotFoundException if the program cant find the studyplan.json file
     */
    Workspace loadWorkspace() throws StudyPlanNotFoundException, OldFileException;

    /**
     * creates a new empty Student
     */
    void createNewStudentFile();

}
