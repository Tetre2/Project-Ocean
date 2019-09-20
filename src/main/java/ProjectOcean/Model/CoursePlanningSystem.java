package ProjectOcean.Model;

import java.util.List;

public class CoursePlanningSystem {

    private List<Course> courses;

    public CoursePlanningSystem() {
        this.courses = new CourseInfo().getAllCourses();
    }

    public List<Course> getAllCourses() {
        return courses;
    }

}
