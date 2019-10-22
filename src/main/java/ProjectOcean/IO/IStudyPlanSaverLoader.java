package ProjectOcean.IO;

import ProjectOcean.IO.Exceptions.OldFileException;
import ProjectOcean.IO.Exceptions.StudyPlanNotFoundException;
import ProjectOcean.Model.CoursePlanningSystem;
import ProjectOcean.Model.StudyPlan;
import ProjectOcean.Model.Workspace;

import java.util.List;

public interface IStudyPlanSaverLoader {

    /**
     * Saves the students studyplans to a file
     * @param model the model
     */
    void saveModel(CoursePlanningSystem model);

    /**
     * tries to load all studyplans saved in the JSON file
     * @return a list of studyplans
     * @throws StudyPlanNotFoundException
     */
    List<StudyPlan> loadStudyplans() throws StudyPlanNotFoundException, OldFileException;

    /**
     * tries to load the current studyplan saved in the JSON file
     * @return a studyplan
     * @throws StudyPlanNotFoundException
     */
    StudyPlan loadCurrentStudyPlan(List<StudyPlan> studyPlans) throws StudyPlanNotFoundException, OldFileException;

    /**
     * tries to load a workspace saved in the JSON file
     * @return a workspace
     * @throws StudyPlanNotFoundException
     */
    Workspace loadWorkspace() throws StudyPlanNotFoundException, OldFileException;

    /**
     * creates a new empty Student
     */
    void createNewStudentFile();

}
