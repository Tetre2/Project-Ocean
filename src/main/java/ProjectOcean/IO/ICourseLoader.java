package ProjectOcean.IO;

import ProjectOcean.IO.Exceptions.CoursesNotFoundException;
import ProjectOcean.IO.Exceptions.OldFileException;
import ProjectOcean.Model.ICourse;

import java.util.List;

public interface ICourseLoader {

    /**
     * tries to load all courses form a file if it cant load it it creates a new empty file
     * @returns the loaded courses
     */
    List<ICourse> loadCoursesFile() throws CoursesNotFoundException, OldFileException;

    void createCoursesFile();



}
