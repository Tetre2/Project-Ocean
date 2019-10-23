package ProjectOcean.Model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a workspace where you can temporarily save courses for easy access.
 */
public class Workspace {

    private final List courses = new LinkedList<ICourse>();

    /**
     * Adds a specific course to the workspace
     * @param course the course instance to be added
     */
    public void addCourse(ICourse course){
        if(!courses.contains(course)){
            courses.add(course);
        }
    }

    /**
     * Remove a specific course to the workspace
     * @param course the course instance to be removed
     */
    public void removeCourse(ICourse course){
        if(courses.contains(course)){
            courses.remove(course);
        }
    }

    /**
     * Removes all course from the workspace
     */
    public void removeAllCourses(){
        while ( !courses.isEmpty()){
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

    @Override
    public String toString() {
        return "Workspace{" +
                "courses=" + courses +
                '}';
    }

    /**
     * checks if this and an other object is the same
     * @param o is the object being checked against this object
     * @return true if the this object is the same as o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workspace workspace = (Workspace) o;
        return courses.equals(workspace.courses);
    }

    /**
     * @return a hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(courses);
    }
}
