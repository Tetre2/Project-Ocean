package ProjectOcean.IO;

import ProjectOcean.Model.Student;
import ProjectOcean.Model.StudyPlan;
import ProjectOcean.Model.Workspace;

import java.util.List;

public interface IStudyPlanSaverLoader {

    /**
     * Saves the students studyplans to a file
     * @param student
     */
    void saveModel(Student student);

    /**
     * tries to load all studyplans saved in the JSON file
     * @return a list of studyplans
     * @throws StudyPlanNotFoundException
     */
    List<StudyPlan> loadStudyplans() throws StudyPlanNotFoundException, OldStudyplanExeption;

    /**
     * tries to load the current studyplan saved in the JSON file
     * @return a studyplan
     * @throws StudyPlanNotFoundException
     */
    StudyPlan loadCurrentStudyPlan(List<StudyPlan> studyPlans) throws StudyPlanNotFoundException, OldStudyplanExeption;

    /**
     * tries to load a workspace saved in the JSON file
     * @return a workspace
     * @throws StudyPlanNotFoundException
     */
    Workspace loadWorkspace() throws StudyPlanNotFoundException, OldStudyplanExeption;

    /**
     * creates a new empty Student
     */
    void createNewStudentFile();

}
