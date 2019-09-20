package ProjectOcean.Model;

import java.util.List;

public class Course {

    private String name;

    private String courseCode;

    private double hp;

    private List<Course> course;


    public List<Course> getAllCourses() {
        return null;
    }

    public Course(String courseCode, String name, double hp) {
        this.courseCode = courseCode;
        this.name = name;
        this.hp = hp;
    }
}
