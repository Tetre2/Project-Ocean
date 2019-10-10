package ProjectOcean.IO;

import ProjectOcean.Model.Student;

public interface IStudyPlanSaverLoader {

    /**
     * Saves the students studyplans to a file
     * @param student
     */
    void saveStudyplans(Student student);

    /**
     * tries to load a student form a file if it cant load it it creates a new empty file
     * @returns the loaded student
     */
    Student tryToLoadStudentFileIfNotCreateNewFile();


}
