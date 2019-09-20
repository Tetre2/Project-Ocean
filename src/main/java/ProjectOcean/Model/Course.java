package ProjectOcean.Model;

import java.util.List;

public class Course {

    private String name;

    private String courseCode;

    private float studyPoints;

    public List<Course> getAllCourses() {
        return null;
    }

    public Course(String courseCode, String name, float studyPoints) {
        this.courseCode = courseCode;
        this.name = name;
        this.studyPoints = studyPoints;
    }
}
