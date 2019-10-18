package ProjectOcean.IO;

import ProjectOcean.Model.Course;

import java.util.List;

public interface ICourseSaveLoader {

    /**
     * tries to load all courses form a file if it cant load it it creates a new empty file
     * @returns the loaded courses
     */
    List<Course> loadCoursesFile() throws CoursesNotFoundException;

    void createCoursesFile();



}