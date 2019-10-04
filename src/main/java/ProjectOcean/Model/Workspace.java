package ProjectOcean.Model;

import java.util.*;

/**
 * Represents a workspace where you can temporarily save courses for easy access.
 */
public class Workspace {

    private final List courses = new LinkedList<Course>();


    /**
     * Adds a specific course to the workspace
     * @param course the course instance to be added
     */
    public void addCourse(Course course){
        if(!courses.contains(course)){
            courses.add(course);
        }
    }

    /**
     * Remove a specific course to the workspace
     * @param course the course instance to be removed
     */
    public void removeCourse(Course course){
        if(courses.contains(course)){
            courses.remove(course);
        }
    }

    /**
     * Removes all course from the workspace
     */
    public void removeAllCourses(){
        while (courses.isEmpty() == false){
            courses.remove(0);
        }
    }

    /**
     * Gets a list of all courses in the workspace.
     * @return an unmodifiable list of courses
     */
    public List<Course> getAllCourses(){
        return Collections.unmodifiableList(courses);
    }
}
