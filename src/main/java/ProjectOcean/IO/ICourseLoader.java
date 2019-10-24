package ProjectOcean.IO;

import ProjectOcean.IO.Exceptions.CoursesNotFoundException;
import ProjectOcean.IO.Exceptions.OldFileException;
import ProjectOcean.Model.Course;

import java.util.List;

public interface ICourseLoader {

    /**
     * tries to load all courses form a file if it cant load it it creates a new empty file
     * @return the loaded courses
     */
    List<Course> loadCoursesFile() throws CoursesNotFoundException, OldFileException;

}
