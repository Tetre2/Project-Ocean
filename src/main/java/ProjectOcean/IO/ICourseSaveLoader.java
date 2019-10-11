package ProjectOcean.IO;

import ProjectOcean.Model.Course;

import java.util.Map;
import java.util.UUID;

public interface ICourseSaveLoader {

    /**
     * tries to load all courses form a file if it cant load it it creates a new empty file
     * @returns the loaded courses
     */
    Map<UUID, Course> loadCoursesFile() throws CoursesNotFoundException;

    void createCoursesFile();



}
