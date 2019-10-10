package ProjectOcean.IO;

import ProjectOcean.Model.Student;

public interface IStudyPlanSaverLoader {

    void saveStudyplans(Student student);

    Student tryToLoadStudentFileIfNotCreateNewFile();


}
